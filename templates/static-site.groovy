/**
 * JTE Pipeline Template: static-site
 * Used by: technovet (Hugo / Next.js)
 */
void call() {
    node {
        stage("Checkout") { checkout scm }

        stage("Build") {
            build()
        }

        stage("Push") {
            push()
        }

        stage("Deploy") {
            notify_flux()
        }

        if (env.BRANCH_NAME == "main") {
            stage("Promote to Staging") {
                promote(from: "dev", to: "staging")
            }
        }
    }
}
