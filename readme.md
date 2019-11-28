# Adventurer's Guidebook
## HBV501G - Háskóli Íslands
### Team 9
Andrea, ars59@hi.is

Bjartur, boj8@hi.is

Erling Óskar Kristjánsson, eok4@hi.is

Jónas, jgs7@hi.is

#### Fall 2019

## How to build and run

Clone or download the git repository to your computer. Open the application in IntelliJ and run the application. The main function which runs the application is in `AdventuresGuide/src/main/java/is/hi/hbv501g/agb/AGB/Application.java`.

Alternative you should be able to run `mvn clean build` and run the generated Fat JAR. This Fat JAR should also be built when you run the application with the current settings from ´pom.xml´, and it contains all necessary dependencies. You can also download this Fat JAR from [this link](https://drive.google.com/file/d/1b8bwxiqH8pe5Je-PH0DOwRpfeuKEvu4P/view?usp=sharing) and run it on your machine with the command ´java -jar AGB-0.0.1-SNAPSHOT.jar´. When the application is running, go to [localhost:8080](localhost:8080) in your browser and the web application should be running.

## Notes for users (adventurers)
To SignUp for the Adventurer's Guidebook you need to enter a username and password, both of which must be longer than 2 characters. You also need to enter a valid email address.

Don't worry about making errors when you submitting your user input, such as when you create guides, edit your profile or create reviews. If your input is invalid, you will be informed accordingly.

Each adventurer can only write one review per guide. You can also write a review for your own guide.


## Notes for programmers
The system uses `spring-boot-starter-security` to manage user passwords so you need a username and password to run the application.
The file `src\main\resources\application.properties` contains this username and password. The password and username can also be edited there.

If you want to delete all the data in the database, you can change `spring.jpa.hibernate.ddl-auto=update` to `spring.jpa.hibernate.ddl-auto=create` in the same file.

### Database Instances
Only one running application can be connected to the same instance of the database at any given time. So the database of the master branch and fat jar used in the presentation and handed in to Matthias should not be used by anyone else until the year 2020.

#### Andrea
jdbc:postgresql://balarama.db.elephantsql.com:5432/zxhetpdv

Username: zxhetpdv

Password: 4P3Kntyc99y7s5I-fVgCJt7MQSUlN29A


#### Erling (Master, Presentation, Matthias)
jdbc:postgresql://balarama.db.elephantsql.com:5432/udzhabmv

Username: udzhabmv

Password: orHuLqQAcB7aRHEK7T98o45nBQVKVGBF



#### Original
jdbc:postgresql://balarama.db.elephantsql.com:5432/ebrdqlrr

Username: ebrdqlrr

Password: 1z5RvSCJjtLBN0Vp4DExGmtV-g_Me-h9



#### Team 6
jdbc:postgresql://balarama.db.elephantsql.com:5432/jslneyby

Username: jslneyby

Password: DepZxzJc9TZhbQs3jPo6-oWtERyL0sES



### Bootstrap

You can click an example, right-click the background and select *View Page Source* and follow the example HTML and CSS at
https://getbootstrap.com/docs/4.3/examples/

https://getbootstrap.com/docs/4.3/getting-started/introduction/

This page shows a lot: https://getbootstrap.com/docs/4.3/layout/overview/

How the navbar was created: https://getbootstrap.com/docs/4.3/components/navbar/

### TODO

We can add `@Lob` above Description of Guide entity to keep it in database as a BLOB, probably allowing for longer length. Drawback of BLOB is that you can't search for the value, but we're not searching by Description anyway.

Can also add the following above dates to make them generate automatically
```
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "created_at", nullable = false, updatable = false)
@CreatedDate

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "updated_at", nullable = false)
@LastModifiedDate
```
