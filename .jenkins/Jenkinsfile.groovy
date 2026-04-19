pipeline {
	agent {
		label 'linux && docker'
	}

	stages {
		stage('Index workspace') {
			steps {
				script {
					def unityConfig = readProperties file: 'unityProject.properties'

					docker.image('faulo/compose-unity:latest').inside {
						unityProject(unityConfig)
					}
				}
			}
		}
	}
}