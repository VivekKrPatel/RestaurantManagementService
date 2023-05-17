
# Restaurant Management Service
To perform some crud operations and know more about relationship conceots of tables.


## Framework and Language used
- springboot
- java version 19

## Data Structure used in project
- List-ArrayList

## Steps to run project

- Download the source code and import in intellijIDEA.
- Put the properties into application properties of Resource Package

``` spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_management ```

``` spring.datasource.username=root ```

``` spring.datasource.password=MySQL@0101 ```

``` spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver ```

``` spring.jpa.hibernate.ddl-auto=update ```

``` spring.jpa.properties.hibernate.show_sql=true ```
``` spring.jpa.properties.hibernate.use_sql_comments=true ```

``` spring.jpa.properties.hibernate.format_sql=true ```

- Go to localhost:8080/
- For mysql workbench localHost:3306/

## Data flow
1. controller
2. model        
3. Dto
3. service 
4. repository

## Project Summary
``` In this project I am creating a Restaurant Management Service application.I have created admin as well as user and authenticated both. So we can do some basic CRUD operations like add food ,get all food as well as order food for admin or user. We can perform custom operations . I have used relations between two tables by using bidirectional i.e ManyToOne and OneToMany.To take data a from client I have used dto package. ```

end.

