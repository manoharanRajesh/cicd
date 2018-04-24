#!groovy


node() {
    stage('Checkout'){
        checkout scm
    }
    stage ('build'){
        sh 'java -version'
        sh 'mvn clean'
    }

    parallel 'integration-tests':{
       // node('mvn-3.3'){ ... }
    }, 'functional-tests':{
       //  node('selenium'){ ... }
    }

    stage 'deployment'
    input 'Do you approve deployment?'
    node{
        // deploy the things
    }

}
