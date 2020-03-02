FROM ubuntu:18.04

#Install Open JDK 8
RUN apt-get update \
    && apt-get -y install openjdk-8-jdk \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the main application.
COPY ml_simian_analysis /opt/simian_analysis/
WORKDIR /opt/simian_analysis/

RUN mvn clean install -DskipTests

ADD analysis_config.yml /opt/simian_analysis/config.yml