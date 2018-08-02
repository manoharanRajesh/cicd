# CICD WorkShop Setup

## Jenkins 

* [Docker setup](./jenkins/README.md)

## VSTS Agent 

* [Vagrant-Agent](./vsts/vagrant/README.md)

* [Docker-Agent](./vsts/docker/README.md)


# To create VM in azure
1. Create Account and login to [AZURE-PORTAL](https://portal.azure.com)
2. Create VM by Clicking Deploy to azure button.
<a href="https://portal.azure.com/#create/Microsoft.Template/uri/https%3A%2F%2Fraw.githubusercontent.com%2Fazure%2Fazure-quickstart-templates%2Fmaster%2Fdocker-simple-on-ubuntu%2Fazuredeploy.json" target="_blank">
    <img src="http://azuredeploy.net/deploybutton.png"/>
</a>
    1. Enter the Following
        1. Create new resource Group : <enter resource group name of your choose>
        2. Admin user name , password & Dns name of your choose.
        3. Select Ubuntu OS 16.04.0-LTS
        4. Create VM.
    2. Connect to VM via ssh using terminal.
    3. Run 
    ``` wget -O - https://raw.githubusercontent.com/manoharanRajesh/cicd/dev/init.sh | sudo bash ```
    4. Access the Jenkins server with <Dns name>:8082 or <IP>:8082




# Simple deployment of an Ubuntu VM with Docker

<a href="https://portal.azure.com/#create/Microsoft.Template/uri/https%3A%2F%2Fraw.githubusercontent.com%2Fazure%2Fazure-quickstart-templates%2Fmaster%2Fdocker-simple-on-ubuntu%2Fazuredeploy.json" target="_blank">
    <img src="http://azuredeploy.net/deploybutton.png"/>
</a>
<a href="http://armviz.io/#/?load=https%3A%2F%2Fraw.githubusercontent.com%2Fazure%2Fazure-quickstart-templates%2Fmaster%2Fdocker-simple-on-ubuntu%2Fazuredeploy.json" target="_blank">
    <img src="http://armviz.io/visualizebutton.png"/>
</a>

This template allows you to deploy an Ubuntu VM with Docker (using the Docker Extension) installed.
You can run `docker` commands by connecting to the virtual machine with SSH.


## Running it in the Azure Vm:

wget -O - https://raw.githubusercontent.com/manoharanRajesh/cicd/dev/init.sh | sudo bash 
