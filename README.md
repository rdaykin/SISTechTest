## Synopsis

A solution to the SIS Java Technical Test, in which a RESTFul webservice is created to allow football teams to be added to a list and then their details to be accessed via a number of different paths.
## Installation and setup

This project requires gradle and Java to be installed.  Once these prerequisites are installed, simply clone the repository to the directory of your choosing from git with the following command:

git clone https://github.com/rdaykin/SISTechTest.git

Then enter the root directory of the project, SISTechTest, and build the project using 

gradle build

Once the project is built, deploy to a web container with the command

gradle jettyRun

The application should then be available from the root

http://localhost:8080/football/addplayer

# Using the application

http://localhost:8080/football/homepage - contains links for the majority of pages, including adding teams, adding players to teams and two of the display pages.
http://localhost:8080/football/teamdetails/all - displays the details of all teams as JSON
http://localhost:8080/football/teamdetails/{team} - displays the details of an individual team specified by the path variable {team} as JSON
http://localhost:8080/football/teamnames - displays the names of all teams in JSON as a list
http://localhost:8080/football/addplayer - allows a player to be added to an existing team
http://localhost:8080/football/newteam - allows a new team to be added

## Libraries

I based the application around Spring MVC, as this is a framework I have experience with and I knew I could easily achieve the goals of the test using this framework.

I used Log4j for logging as it is a good library for lightweight but verbose logging.

Mockito and JUnit were used for testing purposes.
