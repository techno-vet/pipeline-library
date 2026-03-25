/**
 * pipeline_config.groovy — place this in the root of the daitraider repo
 */
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
        template = "python-app"
    }
}

// Project-specific config
APPLICATION_NAME = "daitraider-app"
PROJECT_NS       = "daitraider"
LANGUAGE         = "python"
DOCKER_CONTEXT   = "."
