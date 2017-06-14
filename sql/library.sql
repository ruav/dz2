--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.8
-- Dumped by pg_dump version 9.4.8
-- Started on 2017-02-06 22:12:11 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2083 (class 1262 OID 12175)
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2084 (class 1262 OID 12175)
-- Dependencies: 2083
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 1 (class 3079 OID 11895)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 16398)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE books (
    id integer NOT NULL,
    title character varying(50) NOT NULL,
    author character varying(50) DEFAULT 'Не известен'::character varying NOT NULL,
    publisher character varying(50),
    yearpublishing integer DEFAULT 1800 NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE books OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16401)
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE books_id_seq OWNER TO postgres;

--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 176
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE books_id_seq OWNED BY books.id;


--
-- TOC entry 179 (class 1259 OID 24581)
-- Name: entity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE entity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE entity_id_seq OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 24579)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 57347)
-- Name: readbooks; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE readbooks (
    id integer NOT NULL,
    book_id integer,
    user_id integer
);


ALTER TABLE readbooks OWNER TO postgres;

--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN readbooks.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN readbooks.id IS '		';


--
-- TOC entry 181 (class 1259 OID 57350)
-- Name: readbooks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE readbooks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE readbooks_id_seq OWNER TO postgres;

--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 181
-- Name: readbooks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE readbooks_id_seq OWNED BY readbooks.id;


--
-- TOC entry 177 (class 1259 OID 24576)
-- Name: temp; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE temp (
);


ALTER TABLE temp OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16387)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(32) NOT NULL,
    firstname character varying(50),
    lastname character varying(50),
    admin boolean DEFAULT false NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16385)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 173
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 1942 (class 2604 OID 16414)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY books ALTER COLUMN id SET DEFAULT nextval('books_id_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 57352)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY readbooks ALTER COLUMN id SET DEFAULT nextval('readbooks_id_seq'::regclass);


--
-- TOC entry 1939 (class 2604 OID 16424)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 2072 (class 0 OID 16398)
-- Dependencies: 175
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO books VALUES (28, 'Герой нашего времени', 'Михаил Юрьевич Лермонтов', 'неизв.', 2007, 0);
INSERT INTO books VALUES (9, 'Проба два', 'Поба', 'Самоиздат', 1986, 0);
INSERT INTO books VALUES (10, 'Проба три', 'Поба', 'Самоиздат', 1987, 0);
INSERT INTO books VALUES (17, 'Скотный двор', 'Джордж Оруэлл', 'неизв.', 1997, 0);
INSERT INTO books VALUES (18, '1984', 'Джордж Оруэлл', 'неизв.', 1987, 0);
INSERT INTO books VALUES (19, 'Приключения Тома Сойера', 'Марк Твен', 'неизв.', 1979, 0);
INSERT INTO books VALUES (20, 'Зов Ктулху', 'Говард Лафкрафт', 'неизв.', 1993, 0);
INSERT INTO books VALUES (21, 'Стража! Стража!', 'Терри Прэтчетт', 'неизв.', 1989, 0);
INSERT INTO books VALUES (22, 'Безумная звезда', 'Терри Прэтчетт', 'неизв.', 1986, 0);
INSERT INTO books VALUES (23, 'Анна Каренина', 'Лев Николаевич Толстой', 'неизв.', 2016, 0);
INSERT INTO books VALUES (24, 'Недоросль', 'Денис Иванович Фонвизин', 'неизв.', 2005, 0);
INSERT INTO books VALUES (25, 'Отцы и дети', 'Иван Сергеевич Тургенев', 'неизв.', 2013, 0);
INSERT INTO books VALUES (26, 'Муму', 'Иван Сергеевич Тургенев', 'неизв.', 2013, 0);
INSERT INTO books VALUES (27, 'Доходное место', 'Александр Николаевич Островский', 'Москва, Изд-во "ЭКСМО"', 2004, 0);
INSERT INTO books VALUES (8, 'Проба раз', 'Поба', 'Самоиздат', 1987, 5);
INSERT INTO books VALUES (77, 'e345', 'ert', '89hguyy', 62, 0);


--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 176
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('books_id_seq', 77, true);


--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 179
-- Name: entity_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('entity_id_seq', 1, true);


--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 178
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 5, true);


