
CREATE TYPE public."e_gender" AS ENUM (
	'MALE',
	'FEMALE',
	'OTHER');

CREATE TYPE public."e_user_status" AS ENUM (
	'ACTIVE',
	'INACTIVE',
	'NONE');


CREATE TYPE public."e_user_type" AS ENUM (
	'OWNER',
	'ADMIN',
	'USER');

CREATE TABLE public.tbl_user
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255)  NOT NULL,
    date_of_birth DATE          NOT NULL,
    gender        e_gender      NOT NULL,
    phone         VARCHAR(255),
    email         VARCHAR(255),
    username      VARCHAR(255)  NOT NULL,
    password      VARCHAR(255)  NOT NULL,
    type          e_user_type   NOT NULL,
    status        e_user_status NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255)
);



CREATE TABLE public.tbl_address
(
    id               BIGSERIAL PRIMARY KEY,
    apartment_number VARCHAR(255),
    floor            VARCHAR(255),
    building         VARCHAR(255),
    street_number    VARCHAR(255),
    street           VARCHAR(255),
    city             VARCHAR(255),
    country          VARCHAR(255),
    address_type     INT,
    user_id          BIGINT REFERENCES public.tbl_user (id) ON DELETE CASCADE,
    created_at       TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at       TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    created_by       VARCHAR(255),
    updated_by       VARCHAR(255)
);

