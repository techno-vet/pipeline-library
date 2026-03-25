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
        template = "node-app"
    }
}

APPLICATION_NAME = "openjukebox-app"
PROJECT_NS       = "openjukebox"
LANGUAGE         = "node"
DOCKER_CONTEXT   = "."
