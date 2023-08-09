# Spring-Security
Java 8 JWT Spring Security using custom mapper which stores masked phone number in user/admin table and unmasked phone number in authority table in single controller
Login uses Authority table for username and password
I used Common DTO for all the entities. 

# Alteration in this Repo
Move application.properties and psql_query.txt to "resource" folder in your IDEA
Create a "configuration" folder in yourjava folder in scr directory and move "AuthFilter.java", "InfoUserDetailsService.java", "UserDetailsInfo.java" and "securityConfig"

# How to Run
In your IDEA open terminal and run these these following commands
-> mvn clean
-> mvn clean install
If above given 2 commands gives "Build Success" then run the below command
-> mvn spring-boot:run
