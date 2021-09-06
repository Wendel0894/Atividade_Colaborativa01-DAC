FROM openjdk:11
WORKDIR /root/recurso02
#Copiando a classe para dentro do container
COPY Principal.java /root/recurso02 
#Compilando a classe
RUN javac Principal.java
#Gerando o .jar
RUN jar cvf app.jar Principal.class
#Criando um arquivo para tornar o .jar executavel : )
RUN touch MANIFEST.MF
#Definindo a Main-Class
RUN echo "Main-Class: Principal" > ./MANIFEST.MF
#Atualizando o .jar para que ele se torne executável
RUN jar cvmf MANIFEST.MF app.jar Principal.class
#Executando o .jar
CMD ["java", "-jar", "app.jar"]