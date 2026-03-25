# techno-vet/pipeline-library

Jenkins Templating Engine (JTE) shared pipeline library for all techno-vet projects.

## Structure

```
templates/          JTE pipeline templates (one per app type)
  python-app.groovy   daitraider (Python/FastAPI)
  static-site.groovy  technovet (Hugo/Next.js)
  node-app.groovy     openjukebox (Node.js)

steps/              Reusable pipeline steps
  build.groovy        docker buildx → ghcr.io image
  push.groovy         push to ghcr.io/techno-vet
  run_tests.groovy    pytest or npm test
  notify_flux.groovy  trigger Flux reconcile
  promote.groovy      manual-gate env promotion

examples/           Drop-in pipeline_config.groovy for each project
```

## Usage

Each project repo needs a `pipeline_config.groovy` in its root.
See `examples/` for ready-to-use configs.

## Environments

| Branch | Deploys to | Domain pattern |
|--------|-----------|----------------|
| any    | dev-*     | dev.{domain}   |
| main   | staging-* | staging.{domain} (after approval) |
| tag    | prod-*    | {domain} (after approval) |
