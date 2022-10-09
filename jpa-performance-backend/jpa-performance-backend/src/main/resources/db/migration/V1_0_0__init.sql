CREATE TABLE customer
(
    id        BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number    INT     NOT NULL,
    firstname VARCHAR NOT NULL,
    lastname  VARCHAR NOT NULL,
    email     VARCHAR NOT NULL
);

CREATE TABLE command
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number      INT  NOT NULL,
    ordered_at  DATE NOT NULL,
    customer_id INT  NOT NULL
);

ALTER TABLE command
    ADD CONSTRAINT command_customer_id_fkey
        FOREIGN KEY (customer_id)
            REFERENCES customer (id);

CREATE TABLE item
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    code       VARCHAR NOT NULL,
    quantity   INT     NOT NULL,
    price      FLOAT   NOT NULL,
    command_id INT     NOT NULL
);

ALTER TABLE item
    ADD CONSTRAINT item_command_id_fkey
        FOREIGN KEY (command_id)
            REFERENCES command (id);
