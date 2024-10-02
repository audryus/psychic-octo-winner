--liquibase formatted sql

--changeset audryus:desafio-01 labels:beneficiario
--comment: create table beneficiario
CREATE TABLE beneficiario (
	ID SERIAL PRIMARY KEY,
	NOME varchar(255),
	TELEFONE varchar(26),
	DT_NASCIMENTO date,
	TS_INCLUSAO timestamp not null default CURRENT_TIMESTAMP,
	TS_ATUALIZACAO timestamp not null default CURRENT_TIMESTAMP
);
