CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE public.article
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    price character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT article_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.article
    OWNER to admin;

CREATE TABLE public.article_shop_user
(
    id bigint NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT article_shop_user_pkey PRIMARY KEY (id),
    CONSTRAINT uk_b5ord78kqgc3mgevb8t0r0e9v UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE public.article_shop_user
    OWNER to admin;

CREATE TABLE public.buys
(
    id bigint NOT NULL,
    article_id bigint,
    user_id bigint,
    CONSTRAINT buys_pkey PRIMARY KEY (id),
    CONSTRAINT fk739fdpiuxd851nl4r0ev5xcj8 FOREIGN KEY (user_id)
        REFERENCES public.article_shop_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkirrd7sy7533laqj2v658ffals FOREIGN KEY (article_id)
        REFERENCES public.article (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.buys
    OWNER to admin;

INSERT INTO public.article_shop_user (id, firstname, lastname, password, user_role, username)
VALUES (1, 'Simon', 'Ruckli', crypt('secret', gen_salt('bf', 8)), 'Admin', 'deMetEmLange');