pipeline {
    // master executor should be set to 0,it is already set to 0 for me
    agent any
    stages {
        stage('Build Jar') {
            steps {
                
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                
                bat "docker build -t='bharathnellore/selenium-docker' ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                   
			        bat "docker login --username=${user} --password=${pass}"
			        bat "docker push bharathnellore/selenium-docker:latest"
			    }                           
            }
        }
    }
}