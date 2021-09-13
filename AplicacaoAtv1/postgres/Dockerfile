#Criando a imagem a partir da imagem do postgresql
FROM postgres
#Criando o banco e o usuário padrão
ENV POSTGRES_DB Atividade1
ENV POSTGRES_USER ads
ENV POSTGRES_PASSWORD 123
#copiando o arquivo scritp.sql para que o banco seja criado já com as tabelas
COPY script.sql /docker-entrypoint-initdb.d/