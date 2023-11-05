# Use the official MySQL image as a base
FROM mysql:8.0

# Copy SQL initialization files
COPY ./sql/init/ /docker-entrypoint-initdb.d/
