\c root

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS public.recipe_ingredient
(
    recipe_uuid uuid NOT NULL,
    ingredient_id bigint NOT NULL,
    CONSTRAINT recipe_uuid_foreigh FOREIGN KEY (recipe_uuid)
        REFERENCES public.recipes (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ingredient_id_foreigh FOREIGN KEY (ingredient_id)
        REFERENCES public.ingredients (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


