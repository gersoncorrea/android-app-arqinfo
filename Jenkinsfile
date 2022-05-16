pipeline{
  agent{
    node { label 'android' }
  }

  stages{

  stage('Check labels'){
  steps{
    pullRequests.labels.each{
        echo "label: $it"
    }
  }
  }

    stage('Test'){
      parallel {

        stage('KtLint Test'){
          steps {
            sh 'sh ./gradlew ktlintCheck'
          }
        }

        stage('Unit Test'){
          steps{
            sh 'sh ./gradlew testDebugUnitTest'
          }
        }
        
      }
    }
  }

}