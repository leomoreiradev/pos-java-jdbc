insert into userposjava (id,nome,email)
values(2,'joao', 'joao@gmail.com');

select * from userposjava;

-- criando sequence no db para que o id seja auto increment
create SEQUENCE usersequence
increment 1
minvalue  1
maxvalue 9223372036854775807
start 7;

-- alterando a tabela para usar o sequence criado
alter table userposjava ALTER column id set default nextval('usersequence'::regclass);

alter table userposjava add unique (id);

create table telefoneuser (
id bigint not null,
numero character varying (255) not null,
tipo character varying(255) not null,
usuariopessoa bigint not null,
constraint telefone_id primary key (id)
);