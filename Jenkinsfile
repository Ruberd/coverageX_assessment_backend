pipeline {
  agent any

  tools {
    maven 'Maven3'   // From Jenkins global tools config
  }

  environment {
    DOCKER_IMAGE = "nijanthan97/demo-app"
    DOCKER_TAG = "latest"
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build JAR') {
      steps {
        withMaven(maven: 'Maven3') {
          sh '''
            mvn clean install -DskipTests=true -B
          '''
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        sh '''
          docker build -t $DOCKER_IMAGE:$DOCKER_TAG .
        '''
      }
    }

    stage('Login to DockerHub') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'dockerhub-cred',
          usernameVariable: 'DOCKER_USER',
          passwordVariable: 'DOCKER_PASS'
        )]) {
          sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
          '''
        }
      }
    }

    stage('Push Docker Image') {
      steps {
        sh '''
          docker push $DOCKER_IMAGE:$DOCKER_TAG
        '''
      }
    }

    stage('Deploy to EC2') {
      steps {
        withCredentials([sshUserPrivateKey(
          credentialsId: 'ec2-ssh-key',
          keyFileVariable: 'SSH_KEY'
        )]) {
          sh '''
            ssh -o StrictHostKeyChecking=no -i $SSH_KEY ubuntu@13.51.193.165 '
              docker pull nijanthan97/demo-app:latest &&
              docker stop demo-app || true &&
              docker rm demo-app || true &&
              docker run -d -p 7000:7000 --name demo-app nijanthan97/demo-app:latest
            '
          '''
        }
      }
    }

  }
}
