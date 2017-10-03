create table if not exists categoria (
	codigo BIGINT(20) identity primary key,
	nome varchar(50) NOT NULL
)