pipeline {
  agent any

  tools {
    maven 'Maven3'
  }

  stages {
    stage('Build') {
      steps {
        dir(env.PROJECT_DIR) {
          withMaven {
            sh """
              mvn clean install -DskipUnitTests=true -DskipTests=true -Dinvoker.skip=true -DskipITs=true -B
            """
          }
        }
      }
    }
  }
}