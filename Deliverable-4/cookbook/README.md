# ser322_team20_cookbook

- Please keep these instructions.
- If you edit the `RecipesPages.java` file, you need to create a new class file and place it in: `Deliverable-4/cookbook/bin/cookbook`

## File Organization:

- `lib` contains the MySQL connector JAR file.
- `resources` contains the diagrams from Deliverable 2 and 3.
- `sql` has the `create.sql` script which creates and populates the database.

# Instructions to use MySQL to load the scripts and access the java file:

- [MySQL Shell] If in `MySQL JS` switch to MySQL SQL using:
  - `\sql`
- [MySQL Shell] Connect to the Workbench using:
  - `\connect root@localhost`
- [MySQL Shell] Load the database using `team20_cookbook_create.sql`. 
  - [MySQL Shell] `source <path to file>/cookbook/sql/create.sql`
    - For example: `source C://Users/Wraith/Desktop/cookbook/sql/create.sql`
- [MySQL Shell] Make sure the MySQL shell has access to the cookbook database by using:
  - `show databases;`
- [MySQL Shell] Set the default schema to cookbook using:
  - `use cookbook;`

- [Terminal] Open a terminal and locate the RecipePages java file using `<path to file>/cookbook/src/cookbook`
  - `C:/Users/Wraith/Desktop/cookbook/src/cookbook`
- [Terminal] Create a RecipePages class file using:
  - `javac RecipePages.java`
- [Terminal] Navigate to the source directory of `<path to folder>/cookbook`
- [Terminal] Connect to the application database using one of the following commands:
  - `java -cp lib/mysql-connector-java-8.0.23.jar;bin cookbook.RecipePages "jdbc:mysql://localhost/cookbook?autoReconnect=true&useSSL=false" root mysql com.mysql.cj.jdbc.Driver`
  - `java -cp lib/mysql-connector-java-8.0.23.jar;bin cookbook.RecipePages "jdbc:mysql://localhost/cookbook?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&&serverTimezone=America/New_York" root mysql com.mysql.cj.jdbc.Driver`

The notation here is:
- `lib/mysql-connector-java-8.0.23.jar;bin` is the path to the SQL connector. Change the `;` to `:` if using a Mac OS.
- `cookbook.RecipePages` is the pathfile to the java file with all of the application information.
- `jdbc:mysql://localhost/cookbook?` is the URL and `cookbook` is the name of the database as listed in MySQL.
- `autoReconnect=true` encourages the JDBC driver to connect to failing connections. 
- `useSSL=false` is only needed for MySQL 5.5.45+, 5.6.26+, or 5.7.6+. If you are using one of these MySQL versions, set `useSSL` to `true`.
- `root` refers to the username on your MySQL.
- `mysql` refers to the password of your MySQL.
- `com.mysql.jdbc.Driver` refers to the driver.
- `useLegacyDatetimeCode=false"` This refers to how the Connector retrieves a timestamp. According to the [MySQL manual](https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-known-issues-limitations.html), this refers to MySQL Connector/J 5.1 and may not be necessary for a different version.
- `serverTimezone=America/New_York` This sets the timezone to New York's timezone.