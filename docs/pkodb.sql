--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3 (Debian 15.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: airline; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public.airline (
    id uuid NOT NULL,
    airline character varying(255) NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    icon character varying(255) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL
);


ALTER TABLE public.airline OWNER TO developer;

--
-- Name: authentication; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public.authentication (
    id uuid NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    expired_at timestamp(6) without time zone NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    token character varying(255) NOT NULL
);


ALTER TABLE public.authentication OWNER TO developer;

--
-- Name: booking; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public.booking (
    id uuid NOT NULL,
    booking_code character varying(100) NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    passenger_name character varying(255) NOT NULL,
    passenger_title character varying(10) NOT NULL,
    status character varying(10) NOT NULL
);


ALTER TABLE public.booking OWNER TO developer;

--
-- Name: flight; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public.flight (
    id uuid NOT NULL,
    arrival_time timestamp(6) with time zone NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    depart_time timestamp(6) with time zone NOT NULL,
    departure character varying(50) NOT NULL,
    destination character varying(50) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    price numeric(10,2) NOT NULL,
    airline_id_id uuid
);


ALTER TABLE public.flight OWNER TO developer;

--
-- Name: flight_booking; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public.flight_booking (
    flight_id uuid NOT NULL,
    booking_id uuid NOT NULL
);


ALTER TABLE public.flight_booking OWNER TO developer;

--
-- Name: ktp; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public.ktp (
    id uuid NOT NULL,
    date_created timestamp(6) with time zone NOT NULL,
    image_url character varying(255) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    ktp_result_id uuid,
    user_id_id uuid
);


ALTER TABLE public.ktp OWNER TO developer;

--
-- Name: ktp_result; Type: TABLE; Schema: public; Owner: developer
--

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


ALTER TABLE public.ktp_result OWNER TO developer;

--
-- Name: user; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public."user" (
    id uuid NOT NULL,
    avatar text,
    date_created timestamp(6) with time zone NOT NULL,
    email character varying(100) NOT NULL,
    last_updated timestamp(6) with time zone NOT NULL,
    name character varying(150) NOT NULL,
    password character varying(255) NOT NULL,
    authentication_id uuid
);


ALTER TABLE public."user" OWNER TO developer;

--
-- Name: user_booking; Type: TABLE; Schema: public; Owner: developer
--

CREATE TABLE public.user_booking (
    user_id uuid NOT NULL,
    booking_id uuid NOT NULL
);


ALTER TABLE public.user_booking OWNER TO developer;

--
-- Data for Name: airline; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.airline (id, airline, date_created, icon, last_updated) FROM stdin;
\.


--
-- Data for Name: authentication; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.authentication (id, date_created, expired_at, last_updated, token) FROM stdin;
\.


--
-- Data for Name: booking; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.booking (id, booking_code, date_created, last_updated, passenger_name, passenger_title, status) FROM stdin;
\.


--
-- Data for Name: flight; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.flight (id, arrival_time, date_created, depart_time, departure, destination, last_updated, price, airline_id_id) FROM stdin;
\.


--
-- Data for Name: flight_booking; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.flight_booking (flight_id, booking_id) FROM stdin;
\.


--
-- Data for Name: ktp; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.ktp (id, date_created, image_url, last_updated, ktp_result_id, user_id_id) FROM stdin;
\.


--
-- Data for Name: ktp_result; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.ktp_result (id, date_created, last_updated, married, name, nationality, nik, sex, title) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public."user" (id, avatar, date_created, email, last_updated, name, password, authentication_id) FROM stdin;
\.


--
-- Data for Name: user_booking; Type: TABLE DATA; Schema: public; Owner: developer
--

COPY public.user_booking (user_id, booking_id) FROM stdin;
\.


--
-- Name: airline airline_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.airline
    ADD CONSTRAINT airline_pkey PRIMARY KEY (id);


--
-- Name: authentication authentication_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.authentication
    ADD CONSTRAINT authentication_pkey PRIMARY KEY (id);


--
-- Name: booking booking_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);


--
-- Name: flight_booking flight_booking_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.flight_booking
    ADD CONSTRAINT flight_booking_pkey PRIMARY KEY (flight_id, booking_id);


--
-- Name: flight flight_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT flight_pkey PRIMARY KEY (id);


--
-- Name: ktp ktp_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT ktp_pkey PRIMARY KEY (id);


--
-- Name: ktp_result ktp_result_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.ktp_result
    ADD CONSTRAINT ktp_result_pkey PRIMARY KEY (id);


--
-- Name: ktp uk_5t75t7v9xeso9v8dwnxn0iuf9; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT uk_5t75t7v9xeso9v8dwnxn0iuf9 UNIQUE (ktp_result_id);


--
-- Name: user uk_bb2dht0cjfqusr6rew4or0qvb; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_bb2dht0cjfqusr6rew4or0qvb UNIQUE (authentication_id);


--
-- Name: booking uk_grbhxwawj7ixsr2jeto2vrx34; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.booking
    ADD CONSTRAINT uk_grbhxwawj7ixsr2jeto2vrx34 UNIQUE (booking_code);


--
-- Name: user uk_ob8kqyqqgmefl0aco34akdtpe; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email);


--
-- Name: user_booking user_booking_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.user_booking
    ADD CONSTRAINT user_booking_pkey PRIMARY KEY (user_id, booking_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: flight_booking fk3uiklmjy1d7ba6rrjp6iq08kq; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.flight_booking
    ADD CONSTRAINT fk3uiklmjy1d7ba6rrjp6iq08kq FOREIGN KEY (flight_id) REFERENCES public.flight(id);


--
-- Name: flight_booking fka9g3e73w0v8llleclslk4jrdq; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.flight_booking
    ADD CONSTRAINT fka9g3e73w0v8llleclslk4jrdq FOREIGN KEY (booking_id) REFERENCES public.booking(id);


--
-- Name: ktp fkdn8cj59cnyr8t4vv8fav07c74; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT fkdn8cj59cnyr8t4vv8fav07c74 FOREIGN KEY (user_id_id) REFERENCES public."user"(id);


--
-- Name: user fki1ehwiv4j9pf13pug7ggy3fhq; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fki1ehwiv4j9pf13pug7ggy3fhq FOREIGN KEY (authentication_id) REFERENCES public.authentication(id);


--
-- Name: ktp fkjxgfaysw3hydxp8fkjpu0725o; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.ktp
    ADD CONSTRAINT fkjxgfaysw3hydxp8fkjpu0725o FOREIGN KEY (ktp_result_id) REFERENCES public.ktp_result(id);


--
-- Name: user_booking fkqsgehubtjd9afvmyapjnup50; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.user_booking
    ADD CONSTRAINT fkqsgehubtjd9afvmyapjnup50 FOREIGN KEY (booking_id) REFERENCES public.booking(id);


--
-- Name: user_booking fkqvblem0sp5v0e7bl9n1cyquk3; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.user_booking
    ADD CONSTRAINT fkqvblem0sp5v0e7bl9n1cyquk3 FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: flight fks1ubb88l7vhfx02immim7f5ha; Type: FK CONSTRAINT; Schema: public; Owner: developer
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT fks1ubb88l7vhfx02immim7f5ha FOREIGN KEY (airline_id_id) REFERENCES public.airline(id);


--
-- PostgreSQL database dump complete
--

