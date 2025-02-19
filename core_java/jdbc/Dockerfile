FROM openjdk:11-jdk-slim
WORKDIR /usr/local/app/stockquote
COPY target/jdbc*.jar stockquote.jar
COPY target/dependency/ lib/
ENTRYPOINT ["java", "-cp", "stockquote.jar:lib/*", "ca.jrvs.apps.stockquote.Main"]
