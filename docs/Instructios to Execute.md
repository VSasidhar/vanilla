# 1)	# Instructions to run from local Chrome browser:

Go to src/main/resources folder packer and inside choices.conf change the  Host value to host.localhost

**# valid choices are: "host.localhost", "host.docker.container", "host.docker.selenium.grid"**

**HOST = "host.localhost"**

Then Right click on testing.xml and run as  testNG Suite.

Option2: right click on pom.xml and run as  maven install.


To run from command prompt, navigate to project root folder and run below command 

mvn clean install test


Note: if facing any issue with dependencies please right click on project folder and maven  update project and check force update option and click ok.


# 2)	In Order to Run on Local Selenium Grid:

Please download Selenium standalone server form below link.

[Selenium-standaloneserver](https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.2.0/selenium-server-4.2.2.jar)

And download Chromedriver corresponding to the chrome browser version installed on your machine from below url and place it in same folder as in Selenium standalone 

[Chrome-Driver](https://chromedriver.chromium.org/downloads)


Once both the file are in the same folder..-  Using command prompt/ mac terminal navigate  to the folder containing standalone server and chromedriver

And run below command.

java -jar selenium-server-<version>.jar standalone

Replace <version> with the standalone server java version in this case it should be 4.2.2 

Once both hub and node are up you can launch the browser and type  http://localhost:4444/ui

You should be able to see chrome-browser icon

Go to src/main/resources folder packer and inside choices.conf change the Host value to host.docker.selenium.grid

**# valid choices are: "host.localhost", "host.docker.container", "host.docker.selenium.grid"**

**HOST = "host.docker.selenium.grid"**

Inside eclipse/Intellij ide: 
Then Right click on testing.xml and run as  testNG Suite.

Option2: right click on pom.xml and run as  maven install.



Then Right click on project folder or testing.xml ( inside project root folder) from the Eclipse/Intellij ide and run as TestNG.

If you want to run using maven : in ide after right clicking on root folder or pom.xml (inside project root folder) – run as  maven install



# 3)	To Execute Scripts on Docker:

Make sur docker is installed on your machine and start docker – daemon

Then Navigate to project root folder where docker-compose.yaml file located using command prompt / terminal

Then Run below command:

Docker-compose up

This command will bring up the Hub and Node – to make sure open  http://localhost:4444/ui in any browser and verify if chrome icon is present or not.


Go to src/main/resources folder packer and inside choices.conf change the Host value to host.docker.selenium.grid

**# valid choices are: "host.localhost", "host.docker.container", "host.docker.selenium.grid"**

**HOST = "host.docker.selenium.grid"**


Inside eclipse/Intellij ide: 
Then Right click on testing.xml and run as  testNG Suite.

Option2: right click on pom.xml and run as  maven install.



To view reports:

This framework is integrated with extent reports  
Project root folder  Extent reports  ExtentReportResults.html