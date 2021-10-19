#Shopping list application
An example project that used to demonstrate different aspect of the testing.

#About the application
Simple two layer spring boot application that uses maven and java 11 and H2 in-memory database 
that persist to a file upon exiting.
The program has minimal front-end, swagger-ui for user related CRD operations. (Update not implemented). 

#Building and running
If you have no Maven installed (or you have a specific setting that override the default repository),
you can use the wrapped version with "./mvnw clean install" command to build or the 
"./mvnw clean spring-boot:run" command to start the server. This is a spring-boot application, meaning you don't 
have to deploy to external servers, it uses a built-in Tomcat application server.

If you have Maven installed, you can use the mentioned command with the regular mvn command.

Once you fired up the server you can use the http://localhost:8080/ address to reach the "home page" and 
http://localhost:8080/swagger-ui.html for the swagger. 

An example create-user-object also supplied in the project folder.

#P.S.
Happy shopping and may the force be with you!