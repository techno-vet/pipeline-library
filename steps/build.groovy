/**
 * build() — Docker buildx multi-platform image
 * Reads: pipeline_config.APPLICATION_NAME, pipeline_config.DOCKER_CONTEXT (optional)
 */
void call() {
    def app     = pipeline_config.APPLICATION_NAME
    def context = pipeline_config.DOCKER_CONTEXT ?: "."
    def registry = "ghcr.io/techno-vet"
    def tag      = "${env.GIT_COMMIT[0..7]}"

    env.IMAGE_TAG  = tag
    env.IMAGE_NAME = "${registry}/${app}"
    env.FULL_IMAGE = "${env.IMAGE_NAME}:${tag}"

    echo "Building ${env.FULL_IMAGE}"
    sh """
        docker buildx build \
          --platform linux/amd64 \
          --tag ${env.FULL_IMAGE} \
          --tag ${env.IMAGE_NAME}:${env.BRANCH_NAME} \
          ${context}
    """
}
