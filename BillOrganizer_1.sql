DROP DATABASE IF EXISTS billorganizer;
CREATE DATABASE billorganizer;
USE billorganizer;

CREATE TABLE conta (
id int,
entidadeEmissora varchar(45),
dataVencimento date,
status bit(1),
categoria varchar(45),
pasta varchar(45),
gaveta int,
armario int,
usuario varchar(45),
codigoDeBarras bigint,
pasta_idPasta int,
emissor_cnpj varchar(45),
PRIMARY KEY (id)
);

CREATE TABLE usuario_conta (
usuario_id varchar(45),
conta_id int,
primary key(conta_id, usuario_id)
);

create table usuario (
id varchar(45),
RG varchar(45),
emissor varchar(45),
cpf varchar(45),
nome varchar(45),
primary key (id)
);

alter table usuario_conta 
	add foreign key ( usuario_id)
		references usuario (id);
        
alter table usuario_conta 
	add foreign key (conta_id)
		references conta (id);

create table emissor (
id int,
cnpj bigint,
nome varchar(45),
endereco varchar(80),
categoria varchar(45)
);



create table pasta (
id int,
gaveta_id int
);



create table gaveta(
id int,
armario_id int
);



create table armario (
id int
);

