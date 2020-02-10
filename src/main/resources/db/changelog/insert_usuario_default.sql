--liquibase formatted sql
--changeset andre.bongiolo:1
INSERT INTO USUARIOS( ID, NOME, SOBRENOME, SENHA,EMAIL)
  VALUES ((SELECT NEXTVAL('SEQ_USUARIOS')),'Andre','Bongiolo','$2a$10$E/3M3knmOxeaTjINE.9aOe9EvTdEuZ7BdM7E4uLMLZFW90IRyNVmi', 'andrejp09@gmail.com');
--rollback not required

