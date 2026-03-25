/**
 * JTE Pipeline Template: node-app
 * Used by: openjukebox (Node.js)
 */
void call() {
    node {
        stage("Checkout") { checkout scm }

        stage("Test") {
            run_tests()
        }

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
