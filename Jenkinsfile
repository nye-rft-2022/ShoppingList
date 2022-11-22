pipeline {
    agent any
    stages {
        stage('check maven version on executor') {
            steps {
                sh 'mvn --version'
            }
        }

        stage('Build Shopping List') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Upload test results') {
            steps {
                junit '**/target/*.xml'
            }
        }

    }
}