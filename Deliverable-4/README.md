# ser322_team20_cookbook

[MySQL Shell] If in `MySQL JS` switch to MySQL SQL using:
```
\sql
```

[MySQL Shell] Connect to the Workbench using:
```
\connect root@localhost
```

Here I am using the default connection suggested by MySQL tutorials.

[MySQL Shell] Load the database using:
```
team20_cookbook_create.sql
```
OR
```
source C://Users/Wraith/Desktop/ser322_team20_cookbook/sql/team20_create_cookbook.sql
```

Make sure the MySQL shell has access to the Cookbook database by using:
```
show databases;
```

Set the default schema to Cookbook using:
```
use cookbook;
```

[Terminal] Open a terminal and locate the files using:
```
C:\Users\Wraith\Desktop\ser322_team20_cookbook
```

[Terminal] Connect to the application using:
```
java -cp lib/mysql-connector-java-8.0.23.jar;bin cookbook.Application "jdbc:mysql://localhost/cookbook?autoReconnect=true&useSSL=false" root mysql com.mysql.cj.jdbc.Driver
```
OR
```
java -cp lib/mysql-connector-java-8.0.23.jar;bin cookbook.Application "jdbc:mysql://localhost/cookbook?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&&serverTimezone=America/New_York" root mysql com.mysql.cj.jdbc.Driver
```

The notation here is:
- `lib/mysql-connector-java-8.0.23.jar;bin` is the path to the SQL connector.
- `cookbook.Application` is the pathfile to the java file with all of the application information.
- `jdbc:mysql://localhost/cookbook?` is the URL.
- `autoReconnect=true` encourages the JDBC driver to connect to failing connections. 
- `useSSL=false` is only needed for MySQL 5.5.45+, 5.6.26+, or 5.7.6+. If you are using one of these MySQL versions, set `useSSL` to `true`.
- `useLegacyDatetimeCode=false"` This refers to how the Connector retrieves a timestamp. According to the [MySQL manual](https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-known-issues-limitations.html), this refers to MySQL Connector/J 5.1 and may not be necessary for a different version.
- 'serverTimezone=America/New_York' This sets the timezone to New York's timezone.