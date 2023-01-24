pipeline {
    agent any

    stages {

        stage('Clean Docker') {
            steps {
                sh 'docker ps -q -f status=exited | xargs --no-run-if-empty docker rm'
            }
        }

        stage('Compile and Clean') {
            steps {
                sh "mvn clean compile"
            }
        }

        stage('package') {
            steps {
                sh "mvn package"
            }
        }

        stage('Build Docker image'){
            steps {
                echo "Promotion Application"
                sh 'ls'
                sh 'docker build -t  bum240798/docker_jenkins_springboot:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Login'){
            steps {
                 withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u bum240798 -p ${Dockerpwd}"
                }
            }
        }

        stage('Docker Push'){
            steps {
                sh 'docker push bum240798/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }

        stage('Docker deploy'){
            steps {
                sh 'docker run -itd -p  8081:8080 bum240798/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }

        stage('Archving') {
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}