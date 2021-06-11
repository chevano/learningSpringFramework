CREATE USER 'learningSpringFramework'@'localhost' IDENTIFIED BY 'learningSpringFramework';

GRANT ALL PRIVILEGES ON * . * TO 'learningSpringFramework'@'localhost';

ALTER USER 'learningSpringFramework'@'localhost' IDENTIFIED WITH mysql_native_password BY 'learningSpringFramework';
