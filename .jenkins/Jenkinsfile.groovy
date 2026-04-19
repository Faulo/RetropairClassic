pipeline {
	agent {
		label 'linux && docker'
	}

	stages {
		stage('Index workspace') {
			steps {
				script {
					def unityConfig = readProperties file: '.project'

					docker.image('faulo/compose-unity:latest').inside {
						unityProject(unityConfig)
					}
				}
			}
		}
	}
}