
def push(space,org,url){
    pushToCloudFoundry cloudSpace: space, credentialsId: 'cfId', organization: org, target: url

}

def deployApp(){
    pushToCloudFoundry cloudSpace: 't-space', credentialsId: 'pcf', organization: 'g-org', target: 'https://api.run.pivotal.io'
}

return this;