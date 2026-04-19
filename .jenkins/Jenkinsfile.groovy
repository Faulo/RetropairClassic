pipeline {
	agent {
		label '(linux && docker) || (windows && unity)'
	}

	stages {
		stage('Index workspace') {
			steps {
				script {
					def unityConfig = readProperties file: '.project'

					if (isUnix()) {
						docker.image('faulo/compose-unity:latest').inside {
							unityProject(unityConfig)
						}
					} else {
						unityProject(unityConfig)
					}
				}
			}
		}
	}
}