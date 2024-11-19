pipeline {
    agent any
    stages {
        stage('Check Maven Version') {
            steps {
                sh 'mvn -version'
            }
        }
        stage('Package') {
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