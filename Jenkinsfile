pipeline {
    agent any
    stages {
        stage('check maven version') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('compile application') {
            steps {
                sh 'mvn compile'
            }
        }
    }
}