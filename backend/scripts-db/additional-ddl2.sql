create table ver_versao (
ver_id bigint generated always as identity primary key,
ver_novo_texto varchar(256) not null,
ver_justificativa varchar(100),
ver_data date not null,
ver_anotacao bigint not null,
ver_nivel_diferenca float,
foreign key (ver_anotacao) references ant_anotacao (ant_id) on delete restrict on update cascade
);
insert into ver_versao (ver_novo_texto, ver_data, ver_anotacao, ver_nivel_diferenca)
values ('Acho que errei', '2026-03-01', 1, 9),
('Estava certo', current_date, 1, null);
grant update, delete, insert, select on all tables in schema public to spring;