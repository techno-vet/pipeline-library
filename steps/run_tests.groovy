/**
 * run_tests() — Runs tests based on pipeline_config.TEST_CMD
 * Default Python: pytest; Default Node: npm test
 */
void call() {
    def lang = pipeline_config.LANGUAGE ?: "python"
    def cmd  = pipeline_config.TEST_CMD

    if (!cmd) {
        cmd = (lang == "python") ? "pip install -r requirements.txt && pytest" : "npm ci && npm test"
    }

    echo "Running tests: ${cmd}"
    sh cmd
}
