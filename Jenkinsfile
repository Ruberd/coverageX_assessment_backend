pipeline {
  agent any

  tools {
    maven 'Maven3'
  }

  stages {
    stage('Build') {
      steps {
        withMaven {
          sh '''
            mvn clean install -DskipUnitTests=true -DskipTests=true -Dinvoker.skip=true -DskipITs=true -B
          '''
        }
      }
    }
  }
}
