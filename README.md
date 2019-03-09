# mapeamento-estrategico

Pré-requisitos:
---------------
1 - Ter o Docker instalado.

Executando exemplo:
-------------------
1 - git clone https://github.com/giovanasilveiracardoso/mapeamento-estrategico.git

2 - cd mapeamento-estrategico

3 - Construir o container através do comando abaixo:

   ``$ docker build -t giovanacardoso/mapeamento-estrategico .``

4 - Iniciar o container através do comando abaixo:
	``$ docker run -p 8080:8080 --rm -it giovanacardoso/mapeamento-estrategico``

5 - Acessar através do browser:
	http://localhost:8080/
	
6 - Acessar api que retorna as visitas que devem ser efetuadas pelos representantes:
	http://localhost:8080/visita
