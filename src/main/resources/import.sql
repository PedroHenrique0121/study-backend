-- INSERT INTO "user" (id, nome, login, senha)
-- values (1,'Pedro Henrique', 'login2','senha');
-- INSERT INTO role (id, descricao)
-- VALUES(1,'ADMIN');
INSERT INTO role (id, descricao)
VALUES(2,'SUPER_ADMIN');
-- INSERT INTO "authorization" (id,user_id, role_id)
-- VALUES(1,1,1);
INSERT INTO "authorization" (id,user_id, role_id)
VALUES(2,1,2);

