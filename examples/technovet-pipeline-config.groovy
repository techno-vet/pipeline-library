library_config {
    library("pipeline-library") {
        source {
            github {
                owner = "techno-vet"
                repo  = "pipeline-library"
            }
        }
    }
    template_selection {
        template = "static-site"
    }
}

APPLICATION_NAME = "technovet-app"
PROJECT_NS       = "technovet"
LANGUAGE         = "node"
DOCKER_CONTEXT   = "."
