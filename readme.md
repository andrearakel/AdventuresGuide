# Adventurer's Guidebook
## HBV501G - Háskóli Íslands
### Team 9
Andrea 

Bjartur

Erling Óskar Kristjánsson, eok4@hi.is

Jónas 
#### Fall 2019

## Notes for programmers
The system uses `spring-boot-starter-security` to manage user passwords so you need a username and password to run the application.

The file `src\main\resources\application.properties` contains this username and password. The password and username can also be edited there.

If you want to delete all the data in the database, you can change `spring.jpa.hibernate.ddl-auto=update` to `spring.jpa.hibernate.ddl-auto=create` in this same file.

## Notes for users (adventurers)
To SignUp for the Adventurer's Guidebook you need to enter a username and password, both of which must be longer than 2 characters. You also need to enter a valid email address.

