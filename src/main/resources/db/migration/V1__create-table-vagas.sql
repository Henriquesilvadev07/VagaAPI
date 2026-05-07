CREATE TABLE vagas(
    id BIGSERIAL primary key,
    titulo TEXT NOT NULL,
    empresa TEXT NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    modalidade VARCHAR(20),
    status VARCHAR(20)
);