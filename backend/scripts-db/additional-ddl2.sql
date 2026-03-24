create table cat_catraca (
    cat_id bigint generated always as identity primary key,
    cat_sentido varchar(7) not null,
    cat_metodo varchar(50),
    cat_data_hora timestamp not null,
    cat_tempo int,
    cat_usuario bigint not null,
    foreign key (cat_usuario) references usr_usuario (usr_id) on delete restrict on update cascade
);

insert into cat_catraca (cat_sentido, cat_data_hora, cat_tempo, cat_usuario)
values ('Entrada', '2026-03-23 09:00:00', 15, 1),
       ('Saida', current_timestamp, null, 1);

grant update, delete, insert, select on all tables in schema public to spring;