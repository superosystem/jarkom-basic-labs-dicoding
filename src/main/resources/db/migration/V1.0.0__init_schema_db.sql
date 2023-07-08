CREATE TABLE public."user" (
    id uuid NOT NULL,
    avatar text,
    date_created timestamp(6) with time zone NOT NULL,
    email character varying(100) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    name character varying(150) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50) NOT NULL,
    authentication_id uuid
);
ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email);
ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_bb2dht0cjfqusr6rew4or0qvb UNIQUE (authentication_id);


CREATE TABLE public.authentication (
    id uuid NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    expired_at timestamp(6) without time zone NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    token character varying(255) NOT NULL
);
ALTER TABLE ONLY public.authentication
    ADD CONSTRAINT authentication_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fki1ehwiv4j9pf13pug7ggy3fhq FOREIGN KEY (authentication_id) REFERENCES public.authentication(id);


CREATE TABLE public.ktp (
    id uuid NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    image_url character varying(255) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    ktp_result_id uuid,
    user_id uuid
);
ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT ktp_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT uk_5t75t7v9xeso9v8dwnxn0iuf9 UNIQUE (ktp_result_id);
ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT fkdn8cj59cnyr8t4vv8fav07c74 FOREIGN KEY (user_id) REFERENCES public."user"(id);

CREATE TABLE public.ktp_result (
    id uuid NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    married character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    nationality character varying(255) NOT NULL,
    nik bigint NOT NULL,
    sex character varying(20) NOT NULL,
    title character varying(5) NOT NULL
);
ALTER TABLE ONLY public.ktp_result
    ADD CONSTRAINT ktp_result_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT fkjxgfaysw3hydxp8fkjpu0725o FOREIGN KEY (ktp_result_id) REFERENCES public.ktp_result(id);


CREATE TABLE public.airline (
    id uuid NOT NULL,
    airline character varying(255) NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    icon character varying(255) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL
);
ALTER TABLE ONLY public.airline
    ADD CONSTRAINT airline_pkey PRIMARY KEY (id);


CREATE TABLE public.flight (
    id uuid NOT NULL,
    arrival_time timestamp(6) with time zone NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    depart_time timestamp(6) with time zone NOT NULL,
    departure character varying(50) NOT NULL,
    destination character varying(50) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    price numeric(10,2) NOT NULL,
    airline_id uuid
);
ALTER TABLE ONLY public.flight
    ADD CONSTRAINT flight_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.flight
    ADD CONSTRAINT fks1ubb88l7vhfx02immim7f5ha FOREIGN KEY (airline_id) REFERENCES public.airline(id);

CREATE TABLE public.booking (
    id uuid NOT NULL,
    booking_code character varying(100) NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    passenger_name character varying(255) NOT NULL,
    passenger_title character varying(10) NOT NULL,
    status character varying(10) NOT NULL
);
ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.booking
    ADD CONSTRAINT uk_grbhxwawj7ixsr2jeto2vrx34 UNIQUE (booking_code);


CREATE TABLE public.user_booking (
    user_id uuid NOT NULL,
    booking_id uuid NOT NULL
);
ALTER TABLE ONLY public.user_booking
    ADD CONSTRAINT user_booking_pkey PRIMARY KEY (user_id, booking_id);
ALTER TABLE ONLY public.user_booking
    ADD CONSTRAINT fkqvblem0sp5v0e7bl9n1cyquk3 FOREIGN KEY (user_id) REFERENCES public."user"(id);
ALTER TABLE ONLY public.user_booking
    ADD CONSTRAINT fkqsgehubtjd9afvmyapjnup50 FOREIGN KEY (booking_id) REFERENCES public.booking(id);

CREATE TABLE public.flight_booking (
    flight_id uuid NOT NULL,
    booking_id uuid NOT NULL
);
ALTER TABLE ONLY public.flight_booking
    ADD CONSTRAINT flight_booking_pkey PRIMARY KEY (flight_id, booking_id);
ALTER TABLE ONLY public.flight_booking
    ADD CONSTRAINT fk3uiklmjy1d7ba6rrjp6iq08kq FOREIGN KEY (flight_id) REFERENCES public.flight(id);
ALTER TABLE ONLY public.flight_booking
    ADD CONSTRAINT fka9g3e73w0v8llleclslk4jrdq FOREIGN KEY (booking_id) REFERENCES public.booking(id);