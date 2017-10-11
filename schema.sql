create table if not exists categoria (
	codigo BIGINT(20) identity primary key,
	nome varchar(50) NOT NULL
);

create table if not exists endereco (

	codigo BIGINT(20) identity PRIMARY KEY ,
	ativo char(1),
	logradouro varchar(50),
	numero varchar(5),
	complemento varchar(50),
	bairro varchar(50),
	cep varchar(15),
	cidade varchar(50),
	estado varchar(30)

);

create table if not exists pessoa(

	codigo BIGINT(20) identity PRIMARY KEY ,
	nome varchar(50) not null,
	ativo char(1),
	id_endereco BIGINT(20),
	foreign key(id_endereco) references endereco(codigo)

);