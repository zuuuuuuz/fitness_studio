\c root

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS public.ingredients
(
    id bigint NOT NULL,
    weight integer,
    product_uuid uuid,
    CONSTRAINT ingredients_pkey PRIMARY KEY (id),
    CONSTRAINT product_uuid_foreigh FOREIGN KEY (product_uuid)
        REFERENCES public.products (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


