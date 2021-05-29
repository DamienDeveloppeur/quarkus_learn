// déclarative pipeline
pipeline {
  // (agent) where to execute
  agent any 
  stages {
    stage("build") {
      // 
      steps {
        echo 'building the application...'
            script {
            def test = 2+2 > 3 ? 'cool' : 'not cool'
            echo test
            }
      }
    }
    stage("test") {
      // 
      steps {
        echo 'Testing the application...'
      }
    }
    stage("deploy") {
      // 
      steps {
        echo 'Deploy the application...'
      }
    }
  }
}
node {
 // groovy script 
}
