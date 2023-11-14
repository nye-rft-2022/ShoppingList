pipeline {
    agent any
    stages {
        stage('check maven version') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('check java version') {
            steps {
                sh 'java -version'
            }
        }
        stage('check maven version') {
            steps {
                sh 'mvn verify'
            }
        }



    }
}