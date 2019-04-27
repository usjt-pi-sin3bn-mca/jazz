# jazz
Repositório focado no desenvolvimento da aplicação Jazz, responsável pelo gerenciamento dos campeonatos, partidas e set's

# Como Testar a Aplicação Local
Para testar a aplicação local basta seguir os seguintes passos para que você provisione o *MySQL* e o *Jazz*

### Pré Requisitos


Instalar o Docker:

* [Docker for Windows](https://docs.docker.com/docker-for-windows/install/)
* [Docker for Linux](https://docs.docker.com/install/linux/docker-ce/centos/)
* [Docker for Linux](https://docs.docker.com/docker-for-mac/install/)

Necessário também instalar o Docker Compose.

[Docker Compose](https://docs.docker.com/compose/install/)


# Getting Started

Para realizar o Start da aplicação utilizando o Docker Compose, faça o seguinte.

* Vá até a pasta da aplicação na raiz.

Execute o seguinte comando

```
docker-compose up
```

Dessa maneira a aplicação subirá na porta 8080 e já estará integrada com o MySQL que subirá via Docker também dentro do Compose.

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
