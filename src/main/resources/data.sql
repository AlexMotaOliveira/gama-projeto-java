
insert into aluno (id, cpf, email, endereco_bairro, endereco_cep, endereco_cidade, endereco_complemento, endereco_logradouro, endereco_numero, endereco_uf, matricula, nome) values (null, '33633985817', 'alex@gama.com', 'artur alvim', '03590170','SAO PAULO', '13B', 'Rua Irmão Nicolau', '169', 'SP', '202110', 'Alex Mota Brito');

insert into curso (id, curso, codigo, conceito, disciplina) values (1, 'ENGENHARIA', 'MM01', 'Matematica basica', 'MATEMATICA');

insert into aluno_cursos (aluno_id, cursos_id) values (1, 1);

