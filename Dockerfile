FROM openjdk:21

# Baixe o PostgreSQL e extraia os binários
RUN wget --quiet -O /tmp/postgresql_key https://www.postgresql.org/media/keys/ACCC4CF8.asc

# Adicione a chave GPG
RUN apt-key add /tmp/postgresql_key

# Adicione o repositório do PostgreSQL às fontes do apt
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list

# Atualize os pacotes e instale o cliente PostgreSQL
RUN apt-get update && apt-get install -y postgresql-client-common postgresql-client

# Limpeza de arquivos temporários
RUN rm -rf /tmp/postgresql_key

WORKDIR /app

COPY pom.xml ./

RUN mvn -f pom.xml clean install

COPY target/*.jar ./

# Configure Spring Boot application properties
RUN echo "spring.datasource.url=jdbc:postgresql://localhost:5432/sistemacatalogo" > application.properties
RUN echo "spring.datasource.username=postgres" >> application.properties
RUN echo "spring.datasource.password=postgres" >> application.properties

# Enable JPA and configure properties
RUN echo "spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true" >> application.properties
RUN echo "spring.jpa.hibernate.ddl-auto=update" >> application.properties
RUN echo "spring.jpa.show-sql=true" >> application.properties
RUN echo "spring.jpa.properties.hibernate.format_sql=true" >> application.properties

CMD ["java", "-jar", "*.jar"]

EXPOSE 8080