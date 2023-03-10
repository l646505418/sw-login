pipeline {
    agent any
    stages {
        stage("compile") {
          steps{
            sh "git config --global user.email \"646505418@qq.com\""
              sh  "git config --global user.name \"lijinming\""

            sh "mvn package"
            }

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
                    sh "docker login --username=l646505418@gmail.com -p=ljm1999115"
                    sh "docker build -t ${env.JOB_NAME}:${BUILDTAG} ."
                    sh "docker tag ${env.JOB_NAME}:${BUILDTAG} jinmingli/${env.JOB_NAME}:${BUILDTAG}"
                    sh "docker push jinmingli/${env.JOB_NAME}:${BUILDTAG}"
            }
        }
        stage("deploy the login"){
        steps{
            sh "docker run -p 9001:9001 -d --name login jinmingli/${env.JOB_NAME}:${BUILDTAG}"
            }
        }
    }
}