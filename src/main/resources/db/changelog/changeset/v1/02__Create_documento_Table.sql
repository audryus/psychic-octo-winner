--liquibase formatted sql

--changeset audryus:desafio-02 labels:documento
--comment: create table documento
CREATE TABLE documento (
    ID SERIAL PRIMARY KEY,
    BENEFICIARIO SERIAL,
    TIPO varchar(50),
    DESCRICAO varchar(120),
    TS_INCLUSAO timestamp not null default CURRENT_TIMESTAMP,
	TS_ATUALIZACAO timestamp not null default CURRENT_TIMESTAMP,
    constraint fk_beneficiario foreign key (beneficiario) references beneficiario(id)
);

create index idx_beneficiario on documento(beneficiario);