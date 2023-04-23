\c root

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS public.recipes
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT recipes_pkey PRIMARY KEY (uuid)
);


