/**
 * promote(from, to) — Manual-gate promotion between environments
 * Sends a Flux reconcile trigger for the target namespace.
 */
void call(Map args) {
    def app  = pipeline_config.APPLICATION_NAME
    def ns   = pipeline_config.PROJECT_NS
    def from = args.from ?: "dev"
    def to   = args.to   ?: "staging"

    def approved = false
    timeout(time: 30, unit: "MINUTES") {
        approved = input(
            message: "Promote ${app} from ${from} to ${to}?",
            ok: "Promote",
            parameters: [booleanParam(name: "CONFIRM", defaultValue: true)]
        )
    }

    if (approved) {
        echo "Promoting ${app} → ${to}-${ns}"
        sh """
            kubectl set image deployment/${app} \
              ${app}=${env.FULL_IMAGE} \
              -n ${to}-${ns}
            kubectl rollout status deployment/${app} -n ${to}-${ns} --timeout=5m
        """
    } else {
        echo "Promotion to ${to} skipped."
    }
}
