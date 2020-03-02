# MLSimian


Environment
---

1. We can run our application on the localhost or in the cloud, using a RDS instance, for example. For a production environment, change the `analysis_config.yml` and `stats_config.yml`, changing the database connection.

2. We can simulate our application on localhost with docker-compose. Run the command: `docker-compose build`. We should have 3 images:
- mysql_image (optional): You can use this image if you want a localhost connection
- analysis_service_image
- stats_service_image

3. We can run our applications with the command: `docker-compose up`. We should then have 3 running containers:
- mysql_image (optional): In a production environment, you could use a RDS instance, for example
- analysis_service_image: The service responsible for processing new Simian Analysis. (POST) => http://localhost:8080/simian
- stats_service_image: The service responsible for retrieving computed Simian Stats. (GET) => http://localhost:8090/stats


4. You also can redirect your machine localhost to your container localhost with the following nginx setup on `sites-available` folder:
```
server {
	listen 80 default_server;
	listen [::]:80 default_server;

	root /var/www/html;

	index index.html index.htm index.nginx-debian.html;

	server_name _;

	location / {
		proxy_pass  http://127.0.0.1:12345/;
	}
}
```

How to start the MLSimian applications (outside docker-compose)
---

1. Run `mvn clean install` to build your application
2. Create a mysql schema, for example `ml_simian`
3. Setup your database configurations (mysql user, mysql password, mysql host and schema name) inside the `config.yml`
4. Run database migrations with `java -jar target/ml-simian-analysis-1.0.jar db migrate config.yml` OR `java -jar target/ml-simian-stats-1.0.jar db migrate config.yml`
5. Start application with `java -jar target/ml-simian-analysis-1.0.jar server config.yml` OR `java -jar target/ml-simian-stats-1.0.jar server config.yml`
6. To check that your application is running enter url `http://localhost:8080`

Tests
---

There are unit tests in this project. You can run it with `mvn clean test`

Project structure
---
1. `Resources` are responsible for handling HTTP requests
2. `Services` are responsible for handling the business logic
3. `DAOs` are responsible for database connections


Endpoints
---

1. You can add a new DNA sequence with a `POST method` on the URL `www.grcampos.com/simian`: `curl --header "Content-Type: application/json"   --request POST   --data '{"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTB", "TCACTG"]}'   http://www.grcampos.com/simian`
2. You can check the results with a `GET method` on the URL `www.grcampos.com/stats`: `curl --header "Content-Type: application/json"   --request GET   http://www.grcampos.com/stats`

Other
---
1. The project is running on an AWS EC2 server and a AWS RDS. Both are very small machines and may fail. Please, feel free to contact me if the server is not reachable or if you need to clear the database.
2. For the coding algorithm, I assumed that the DNA sequence should be case insensitive. Besides, a sequence with more than 4 equal characters should still count as a single sequence
3. Invalid DNA sequences does not sum up on the `count_human_dna` property, but both bad formatted DNA's and human DNA's, returns a 403 status


Contact
---
grcampos21@gmail.com | (11) 99117-6997