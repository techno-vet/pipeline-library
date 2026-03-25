/**
 * push() — Push image to ghcr.io
 * Requires: GHCR_TOKEN credential in Jenkins (username=techno-vet, password=PAT)
 */
void call() {
    withCredentials([usernamePassword(
        credentialsId: 'ghcr-token',
        usernameVariable: 'GHCR_USER',
        passwordVariable: 'GHCR_PASS'
    )]) {
        sh """
            echo \${GHCR_PASS} | docker login ghcr.io -u \${GHCR_USER} --password-stdin
            docker push ${env.FULL_IMAGE}
            docker push ${env.IMAGE_NAME}:${env.BRANCH_NAME}
        """
    }
    echo "Pushed ${env.FULL_IMAGE}"
}