--
-- TOC entry 2077 (class 0 OID 57347)
-- Dependencies: 180
-- Data for Name: readbooks; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO readbooks VALUES (3, 18, 3);
INSERT INTO readbooks VALUES (5, 22, 36);
INSERT INTO readbooks VALUES (8, 25, 36);
INSERT INTO readbooks VALUES (20, 21, 3);
INSERT INTO readbooks VALUES (21, 21, 36);
INSERT INTO readbooks VALUES (28, 25, 3);
INSERT INTO readbooks VALUES (36, 19, 1);
INSERT INTO readbooks VALUES (37, 23, 1);
INSERT INTO readbooks VALUES (41, 8, 1);
INSERT INTO readbooks VALUES (43, 21, 68);
INSERT INTO readbooks VALUES (44, 24, 68);
INSERT INTO readbooks VALUES (45, 27, 68);
INSERT INTO readbooks VALUES (46, 9, 68);
INSERT INTO readbooks VALUES (47, 18, 36);
INSERT INTO readbooks VALUES (48, 21, 1);


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 181
-- Name: readbooks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('readbooks_id_seq', 48, true);


--
-- TOC entry 2074 (class 0 OID 24576)
-- Dependencies: 177
-- Data for Name: temp; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2071 (class 0 OID 16387)
-- Dependencies: 174
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users VALUES (45, 'user', '56b291d6ed9b9cb8e2d3dc09cb6377b9', '', '', false, 2);
INSERT INTO users VALUES (64, 'Temp', '1234567890', '', '', false, 10);
INSERT INTO users VALUES (46, 'user3', '56b291d6ed9b9cb8e2d3dc09cb6377b9', '', '', false, 6);
INSERT INTO users VALUES (42, 'bro12', '56b291d6ed9b9cb8e2d3dc09cb6377b9', 'Бро', 'Иди', false, 0);
INSERT INTO users VALUES (48, 'bob', '56b291d6ed9b9cb8e2d3dc09cb6377b9', '', '', false, 0);
INSERT INTO users VALUES (67, 'temp1', '56b291d6ed9b9cb8e2d3dc09cb6377b9', '', '', false, 0);
INSERT INTO users VALUES (3, 'admin', '56b291d6ed9b9cb8e2d3dc09cb6377b9', '', '', true, 3);
INSERT INTO users VALUES (49, 'Broruav', 'oafh', 'Hощр', NULL, false, 4);
INSERT INTO users VALUES (68, 'qwe8', '56b291d6ed9b9cb8e2d3dc09cb6377b9', '', '', false, 4);
INSERT INTO users VALUES (36, 'bro', '56b291d6ed9b9cb8e2d3dc09cb6377b9', '', '', false, 1);
INSERT INTO users VALUES (1, 'ruav', '61163aa430a600d787bcf24c693ecbc4', '', '', true, 50);


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 173
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 68, true);


--
-- TOC entry 1952 (class 2606 OID 16416)
-- Name: books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- TOC entry 1954 (class 2606 OID 73746)
-- Name: readbooks_book_id_user_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY readbooks
    ADD CONSTRAINT readbooks_book_id_user_id_key UNIQUE (book_id, user_id);


--
-- TOC entry 1956 (class 2606 OID 57357)
-- Name: readbooks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY readbooks
    ADD CONSTRAINT readbooks_pkey PRIMARY KEY (id);


--
-- TOC entry 1948 (class 2606 OID 16428)
-- Name: users_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_login_key UNIQUE (login);


--
-- TOC entry 1950 (class 2606 OID 16426)
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 1960 (class 2606 OID 73754)
-- Name: fk1ktiu3vebwra7wnioiwyiu0x9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY readbooks
    ADD CONSTRAINT fk1ktiu3vebwra7wnioiwyiu0x9 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 1959 (class 2606 OID 73749)
-- Name: fkrhv3eksnw56l75eic8tscbjdx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY readbooks
    ADD CONSTRAINT fkrhv3eksnw56l75eic8tscbjdx FOREIGN KEY (book_id) REFERENCES books(id);


--
-- TOC entry 1957 (class 2606 OID 57378)
-- Name: readbooks_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY readbooks
    ADD CONSTRAINT readbooks_book_id_fkey FOREIGN KEY (book_id) REFERENCES books(id);


--
-- TOC entry 1958 (class 2606 OID 57383)
-- Name: readbooks_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY readbooks
    ADD CONSTRAINT readbooks_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-02-06 22:12:11 MSK

--
-- PostgreSQL database dump complete
--

