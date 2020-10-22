-- Table: public.guest

-- DROP TABLE public.guest;

CREATE TABLE public.guest
(
    email text COLLATE pg_catalog."default",
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name text COLLATE pg_catalog."default" NOT NULL,
    notes text COLLATE pg_catalog."default",
    CONSTRAINT guest_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.guest
    OWNER to postgres;