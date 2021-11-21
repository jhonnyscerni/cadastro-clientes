# MVP Cadastro de Clientes

Este é um MVP de cadastro de clientes, com a possibilidade de:

* Criação de novos clientes;
* Atualização de clientes existentes;
* Listar os clientes de forma paginada;
* Buscas por atributos cadastrais do cliente;
* Cada elemento retornado pela api de clientes informe a idade;

## Pré-requisitos

* Maven
* Java 11
* Docker

## Frameworks
* MapStruct é um gerador de código que simplifica muito a implementação de mapeamentos entre os tipos de bean Java com base em uma convenção sobre abordagem de configuração.
* O Lombok é um framework para Java que permite escrever código eliminando a verbosidade, o que permite ganhar tempo de desenvolvimento para o que realmente é importante. Seu uso permite gerar em tempo de compilação os métodos getters e setters, métodos construtores, padrão builder e muito mais.
* Spring Validation é uma especificação Java para tratar validações de dados de forma centralizada, pois as validações são inseridas no próprio modelo através de anotações, assim possibilitando a consistência das informações em diferentes camadas.

## Testes unitário
Foram aplicados testes para os Services, Mapstruct, Validator

## Documentação
* Swagger:  `http://localhost:8080/v1/cadastro-clientes/swagger-ui.html`

## Execução da aplicação

Utilizando o Docker, é possível criar a estrutura de banco de dados e levantar a aplicação seguindo os passos abaixo:

1. Construir a aplicação com Maven executando o comando `mvn clean install`
2. Acessar o diretório principal do projeto (onde encontra-se o arquivo `docker-compose.yaml`)
3. Executar o comando `docker-compose up -d` para iniciar a aplicação
4. Ao concluir o passo anterior, você poderá acessar : http://localhost:8080/v1/cadastro-clientes/swagger-ui.html para verificar a documentação ou testar os end-points da aplicação utilizando a Collection do Postman `postman-cadastro-clientes.postman_collection` que encontra-se no diretório raiz do projeto.

## Link da API no Heroku
https://cadastro-clientes-app.herokuapp.com/v1/cadastro-clientes

## Link da Documentação da API no Heroku
https://cadastro-clientes-app.herokuapp.com/v1/cadastro-clientes/swagger-ui.html
