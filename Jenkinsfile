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
                archiveArtifacts artifacts: '**/target/*.war'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/junitreports/*'
        }
    }
}