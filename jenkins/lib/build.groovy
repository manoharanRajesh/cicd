
def cleanWorkspace() {
    sh "echo 'Cleaning workspace'"
    deleteDir()
}

def checkout(String project, String branch) {
    checkout scm
}

def archiveTestResults() {
    step([$class: 'JUnitResultArchiver', testResults: '**/target/**/TEST*.xml', allowEmptyResults: true])
}



return this;
