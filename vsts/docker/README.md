# VSTS agent Docker image

* [BASE-IMAGE-GIT-REPO](https://github.com/Microsoft/vsts-agent-docker)
* [IMAGE-BOX](https://hub.docker.com/r/microsoft/vsts-agent/)




## How to use these images
VSTS agents must be started with account connection information, which is provided through two environment variables:

- `VSTS_ACCOUNT`: the name of the Visual Studio account
- `VSTS_TOKEN`: a personal access token (PAT) for the Visual Studio account that has been given at least the **Agent Pools (read, manage)** scope.

To run the default VSTS agent image for a specific Visual Studio account:

```
docker run \
  -e VSTS_ACCOUNT=<name> \
  -e VSTS_TOKEN=<pat> \
  -it microsoft/vsts-agent
```

When using an image that targets a specific TFS version, the connection information is instead supplied through one of the following environment variables:

- `TFS_HOST`: the hostname of the Team Foundation Server
- `TFS_URL`: the full URL of the Team Foundation Server
- `VSTS_TOKEN`: a personal access token (PAT) for the Team Foundation Server account that has been given at least the **Agent Pools (read, manage)** scope.

If `TFS_HOST` is provided, the TFS URL is set to `https://$TFS_HOST/tfs`. If `TFS_URL` is provided, any `TFS_HOST` environment variable is ignored.

To run a VSTS agent image for TFS 2018 that identifies the server at `https://mytfs/tfs`:

```
docker run \
  -e TFS_HOST=mytfs \
  -e VSTS_TOKEN=<pat> \
  -it microsoft/vsts-agent:ubuntu-16.04-tfs-2018
```

A more secure option for passing the personal access token is supported by mounting a file that contains the token into the container and specifying the location of this file with the `VSTS_TOKEN_FILE` environment variable. For instance:

```
docker run \
  -v /path/to/my/token:/vsts-token \
  -e VSTS_ACCOUNT=<name> \
  -e VSTS_TOKEN_FILE=/vsts-token \
  -it microsoft/vsts-agent
```

Whether targeting VSTS or TFS, agents can be further configured with additional environment variables:

- `VSTS_AGENT`: the name of the agent (default: `"$(hostname)"`)
- `VSTS_POOL`: the name of the agent pool (default: `"Default"`)
- `VSTS_WORK`: the agent work folder (default: `"_work"`)

The `VSTS_AGENT` and `VSTS_WORK` values are evaluated inside the container as an expression so they can use shell expansions. The `VSTS_AGENT` value is evaluated first, so the `VSTS_WORK` value may reference the expanded `VSTS_AGENT` value.

To run a VSTS agent on Ubuntu 16.04 for a specific account with a custom agent name, pool and a volume mapped agent work folder:

```
docker run \
  -e VSTS_ACCOUNT=<name> \
  -e VSTS_TOKEN=<pat> \
  -e VSTS_AGENT='$(hostname)-agent' \
  -e VSTS_POOL=mypool \
  -e VSTS_WORK='/var/vsts/$VSTS_AGENT' \
  -v /var/vsts:/var/vsts \
  -it microsoft/vsts-agent:ubuntu-16.04
```

## Derived Images

### `standard` images
These derived images include a set of standard capabilities that enable many of the built-in VSTS build and release tasks. The Ubuntu-based standard images currently include:

- Basic command-line utilities (curl, ftp, etc.)
- Essential build tools (gcc, make, etc.)
- Azure CLI 2.0.31
- CLang 6.0
- CMake 3.10.2
- Go 1.9.4 and 1.10
- HipHop VM 3.25.2
- OpenJDK 7 (1.7.0_95), 8 (1.8.0_162) and 9 (internal)
- Java tools (Ant 1.9.6, Gradle 4.6, Maven 3.3.9)
- MySQL Client 14.14
- .NET Core SDK 2.1.101 (runtime 2.0.6)
- Node.js 8.11.1 LTS
- Powershell Core v6.1.0-preview.1
- Python 2.7.14, 3.3.7, 3.4.8, 3.5.5, 3.6.4 and 3.7.0-b2 (from hosted tools cache)
- Ruby 2.3.7, 2.4.4 and 2.5.1 (from hosted tools cache)
- Scala sbt-extras
- Subversion 1.9.3

### `docker` images
These derived images include a version of the Docker CLI and a compatible version of the Docker Compose CLI. This image cannot run most of the built-in VSTS build or release tasks but it can run tasks that invoke arbitrary Docker workloads.

These images do not run "Docker in Docker", but rather re-use the host instance of Docker. To ensure this works correctly, volume map the host's Docker socket into the container:

```
docker run \
  -e VSTS_ACCOUNT=<name> \
  -e VSTS_TOKEN=<pat> \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -it microsoft/vsts-agent:ubuntu-16.04-docker-17.12.0-ce
```

### `docker-standard` images
These derived images bring together a docker image and a standard image.

