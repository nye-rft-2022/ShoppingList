pipeline {
    agent any
    stages {
        stage('check maven version on executor') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('check java version on executor') {
            steps {
                sh 'java -version'
            }
        }
        stage('verify shoppinglist build') {
            steps {
                sh 'mvn verify'
            }
        }
    }
}