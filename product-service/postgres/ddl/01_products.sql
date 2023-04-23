\c root

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION root;

    CREATE TABLE IF NOT EXISTS public.products
(
    uuid uuid NOT NULL,
    calories integer,
    carbohydrates double precision,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    fats double precision,
    proteins double precision,
    title character varying(255) COLLATE pg_catalog."default",
    weight integer,
    CONSTRAINT products_pkey PRIMARY KEY (uuid),
    CONSTRAINT title_uniue UNIQUE (title)
);


