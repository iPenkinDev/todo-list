FROM tomcat:9.0.67
COPY ./target/TodoList.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]