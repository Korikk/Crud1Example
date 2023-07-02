pipeline {
    agent any
    tools{
        maven 'default'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Korikk/Crud1Example']])
                sh 'mvn clean install'
            }

        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t korik/crud1exampledemo .'
                }
            }
        }
    }
}






