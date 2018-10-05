# SpringCRM

Live version of the application (not optimized for mobile):

https://webapps.andersengenolsen.com/SpringCRM_war/login

Application built with Spring MVC.

Simple CRM application. User must authenticate (Spring Security), before adding / deleting customers. Customers are saved to a MySQL database.
User passwords are stored in an encrypted format using bcrypt.

Key dependencies:

    Spring MVC
    Spring Security
    Hibernate Core
    Hibernate Validator

Database schema:

![database schema](https://github.com/andersengenolsen/SpringCRM/blob/master/database-schema.png)

Simple use case of the planned features:

![use case diagram](https://github.com/andersengenolsen/SpringCRM/blob/master/usecase.png)

Sample application, needs alot more features to be usable..
