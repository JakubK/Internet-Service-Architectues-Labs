# Internet-Service-Architectues-Labs

Set of projects I made for lab classes on `Internet Service Architectures` at Gdansk University of Technology. 
Each branch presents next step in development of web application split into decoupled services.

`01-monolit` - Single monolit REST API made in Java Spring

`02-monolit-h2-jpa` - Refactored code and added JPA with H2 InMemory db

`03-microservices` - Split monolit into microservices, one for category management, second for element and last for ApiGateway

`04-frontend` - Add client frontend application made in Vue3 + TS

`05-docker` - Containerize each project and add Nginx ReverseProxy service

`06-uploads` - Add another microservice in Java Spring for handling file uploads and storing them in volume. Every H2 db is replaced with dedicated mysql db container.

`07-load-balancers` - Add extra instance of frontend application and file microservice. ReverseProxy is directly connected with frontend load balancer and ApiGateway with one for Files.

