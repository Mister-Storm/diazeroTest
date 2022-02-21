# diazeroTest
<p>This project was carried out for the selection process stage with the company Diazero.</p>
<p>I used spring-boot with spring-security and h2 database.</p>
<p>As an architectural decision I decided to follow the principles of clean architecture decoupling the domain layer from the infrastructure layer where the framework works. Thus, the business rules are decoupled from the framework, which facilitates an eventual migration.</p>
<p>A point of improvement that is needed is the removal of the cyclical dependency between the authentication service and the security service (it was necessary to add spring.main.allow-circular-references=true).</p>
<p>Was not implemented a specific endpoint to create users, being inserted two users via data.sql for tests. These users have the username: anUser and OtherUser and the password is the same as the username.</p>
<p>Authentication takes place via jwt token. Thus, it is necessary to make a post in /login to obtain the token and thus be able to access the other endpoints</p>
<p>To run the application, it is possible through the command line or docker-file, being necessary to pass the environment variables secret and expiration, as shown in the following examples</p>

<code>docker build -t diazero/testsfernando .</code></br>
<code>docker run -it -p 8080:8080 -e "secret=DiaZeroFernandoTeste expiration=60000000" diazero/testsfernando dizero</code>
<code>mvn spring-boot:run -Dsecret=DiaZeroFernandoTeste, -Dexpiration=6000000</code>
