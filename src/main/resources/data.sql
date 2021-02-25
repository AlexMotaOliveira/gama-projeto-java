
insert into aluno (id, cpf, email, endereco_bairro, endereco_cep, endereco_cidade, endereco_complemento, endereco_logradouro, endereco_numero, endereco_uf, matricula, nome) values (null, '33633985817', 'alex@gama.com', 'artur alvim', '03590170','SAO PAULO', '13B', 'Rua Irmão Nicolau', '169', 'SP', '202110', 'Alex Mota Brito');

insert into curso (id, curso) values (1, 'ENGENHARIA DE COMPUTAÇÃO');
insert into curso (id, curso) values (2, 'ENGENHARIA DE PRODUÇÃO');
insert into aluno_cursos (aluno_id, cursos_id) values (1, 1);

insert into disciplina (id, codigo, conceito, disciplina) values (1, 'MM01', 'Matematica basica', 'MATEMATICA I');
insert into disciplina (id, codigo, conceito, disciplina) values (2, 'PT01', 'PORTUGUES basico', 'PORTUGUES I');
insert into disciplina (id, codigo, conceito, disciplina) values (3, 'PT02', 'PORTUGUES Intermediario', 'PORTUGUES II');
insert into disciplina (id, codigo, conceito, disciplina) values (4, 'FS03', 'Fisica nuclear', 'FISICA III');
insert into curso_disciplinas (curso_id, disciplinas_id) values (1, 1);
insert into curso_disciplinas (curso_id, disciplinas_id) values (1, 2);
insert into curso_disciplinas (curso_id, disciplinas_id) values (2, 3);
insert into curso_disciplinas (curso_id, disciplinas_id) values (1, 4);
