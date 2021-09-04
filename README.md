# Product-ms
Projeto em Spring Boot de uma API de Catálogo de Produtos

## Detalhes do projeto

Documentação com os endpoints de API disponíveis em: http://localhost:9999/swagger-ui.html

## Base de dados

Foi utilizado o banco H2, um banco em memória. Assim sendo as informações salvas são perdidas assim que a 
aplicação for reiniciada.

## Execução da API

1° Baixar o projeto do github indo **Code -> Download ZIP**.

2° Baixar as dependências do projeto com comando no CMD (Prompt de Comando) na pasta onde está o projeto executar:\
**mvn clean install**

3° Subir a aplicação executando o comando no CMD:\
**mvn spring-boot:run**

4° Acessa os endpoint's na url base:
http://localhost:9999/products
