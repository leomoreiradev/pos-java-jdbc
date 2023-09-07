insert into userposjava (nome,email)
values('maria', 'maria@gmail.com');

insert into telefoneuser (numero,tipo, usuariopessoa)
values('(45)9 8821-2355', 'celular', 9);

select * from userposjava;
select * from telefoneuser;


delete from userposjava;

-- criando sequence no db para que o id seja auto increment
create SEQUENCE usersequence
increment 1
minvalue  1
maxvalue 9223372036854775807
start 7;

-- criando sequence no db para que o id seja auto increment
create SEQUENCE user_telefone_seq
increment 1
minvalue  1
maxvalue 9223372036854775807
start 1;

-- alterando a tabela para usar o sequence criado
alter table userposjava ALTER column id set default nextval('usersequence'::regclass);
alter table telefoneuser ALTER column id set default nextval('user_telefone_seq'::regclass);

alter table userposjava add unique (id);

create table telefoneuser (
id bigint not null,
numero character varying (255) not null,
tipo character varying(255) not null,
usuariopessoa bigint not null,
constraint telefone_id primary key (id)
);

-- criando referencia de uma tabela com a outra
alter table telefoneuser add foreign key (usuariopessoa) references userposjava(id);

-- inner join
select nome, numero, email from telefoneuser as fone
inner join userposjava as userp
on fone.usuariopessoa = userp.id
where userp.id = 9;
