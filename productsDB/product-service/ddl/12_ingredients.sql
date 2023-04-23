\c productsDB

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION "user";

CREATE TABLE IF NOT EXISTS public.ingredients
(
    id bigint NOT NULL,
    weight integer NOT NULL,
    product_uuid uuid NOT NULL,
    CONSTRAINT ingredients_pkey PRIMARY KEY (id),
    CONSTRAINT product_uuid_foreigh FOREIGN KEY (product_uuid)
        REFERENCES public.products (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


