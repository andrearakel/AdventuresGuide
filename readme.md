# Adventurer's Guidebook
## HBV501G - Háskóli Íslands
### Team 9
Andrea 

Bjartur

Erling Óskar Kristjánsson, eok4@hi.is

Jónas 
#### Fall 2019


## Notes for users (adventurers)
To SignUp for the Adventurer's Guidebook you need to enter a username and password, both of which must be longer than 2 characters. You also need to enter a valid email address.


## Notes for programmers
The system uses `spring-boot-starter-security` to manage user passwords so you need a username and password to run the application.
The file `src\main\resources\application.properties` contains this username and password. The password and username can also be edited there.

If you want to delete all the data in the database, you can change `spring.jpa.hibernate.ddl-auto=update` to `spring.jpa.hibernate.ddl-auto=create` in the same file.

### Database Instances
#### Andrea
jdbc:postgresql://balarama.db.elephantsql.com:5432/zxhetpdv

Username: zxhetpdv

Password: 4P3Kntyc99y7s5I-fVgCJt7MQSUlN29A


#### Erling
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