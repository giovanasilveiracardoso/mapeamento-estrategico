# Mapeamento Estratégico

Este projeto foi criado para definir quais lojas cada representante deve visitar com base na distância de no máximo 2 km de suas residências. Uma regra adicionada foi que uma loja não pode ser visitada por mais de um representante.

Este projeto utiliza o banco de dados H2. O H2 é um banco de dados Open Source que funciona em memória. Os dados retornados por ele se encontram em /src/main/resources/data.sql

Pré-requisitos:
---------------
1 - Ter o Docker instalado.

Executando exemplo:
-------------------
1 - git clone https://github.com/giovanasilveiracardoso/mapeamento-estrategico.git

2 - cd mapeamento-estrategico

3 - Construir o container através do comando abaixo (Os testes já rodam nesta etapa):

``$ docker build -t giovanacardoso/mapeamento-estrategico .``

4 - Iniciar o container através do comando abaixo:

``$ docker run -p 8080:8080 --rm -it giovanacardoso/mapeamento-estrategico``

5 - Acesse através do browser:
	http://localhost:8080/
	
6 - Api que retorna as visitas que devem ser efetuadas pelos representantes:
	http://localhost:8080/visita
