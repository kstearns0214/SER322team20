# ser322_team20_cookbook

## File Organization:

- `lib` contains the MySQL connector JAR file.
- `resources` contains the diagrams from Deliverable 2 and 3.
- `sql` has the `create.sql` script which creates and populates the database.

## Instructions to use MySQL to load the scripts and access the java file:

[MySQL Shell] If in `MySQL JS` switch to MySQL SQL using:
```
\sql
```

[MySQL Shell] Connect to the Workbench using:
```
\connect root@localhost
```

Here I am using the default connection suggested by MySQL tutorials.

We need to create the database and load it full of information.
[MySQL Shell] Load the database using:
```
team20_cookbook_create.sql
```
[MySQL Shell] OR
```
source <path to file>/cookbook/sql/create.sql
```
[MySQL Shell] For example:
```
source C://Users/Wraith/Desktop/cookbook/sql/create.sql
```

[MySQL Shell] Make sure the MySQL shell has access to the cookbook database by using:
```
show databases;
```

[MySQL Shell] Set the default schema to cookbook using:
```
use cookbook;
```

[Terminal] Open a terminal and locate the RecipePages java file using:
```
<path to file>/cookbook/src/cookbook
```
[Terminal] OR
```
C:/Users/Wraith/Desktop/cookbook/src/cookbook
```

[Terminal] Create a RecipePages class file using:
```
javac RecipePages.java
```

[Terminal] Navigate to the source directory of `<path to folder>/cookbook`

[Terminal] Connect to the application database using:
```
java -cp lib/mysql-connector-java-8.0.23.jar;bin cookbook.RecipePages "jdbc:mysql://localhost/cookbook?autoReconnect=true&useSSL=false" root mysql com.mysql.cj.jdbc.Driver
```
[Terminal] OR
```
java -cp lib/mysql-connector-java-8.0.23.jar;bin cookbook.RecipePages "jdbc:mysql://localhost/cookbook?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&&serverTimezone=America/New_York" root mysql com.mysql.cj.jdbc.Driver
```

The notation here is:
- `lib/mysql-connector-java-8.0.23.jar;bin` is the path to the SQL connector. Change the `;` to `:` if using a Mac OS.
- `cookbook.RecipePages` is the pathfile to the java file with all of the application information.
- `jdbc:mysql://localhost/cookbook?` is the URL.
- `autoReconnect=true` encourages the JDBC driver to connect to failing connections. 
- `useSSL=false` is only needed for MySQL 5.5.45+, 5.6.26+, or 5.7.6+. If you are using one of these MySQL versions, set `useSSL` to `true`.
- `useLegacyDatetimeCode=false"` This refers to how the Connector retrieves a timestamp. According to the [MySQL manual](https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-known-issues-limitations.html), this refers to MySQL Connector/J 5.1 and may not be necessary for a different version.
- `serverTimezone=America/New_York` This sets the timezone to New York's timezone.