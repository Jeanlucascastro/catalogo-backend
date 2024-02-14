FROM openjdk:17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn clean install

COPY target/*.jar .

FROM postgres:14.4

WORKDIR /var/lib/postgresql/data

COPY --from=builder /app/target/*.jar .

ENV POSTGRES_DB=sistemacatalogo
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

RUN echo "host all all 0.0.0.0/0 md5" >> pg_hba.conf
RUN echo "listen_addresses = '*'" >> postgresql.conf

EXPOSE 5432

CMD ["docker-entrypoint.sh", "postgres"]
