\c usersDB

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION "user";

CREATE TABLE IF NOT EXISTS public.roles
(
    role character varying(255) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role)
);

CREATE TABLE IF NOT EXISTS public.status
(
    status character varying(255) NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (status)
);

CREATE TABLE IF NOT EXISTS public.users
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    fio character varying(255) NOT NULL,
    mail character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT mail_unique UNIQUE (mail),
    CONSTRAINT user_status FOREIGN KEY (status)
        REFERENCES public.status (status) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role FOREIGN KEY (role)
        REFERENCES public.roles (role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

