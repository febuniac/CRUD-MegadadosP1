DROP DATABASE IF EXISTS billorganizer;
CREATE DATABASE billorganizer;
USE billorganizer;

CREATE TABLE conta (
entidadeEmissora varchar(45),
dataVencimento date,
status bit(1),
categoria varchar(45),
pasta varchar(45),
gaveta int,
armario int,
usuario varchar(45),
codigoDeBarras int,
pasta_idPasta int,
emissor_cnpj int,
PRIMARY KEY (entidadeEmissora, dataVencimento, codigoDeBarras)
);

CREATE TABLE usuario_conta (
usuario_RG int,
usuario_emissor varchar(45),
usuario_cpf int,
conta_entidadeEmissora varchar(45),
conta_dataVencimento date,
primary key(usuario_RG, usuario_emissor, usuario_cpf, 
conta_entidadeEmissora, conta_dataVencimento)
);

create table usuario (
RG int,
emissor varchar(45),
cpf int,
nome varchar(45),
primary key (RG, emissor, cpf)
);

alter table usuario_conta 
	add foreign key ( usuario_RG, usuario_emissor, usuario_cpf)
		references usuario (rg, emissor, cpf);
        
alter table usuario_conta 
	add foreign key (conta_entidadeEmissora, conta_dataVencimento)
		references conta (entidadeEmissora, dataVencimento);

create table emissor (
cnpj int,
nome varchar(45),
endereco varchar(80),
categoria varchar(45)
);



create table pasta (
idPasta int,armario
gaveta_idGaveta int
);



create table gaveta(
idGaveta int,
armario_idArmario int
);



create table armario (
idArmario int
);

