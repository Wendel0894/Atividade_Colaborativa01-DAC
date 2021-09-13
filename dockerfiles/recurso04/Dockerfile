#Criando a imagem a partir da imagem do postgresql
FROM postgres
#Criando o banco e o usuário padrão
ENV POSTGRES_DB Atividade1
ENV POSTGRES_USER ads
ENV POSTGRES_PASSWORD 123
#Criando diretório compartilhado
VOLUME ["/var/lib/postgresql/data"]
