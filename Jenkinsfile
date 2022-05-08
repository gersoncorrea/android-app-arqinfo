pipeline{
  agent{
    node { label 'android' }
  }

  stages{
    stage('Lint & Unit Test'){
      parallel {

        stage('ktlint'){
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