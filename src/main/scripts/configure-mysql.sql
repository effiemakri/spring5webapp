## Use to run mysql db docker image, optional if you're using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
# create databases:
CREATE DATABASE telint_dev;
CREATE DATABASE telint_prod;

# create database service accounts
CREATE USER 'telint_dev_user'@'localhost' IDENTIFIED BY 'telint';
CREATE USER 'telint_prod_user'@'localhost' IDENTIFIED BY 'telint';
# create users for any host (in addition to localhost)
CREATE USER 'telint_dev_user'@'%' IDENTIFIED BY 'telint';
CREATE USER 'telint_prod_user'@'%' IDENTIFIED BY 'telint';

# database grants:
GRANT SELECT ON telint_dev.* TO 'telint_dev_user'@'localhost';
GRANT INSERT ON telint_dev.* TO 'telint_dev_user'@'localhost';
GRANT DELETE ON telint_dev.* TO 'telint_dev_user'@'localhost';
GRANT UPDATE ON telint_dev.* TO 'telint_dev_user'@'localhost';
GRANT SELECT ON telint_dev.* TO 'telint_prod_user'@'localhost';
GRANT INSERT ON telint_dev.* TO 'telint_prod_user'@'localhost';
GRANT DELETE ON telint_dev.* TO 'telint_prod_user'@'localhost';
GRANT UPDATE ON telint_dev.* TO 'telint_prod_user'@'localhost';
GRANT SELECT ON telint_dev.* TO 'telint_dev_user'@'%';
GRANT INSERT ON telint_dev.* TO 'telint_dev_user'@'%';
GRANT DELETE ON telint_dev.* TO 'telint_dev_user'@'%';
GRANT UPDATE ON telint_dev.* TO 'telint_dev_user'@'%';
GRANT SELECT ON telint_dev.* TO 'telint_prod_user'@'%';
GRANT INSERT ON telint_dev.* TO 'telint_prod_user'@'%';
GRANT DELETE ON telint_dev.* TO 'telint_prod_user'@'%';
GRANT UPDATE ON telint_dev.* TO 'telint_prod_user'@'%';