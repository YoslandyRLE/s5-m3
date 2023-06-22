

/* ************************* */
/* Criação de Banco de Dados */
/* ************************* */
CREATE DATABASE ecommerce;

/* ****************** */
/* Criação de Tabelas */
/* ****************** */
CREATE TABLE cliente (
  id bigserial PRIMARY KEY,
  nome varchar(255) NOT NULL,
  documento varchar(18) NOT NULL,
  email varchar(100) NOT NULL,
  telefone varchar(16) NOT NULL,
  cep varchar(10) NOT NULL,
  logradouro varchar(120) NOT NULL,
  numero int NOT NULL,
  complemento varchar(20),
  bairro varchar(30) NOT NULL,
  cidade varchar(50) NOT NULL,
  uf char(2) NOT NULL
);

CREATE TABLE produto (
  id bigserial PRIMARY KEY,
  nome varchar(255) NOT NULL,
  descricao text,
  categoria varchar(30) NOT NULL,
  valor numeric(19, 2) NOT NULL,
  marca varchar(20) NOT NULL,
  cor varchar(10) NOT NULL,
  tamanho varchar(10) NOT NULL
);

CREATE TABLE pedido (
  id bigserial PRIMARY KEY,
  data_pedido timestamp NOT NULL DEFAULT now(),
  data_pagamento timestamp NOT NULL,
  cliente_id bigint NOT NULL,
  status int NOT NULL DEFAULT 1,
  total_itens  numeric(19, 2) NOT NULL,
  total_frete  numeric(19, 2) NOT NULL,
  total_pedido numeric(19, 2) NOT NULL,
  CONSTRAINT pedido_cliente_id_fkey
  FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE pedido_item (
  id bigserial PRIMARY KEY,
  pedido_id bigint NOT NULL REFERENCES pedido(id),
  produto_id bigint NOT NULL REFERENCES produto(id),
  quantidade int NOT NULL,
  valor numeric(19, 2) NOT NULL,
  total_item numeric(19, 2) NOT NULL
);

ALTER TABLE pedido ALTER COLUMN data_pagamento DROP NOT NULL;

CREATE TABLE despensa (
	id bigserial PRIMARY KEY,
	credor varchar(255) NOT NULL,
	data_vencimento timestamp NOT NULL,
    data_pagamento timestamp NOT NULL,
	valor numeric(19, 2) NOT NULL,
	descricao text,
	status int NOT NULL DEFAULT 1
);
