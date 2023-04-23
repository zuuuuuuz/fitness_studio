\c productsDB

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION "user";

    CREATE TABLE IF NOT EXISTS public.products
(
    uuid uuid NOT NULL,
    calories integer NOT NULL,
    carbohydrates double precision NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    fats double precision NOT NULL,
    proteins double precision NOT NULL,
    title character varying(255) NOT NULL,
    weight integer NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (uuid),
    CONSTRAINT title_uniue UNIQUE (title)
);


