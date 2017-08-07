pipeline {
    agent any 

    stages {
        stage('Build') { 
            steps { 
                sh 'gradlew build' 
            }
        }
        stage('Test'){
            steps {
                sh 'echo "TEST"'
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "DEPLOY"'
            }
        }
    }
}