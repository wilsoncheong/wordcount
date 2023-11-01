# Getting Started

### Solution Design
https://www.figma.com/community/file/1299409944143849660

### Prerequisites
-------------------	
a. Install Java 17 & Maven<br>
b. Add JAVA_HOME to environment variable<br>
c. Add java bin folder & maven bin folder to Path in environment variable<br>
d. Verify maven version is using JDK 17<br>

    $ mvn -version

e. Copy the archive folder to C:\tmp folder<br>

# To build and run the spring boot application:
-------------------	
From the terminal command line (point to bash):

	$ mvn clean install -DskipTests spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev -Djava.io.tmpdir=C:\tmp -Dwordcount.filename=C:\tmp\test.txt"

    $ mvn clean install -DskipTests spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev -Djava.io.tmpdir=C:\tmp -Dwordcount.filename=C:\tmp\test.docx"

# To package and the spring boot application:
-------------------	
Choose the profile (dev, sit, uat, prod)

    $ mvn clean install -Pdev

Go to the wordcount\target folder and run the application

    $ java -jar -Dspring.profiles.active=dev -Djava.io.tmpdir=C:\\tmp -Dwordcount.filename=C:\\tmp\\test.txt wordcount-dev.jar

    $ java -jar -Dspring.profiles.active=dev -Djava.io.tmpdir=C:\\tmp -Dwordcount.filename=C:\\tmp\\test.docx wordcount-dev.jar


# To Run test

    $ mvn test

    $ mvn -Dtest=com.adp.wordcount.unittest.** test

    $ mvn -Dtest=com.adp.wordcount.integrationtest.** test

# To Check Coverage

    $ mvn clean verify

# To Generate JavaDoc

    $ mvn javadoc:javadoc