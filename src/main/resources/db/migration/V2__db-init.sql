
INSERT INTO "user" (id, nome, login, senha)
VALUES (1, 'Pedro Henrique', 'pedr', '123');

INSERT INTO "role" (id, descricao)
VALUES (1, 'USER');

INSERT INTO "role" (id, descricao)
VALUES (2, 'ADMIN');

INSERT INTO "authorization" (id, role_id, user_id)
VALUES (1,1,1);

INSERT INTO "authorization" (id, role_id, user_id)
VALUES (2,2,1);
