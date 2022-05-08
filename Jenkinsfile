pipeline{
  agent{
    node { label 'android' }
  }

  stages{
    stage('Lint & Unit Test'){
      parallel {

        stage('ktlint'){
          steps {
            sh './gradlew ktlintCheck'
          }
        }

        stage('Unit Test'){
          steps{
            sh './gradlew testDebugUnitTest'
          }
        }
        
      }
    }
  }

}