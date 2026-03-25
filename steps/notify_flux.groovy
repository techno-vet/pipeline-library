/**
 * notify_flux() — Annotate the Flux image policy to trigger reconcile
 * Flux Image Automation picks up the new ghcr.io tag automatically.
 */
void call() {
    def app = pipeline_config.APPLICATION_NAME
    def ns  = "dev-${pipeline_config.PROJECT_NS}"

    echo "Notifying Flux for ${ns}/${app} image: ${env.FULL_IMAGE}"
    sh """
        kubectl annotate --overwrite deployment/${app} \
          -n ${ns} \
          force-sync=\$(date +%s) || true
        flux reconcile kustomization ${ns} --timeout=2m || true
    """
}
