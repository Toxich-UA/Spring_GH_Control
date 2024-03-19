pipeline {
    agent any

    tools{
        maven "maven_3_9"
        jdk "java21"
    }

    stages {
        stage('Build maven') {
            steps {
                sh 'mvn -v'
                sh 'java -version'
                sh 'docker -v'
                sh 'mvn clean install'
            }
        }
        stage('Build docker image') {
            steps {
                sh 'docker stop toxichua-ghcontrol || true && docker rm toxichua-ghcontrol || true'
                sh 'docker build -t toxichua-ghcontrol .'
            }
        }
        stage('Run docker') {
            steps {
                sh 'docker run -d -p 8081:8080 --name toxichua-ghcontrol toxichua-ghcontrol'
            }
        }
    }

}