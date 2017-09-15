create table endereco(

	codigo BIGINT(20) PRIMARY KEY auto_increment,
	ativo char(1),
	logradouro varchar(50),
	numero varchar(5),
	complemento varchar(50),
	bairro varchar(50),
	cep varchar(15),
	cidade varchar(50),
	estado varchar(30)

)engine=InnoDB default charset=utf8;


create table pessoa(

	codigo BIGINT(20) PRIMARY KEY auto_increment,
	nome varchar(50) not null,
	ativo char(1),
	id_endereco BIGINT(20),
	foreign key(id_endereco) references endereco(codigo)

)engine=InnoDB default charset=utf8;