pipeline{
  agent{
    node { label 'android' }
  }

  stages{
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