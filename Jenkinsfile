pipeline {
    agent any
    stages {
        stage("compile") {
            sh "mvn package"

//            parallel {
//                stage(“Build image”) {
//                     sh(‘cd /path/to/proj-1 && make && make publish’)
//                }
//                stage(“Build Y”) {
//                      sh(‘cd /path/to/proj-2 && make && make publish’)
//                }
//            }
        }
        stage("build image"){
            steps{
                step("login to the server"){
                    sh "docker login --username=l646505418@gmail.com -p=ljm1999115"
                }
                step("build the login image"){
                    sh "docker build -t ${env.JOB_NAME}:${BUILDTAG} ."
                    sh "docker tag ${env.JOB_NAME}:${BUILDTAG} jinmingli/${env.JOB_NAME}:${BUILDTAG}"
                }
                step("push the login image"){
                    sh "docker push jinmingli/${env.JOB_NAME}:${BUILDTAG}"
                }
            }
        }
        stage("deploy the login"){
            sh "docker run -p 9001:9001 -d --name login jinmingli/${env.JOB_NAME}:${BUILDTAG}"
        }
    }
}