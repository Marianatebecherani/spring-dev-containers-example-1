create table aud_auditoria (
	aud_id bigint generated always as identity,
	aud_nome_antigo varchar(20) not null,
	aud_nome_novo varchar(20) not null,
	aud_data_hora timestamp not null,
	aud_autorizacao bigint not null,
	aud_risco int,
	foreign key (aud_autorizacao) references aut_autorizacao (aut_id)
);

insert into aud_auditoria (aud_nome_antigo, aud_nome_novo, aud_data_hora, aud_autorizacao, aud_risco)
	values ('ROLE_ROOT', 'ROLE_USER', '2026-03-03 10:00:00', 1, 0.73),
		('ROLE_USER', 'ROLE_ADMIN', current_timestamp, 1, null);

grant update, delete, insert, select on all tables in schema public to spring;