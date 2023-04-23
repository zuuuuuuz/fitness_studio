\c root

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS public.users
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    fio character varying(255) COLLATE pg_catalog."default",
    mail character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    role smallint,
    status smallint,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT mail_unique UNIQUE (mail)
);

