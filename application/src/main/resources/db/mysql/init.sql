CREATE USER 'colamooon'@'%' IDENTIFIED WITH mysql_native_password BY 'colamooon@!local';
CREATE DATABASE colamooon;

GRANT ALL ON colamooon.* TO 'colamooon'@'%';

CREATE DATABASE colamooon_test;
GRANT ALL ON colamooon_test.* TO 'colamooon'@'%';

FLUSH PRIVILEGES;