create table categoria (
	codigo BIGINT(20) primary key AUTO_INCREMENT,
	nome varchar(50) NOT NULL
)ENGINE=InnoDB default CHARSET=utf8;

insert into categoria (nome) values ('Lazer');
insert into categoria (nome) values ('Alimentação');
insert into categoria (nome) values ('Supermercado');
insert into categoria (nome) values ('Farmácia');
insert into categoria (nome) values ('Carro');
insert into categoria (nome) values ('Outros');