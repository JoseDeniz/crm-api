# CRM API

### Technologies

  - JDK 17
  - Spring Boot 3.0.2
  - Spring Boot Data JPA
  - Spring Boot Security
    - [JSON Web Tokens (JWT)](https://jwt.io/)
  - Docker
    - PostgreSQL container
    - Pgadmin container

### Local environment

  - Restore maven dependencies
  - Run containers (postgres and pgadmin)
    
    ````docker-compose up````

  - Run / Debug _ApiApplication.java_
    > Note: This will create the following in the database: 2 customers by default and 2 users by default (ROLE_USER & ROLE_ADMIN)

#### Run tests
Tests are located under _test_ folder

```./mvnw clean && ./mvnw test```

### How to use

#### Option 1. Http Client | IntelliJ (Ultimate version only)
- Open _api.http_ file and execute the requests

#### Option 2. Import postman collection file
- crm-api.postman_collection.json
> This has collection variables and pre-request scripts to get the token automatically

### See data in the database

#### Option 1. Database integration | IntelliJ (Ultimate version only)
1. Open right toolbar tab named _Database_
2. Create new data source (PostgreSQL)
   - Install required drivers 
3. Configure
   - Host: localhost
   - Port: 5432
   - Authentication: User & Password (see _src/main/docker/postgres/Dockerfile_ environment variables)
       - User: POSTGRES_USER
       - Password: POSTGRES_PASSWORD
   - Database: crm
4. Test Connection

#### Option 2. PGAdmin (docker container)

1. Go to _http://localhost:8084/_
2. Login (see _docker-compose.yml_ pgadmin_db environment variables)
   - user: PGADMIN_DEFAULT_EMAIL
   - pass: PGADMIN_DEFAULT_PASSWORD
3. Add new server
   - (General Tab -> Name): Free text to insert, i.e. local_db
   - (Connection Tab)
     - (Host name / address): db (service name in the _docker-compose.yml_)
     - Port: 5432
     - Credentials (see _src/main/docker/postgres/Dockerfile_ environment variables)
       - Username: POSTGRES_USER
       - Password: POSTGRES_PASSWORD

### Package for deployment

#### Option 1. Use the docker-compose
1. Clean and package the application

   ```./mvnw clean package -DskipTests```

2. Copy the _.jar_ file from the _target_ folder to _src/main/docker/springboot_

   ```cp ./target/api-0.0.1-SNAPSHOT.jar ./src/main/docker/springboot```

3. Go to _src/main/docker_

   ```cd src/main/docker```

4. Run

   ```docker-compose -f docker-compose-prod.yml up```

> This will run a postgres, pgadmin and a java container at once

#### Option 2. Build only the Image (no database container)

To create the image, follow these steps:
1. Clean and package the application

   ```./mvnw clean package -DskipTests```

2. Copy the _.jar_ file from the _target_ folder to _src/main/docker/springboot_

   ```cp ./target/api-0.0.1-SNAPSHOT.jar ./src/main/docker/springboot```

3. Go to _src/main/docker/springboot_

   ```cd src/main/docker/springboot```

4. Build the image with _Dockerfile_ with a tag name

   ```docker build -t crm-api .```

> Remember to update the production config variables (src/main/resources/application-prod.properties) to be able to connect to the db server
