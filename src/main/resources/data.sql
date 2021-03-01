
insert into aluno ( cpf, email, endereco_bairro, endereco_cep, endereco_cidade, endereco_complemento, endereco_logradouro, endereco_numero, endereco_uf, matricula, nome) values ( '18859917891', 'carlos@gama.com', 'artur alvim', '03590170','SAO PAULO', '', 'Rua Irmão Nicolau', '169', 'SP', '20210001', 'Carlos Jose');
insert into aluno (cpf, email, endereco_bairro, endereco_cep, endereco_cidade, endereco_complemento, endereco_logradouro, endereco_numero, endereco_uf, matricula, nome) values ( '64218188467', 'alex@gama.com', 'Jardim Ipanema (Cidade Líder)', '03582100','SAO PAULO', '', 'Rua Quinta da Magnólia', '2369', 'SP', '20210002', 'Alex Mota Brito');
insert into aluno ( cpf, email, endereco_bairro, endereco_cep, endereco_cidade, endereco_complemento, endereco_logradouro, endereco_numero, endereco_uf, matricula, nome) values ( '68352452126', 'joao@gama.com', 'artur alvim', '03590090','SAO PAULO', '13B', 'Rua Padre Estevão de Oliveira', '15', 'SP', '20210003', 'Joao da Silva');

insert into curso (id, curso, codigo) values (1, 'ENGENHARIA DE COMPUTAÇÃO', 'ENCO');
insert into curso (id, curso, codigo) values (2, 'ENGENHARIA DE PRODUÇÃO', 'ENPO');
insert into curso (id, curso, codigo) values (3, 'MATEMATICA', 'MATE');
insert into curso (id, curso, codigo) values (4, 'SOCIOLOGIA', 'SOCI');

insert into aluno_cursos (aluno_id, cursos_id) values (1, 1);
insert into aluno_cursos (aluno_id, cursos_id) values (2, 2);
insert into aluno_cursos (aluno_id, cursos_id) values (3, 1);

insert into disciplina (id, codigo, conceito, disciplina) values (1, 'MM01', 'Matematica basica', 'MATEMATICA I');
insert into disciplina (id, codigo, conceito, disciplina) values (2, 'PT01', 'PORTUGUES basico', 'PORTUGUES I');
insert into disciplina (id, codigo, conceito, disciplina) values (3, 'PT02', 'PORTUGUES Intermediario', 'PORTUGUES II');
insert into disciplina (id, codigo, conceito, disciplina) values (4, 'FS03', 'Fisica nuclear', 'FISICA III');

insert into curso_disciplinas (curso_id, disciplinas_id) values (1, 1);
insert into curso_disciplinas (curso_id, disciplinas_id) values (1, 2);
insert into curso_disciplinas (curso_id, disciplinas_id) values (2, 3);
insert into curso_disciplinas (curso_id, disciplinas_id) values (1, 4);
insert into curso_disciplinas (curso_id, disciplinas_id) values (2, 4);


insert into notas (tipo_nota, valor_nota) values ('P1', 5);
insert into notas (tipo_nota, valor_nota) values ('P2', 8);


insert into notas_disciplinas (notas_id,disciplinas_id ) values (1, 1);
insert into notas_disciplinas (notas_id, disciplinas_id) values (2, 1);

insert into aluno_notas (aluno_id, notas_id) values (1, 2);
insert into aluno_notas (aluno_id, notas_id) values (1, 1);

