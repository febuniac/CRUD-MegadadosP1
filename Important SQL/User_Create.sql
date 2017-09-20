CREATE USER IF NOT EXISTS 'usuariobill'@'localhost' IDENTIFIED BY '123456';
GRANT CREATE,DELETE,INSERT,SELECT,UPDATE ON billorganizer.* TO 'usuario'@'localhost' ;

#FLUSH PRIVILEGES;    