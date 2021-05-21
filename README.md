# endpoint-test
Desafio técnico Conductor
O projeto foi realizado na linguagem Java, utilizando SpringBoot.
Utilizei essa stack por ser mais simples na hora de configurar o ambiente e focar apenas nas regras do exercício.


# Clone este repositório
$ git clone https://github.com/bragamateus/endpoint-test.git

# Acesse a pasta do projeto no terminal/cmd
$ cd endpoint-test

# Vá para a pasta demo
$ cd demo

# Instale as dependências e empacote o projeto
$ mvn clean package

# Acesse a pasta target
$ cd target/

# Execute a aplicação
$ java -jar demo-0.0.1-SNAPSHOT.jar

# O servidor inciará na porta:8080 
- Para acessar a base de dados - acesse <http://localhost:8080/h2-console> 
- JDBC URL: jdbc:h2:mem:db
- User Name: user
- Password: pass

Para teste do endpoint, utilize Postan ou Insomnia
