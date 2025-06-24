CREATE TABLE comment
(
    id UUID NOT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE image
(
    id               UUID         NOT NULL,
    url              VARCHAR(255) NOT NULL,
    type             SMALLINT     NOT NULL,
    establishment_id UUID,
    CONSTRAINT pk_image PRIMARY KEY (id)
);

CREATE TABLE post
(
    id                   UUID             NOT NULL,
    dtype                VARCHAR(31),
    description          VARCHAR(255),
    price                DOUBLE PRECISION NOT NULL,
    rating               DOUBLE PRECISION NOT NULL,
    is_delivery          BOOLEAN          NOT NULL,
    establishment_rating DOUBLE PRECISION,
    service_rating       DOUBLE PRECISION,
    delivery_rating      DOUBLE PRECISION,
    CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id                   UUID         NOT NULL,
    dtype                VARCHAR(31),
    email                VARCHAR(255),
    phone_number         VARCHAR(255),
    username             VARCHAR(255),
    password             VARCHAR(255) NOT NULL,
    profile_pic_url      VARCHAR(255),
    is_account_confirmed BOOLEAN,
    confirmation_code    VARCHAR(255),
    bio                  VARCHAR(255),
    cnpj                 VARCHAR(255) NOT NULL,
    type                 SMALLINT     NOT NULL,
    is_delivery          BOOLEAN      NOT NULL,
    is_in_person         BOOLEAN      NOT NULL,
    status               SMALLINT     NOT NULL,
    rejections           INTEGER,
    cep                  VARCHAR(255),
    city                 VARCHAR(255),
    state                VARCHAR(255),
    street               VARCHAR(255),
    number               VARCHAR(255),
    neighborhood         VARCHAR(255),
    country              VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_activity
(
    id               UUID     NOT NULL,
    user_id          UUID,
    entity_id        UUID     NOT NULL,
    action_type      SMALLINT NOT NULL,
    entity_type      SMALLINT NOT NULL,
    timestamp        INTEGER  NOT NULL,
    engagement_score FLOAT    NOT NULL,
    context          BYTEA,
    CONSTRAINT pk_useractivity PRIMARY KEY (id)
);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_phonenumber UNIQUE (phone_number);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE image
    ADD CONSTRAINT FK_IMAGE_ON_ESTABLISHMENT FOREIGN KEY (establishment_id) REFERENCES "user" (id);

ALTER TABLE user_activity
    ADD CONSTRAINT FK_USERACTIVITY_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);