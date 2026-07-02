CREATE TABLE usuarios(
    id BIGSERIAL primary key ,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(250) NOT NULL
);