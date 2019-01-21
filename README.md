# SistemaDeJogadoresUOL

Requisitos para rodar a aplicação:
  - a porta 8180 deve estar desocupada
  - ter docker instalado
  - ter o docker-compose instalado
  
Como executar:
  - (somente pela 1a vez) a partir da pasta raiz "SistemaDeJogadoresUOL" execute o comando: docker-compose up
      - na primeira vez, caso necessário, o docker irá baixar alguns artefatos para executar a aplicação, isso poderá levar alguns segundos ou minutos
  - para encerrar a aplicação, execute o comando: docker-compose stop
  - para reiniciar a aplicação, execute o comando: docker-compose start
      
Como acessar a aplicação:
  - uma vez carregado todos os artefatos, a aplicação estará acessível em: localhost:8180/cadastro_uol/
  - também publiquei a aplicação na minha conta na AWS: http://ec2-18-231-84-9.sa-east-1.compute.amazonaws.com:8180/cadastro_uol/ 

Descrição técnica:
  - o projeto é composto de dois serviços independentes (ver o docker-compose.yml)
    - o serviço front-end "uol_cadastro" é um projeto maven que roda no WildFly utilizando JSF, PrimeFaces, CDI, JAX-RS Client API
    - o serviço back-end "uol_view_be" é um projeto SpringBoot que recebe, processa e salva as requisições do front de acordo com as regras de negócio. O banco de dados é HSQL
