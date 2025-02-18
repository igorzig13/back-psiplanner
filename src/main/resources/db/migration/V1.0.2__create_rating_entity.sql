CREATE TABLE ratings
(
    rating_id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    value           INTEGER                                 NOT NULL,
    comment         VARCHAR(255),
    client_id       BIGINT                                  NOT NULL,
    professional_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_ratings PRIMARY KEY (rating_id)
);

ALTER TABLE ratings
    ADD CONSTRAINT FK_RATINGS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (client_id);

ALTER TABLE ratings
    ADD CONSTRAINT FK_RATINGS_ON_PROFESSIONAL FOREIGN KEY (professional_id) REFERENCES professionals (professional_id);