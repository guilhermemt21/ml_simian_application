FROM mysql:5.7
# Add a database
ENV MYSQL_DATABASE ml_simian
ENV MYSQL_ALLOW_EMPTY_PASSWORD true