--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.8
-- Dumped by pg_dump version 9.4.8
-- Started on 2017-02-06 22:13:11 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2076 (class 1262 OID 49155)
-- Name: prog; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE prog WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE prog OWNER TO postgres;

\connect prog

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11895)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 49156)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 177 (class 1259 OID 49301)
-- Name: articles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE articles (
    uid uuid DEFAULT uuid_generate_v4() NOT NULL,
    id integer NOT NULL,
    title text NOT NULL,
    contenthtml text NOT NULL,
    contentmarkdown text NOT NULL,
    parentuid uuid,
    noteuid uuid NOT NULL
);


ALTER TABLE articles OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 49308)
-- Name: articles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE articles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE articles_id_seq OWNER TO postgres;

--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 178
-- Name: articles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE articles_id_seq OWNED BY articles.id;


--
-- TOC entry 174 (class 1259 OID 49176)
-- Name: graph_data; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE graph_data (
    article_uid uuid NOT NULL,
    note_uid uuid NOT NULL,
    x double precision NOT NULL,
    y double precision NOT NULL
);


ALTER TABLE graph_data OWNER TO postgres;

--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 174
-- Name: TABLE graph_data; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE graph_data IS 'Данные по расположению статей на графе';


--
-- TOC entry 175 (class 1259 OID 49179)
-- Name: notes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notes (
    uid uuid DEFAULT uuid_generate_v4() NOT NULL,
    mainarticleuid uuid,
    id integer NOT NULL
);


ALTER TABLE notes OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 49183)
-- Name: notes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notes_id_seq OWNER TO postgres;

--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 176
-- Name: notes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notes_id_seq OWNED BY notes.id;


--
-- TOC entry 1944 (class 2604 OID 49310)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles ALTER COLUMN id SET DEFAULT nextval('articles_id_seq'::regclass);


--
-- TOC entry 1942 (class 2604 OID 49311)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notes ALTER COLUMN id SET DEFAULT nextval('notes_id_seq'::regclass);


--
-- TOC entry 2070 (class 0 OID 49301)
-- Dependencies: 177
-- Data for Name: articles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO articles VALUES ('d9c9507b-5208-463a-8ff2-9dec01a7b27d', 395, 'конспект', '<p>Краткое описание</p>
', 'Краткое описание', NULL, 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('39670d72-eec7-4dfb-82b3-2b22cbfed5df', 404, 'Структура заголовка объекта', '<p> Каждый заголовок для
большинства JVM(Hotspot, openJVM) состоит из двух машинных слов.
Размер заголовка: 8 байт(х86) 16 байт(х64)
Структура заголовка:</p>
<ul>
<li>Mark Word<ul>
<li>Hash Code</li>
<li>Garbage Collection Information</li>
<li>Lock</li>
</ul>
</li>
<li>Type Information Block Pointer</li>
<li>Array Length</li>
</ul>
', ' Каждый заголовок для
большинства JVM(Hotspot, openJVM) состоит из двух машинных слов.
Размер заголовка: 8 байт(х86) 16 байт(х64)
Структура заголовка:
 - Mark Word
- Hash Code
- Garbage Collection Information
- Lock
 - Type Information Block Pointer
 - Array Length', '28ea5ae7-1d4c-4032-9206-d315b847416a', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('d276cb80-13eb-434d-8a6c-e511661ce9d5', 397, 'Java', '<p>Java</p>
', 'Java', NULL, 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('e3d4a80f-141e-475d-8494-1774cb6b513b', 399, 'Функции', '<ul>
<li>Даёт необходимую информацию для компилятора</li>
<li>Даёт необходимую информацию для генерации др.кода, конфигураций и т.д.</li>
<li>может использоваться во время выполнения, для получения данных через отражение (reflection)</li>
</ul>
', ' - Даёт необходимую информацию для компилятора
 - Даёт необходимую информацию для генерации др.кода, конфигураций и т.д.
 - может использоваться во время выполнения, для получения данных через отражение (reflection)
', '8db843e9-945d-4ffb-8c38-dc9ad33ba630', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('4b5c2a8c-eaf3-41c2-b23c-a1e96cba789b', 401, 'Для кода', '<ul>
<li>@Override — проверяет, переопределён ли метод. Вызывает ошибку компиляции, если метод не найден в родительском классе;</li>
<li>@Deprecated — отмечает, что метод устарел. Вызывает предупреждение компиляции, если метод используется;</li>
<li>@SuppressWarnings — указывает компилятору подавить предупреждения компиляции, определённые в параметрах аннотации;</li>
</ul>
', ' - @Override — проверяет, переопределён ли метод. Вызывает ошибку компиляции, если метод не найден в родительском классе;
 - @Deprecated — отмечает, что метод устарел. Вызывает предупреждение компиляции, если метод используется;
 - @SuppressWarnings — указывает компилятору подавить предупреждения компиляции, определённые в параметрах аннотации;', '8db843e9-945d-4ffb-8c38-dc9ad33ba630', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('e35c8f08-8ee6-47d3-9fcd-715faf357292', 402, 'Для аннотаций', '<ul>
<li>@Retention — определяет, как отмеченная аннотация может храниться — в коде, в скомпилированном классе или во время работы кода;</li>
<li>@Documented — отмечает аннотацию для включения в документацию;</li>
<li>@Target — отмечает аннотацию как ограничивающую, какие элементы аннотации могут быть к ней применены;</li>
<li>@Inherited — отмечает, что аннотация может быть расширена подклассами аннотируемого класса;</li>
</ul>
', ' - @Retention — определяет, как отмеченная аннотация может храниться — в коде, в скомпилированном классе или во время работы кода;
 - @Documented — отмечает аннотацию для включения в документацию;
 - @Target — отмечает аннотацию как ограничивающую, какие элементы аннотации могут быть к ней применены;
 - @Inherited — отмечает, что аннотация может быть расширена подклассами аннотируемого класса;
', '8db843e9-945d-4ffb-8c38-dc9ad33ba630', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('8db843e9-945d-4ffb-8c38-dc9ad33ba630', 400, 'Аннотации', '<p>Java-аннотация — в языке Java специальная форма синтаксических метаданных, которая может быть добавлена в исходный код.</p>
<p>Аннотации используются для анализа кода, компиляции или выполнения. Аннотируемы пакеты, классы, методы, переменные и параметры.</p>
<p>Выглядит как @ИмяАннотации, предваряющее определение переменной, параметра, метода, класса, пакета.</p>
', 'Java-аннотация — в языке Java специальная форма синтаксических метаданных, которая может быть добавлена в исходный код.

Аннотации используются для анализа кода, компиляции или выполнения. Аннотируемы пакеты, классы, методы, переменные и параметры.

Выглядит как @ИмяАннотации, предваряющее определение переменной, параметра, метода, класса, пакета.', 'd276cb80-13eb-434d-8a6c-e511661ce9d5', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('28ea5ae7-1d4c-4032-9206-d315b847416a', 403, 'Объект', '<p>Для каждого объекта JVM хранит:</p>
<ul>
<li>Заголовок объекта</li>
<li>Память для примитивных типов</li>
<li>Память для ссылочных типов</li>
<li>Смещение/выравнивание</li>
</ul>
', 'Для каждого объекта JVM хранит:
- Заголовок объекта
- Память для примитивных типов
- Память для ссылочных типов
- Смещение/выравнивание', 'd276cb80-13eb-434d-8a6c-e511661ce9d5', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('7337fa95-e324-4bd2-a422-553c65e58909', 405, 'Mark Word', '<p>содержит Hash Code, Garbage Collection
Information, Lock</p>
', 'содержит Hash Code, Garbage Collection
Information, Lock', '39670d72-eec7-4dfb-82b3-2b22cbfed5df', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('7bc3131e-e2a4-44a5-a6a6-8502933e8163', 406, 'Hash Code', '<p>каждый объект имеет хеш код. </p>
<p>По умолчанию  результат вызова метода Object.hashCode() вернет адрес
объекта в памяти.</p>
', 'каждый объект имеет хеш код. 

По умолчанию  результат вызова метода Object.hashCode() вернет адрес
объекта в памяти.', '7337fa95-e324-4bd2-a422-553c65e58909', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('bc836640-0283-48fa-94c1-880867e90877', 407, 'Garbage Collection Information', '<p>каждый java объект
содержит информацию нужную для системы управления
памятью.</p>
', 'каждый java объект
содержит информацию нужную для системы управления
памятью.', '7337fa95-e324-4bd2-a422-553c65e58909', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('e38b468e-94f2-4f87-b6d2-90d8242730cb', 408, 'Lock', '<p>каждый объект содержит информацию о состоянии
блокировки. Это может быть указатель на объект
блокировки или прямое представление блокировки.</p>
', 'каждый объект содержит информацию о состоянии
блокировки. Это может быть указатель на объект
блокировки или прямое представление блокировки.', '7337fa95-e324-4bd2-a422-553c65e58909', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('8b5d9aa7-6245-4615-b31d-139d972862a5', 409, 'Type Information Block Pointer', '<p>содержит информацию о
типе объекта (таблица виртуальных методов, указатель на
объект, указатели на некоторые дополнительные
структуры)</p>
', 'содержит информацию о
типе объекта (таблица виртуальных методов, указатель на
объект, указатели на некоторые дополнительные
структуры)', '39670d72-eec7-4dfb-82b3-2b22cbfed5df', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('83c3eeea-9091-42ca-b775-eb405adac1ed', 410, 'Array Length', '<p>если объект — массив, то заголовок
расширяется 4 байтами для хранения длины массива.</p>
', 'если объект — массив, то заголовок
расширяется 4 байтами для хранения длины массива.', '39670d72-eec7-4dfb-82b3-2b22cbfed5df', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('8ff606d6-85c1-4b92-bf62-d0afb49abffd', 411, 'Смещение/выравнивание', '<p>По сути, это несколько неиспользуемых байт, которые
размещаются после данных самого объекта. Это сделано
для того, чтобы адрес в памяти всегда был кратным
машинному слову. Это необходимо для:</p>
<ul>
<li>ускорения чтения из памяти</li>
<li>уменьшения количества бит для указателя на объект</li>
<li>для уменьшения фрагментации памяти
Стоит также отметить, что в java размер любого объекта
кратен 8 байтам.</li>
</ul>
', 'По сути, это несколько неиспользуемых байт, которые
размещаются после данных самого объекта. Это сделано
для того, чтобы адрес в памяти всегда был кратным
машинному слову. Это необходимо для:
- ускорения чтения из памяти
- уменьшения количества бит для указателя на объект
- для уменьшения фрагментации памяти
Стоит также отметить, что в java размер любого объекта
кратен 8 байтам.', '28ea5ae7-1d4c-4032-9206-d315b847416a', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('2c88afe9-fb1a-41a2-99f8-30c2c44b80bb', 412, 'Монитор', '', '', '28ea5ae7-1d4c-4032-9206-d315b847416a', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');
INSERT INTO articles VALUES ('712aa493-ea6e-43d0-94a3-8f99773e3a14', 413, 'Состояния', '<ul>
<li><h5 id="init-">init:</h5>
монитор только что создан, и пока никем не был захвачен</li>
<li><h5 id="biased-">biased:</h5>
(умная оптимизация, появившаяся далеко не сразу) Монитор «зарезервирован» под первый поток, который его захватил. В дальнейшем для захвата этому потоку не нужны дорогие операции, и захват происходит очень быстро. Когда захват пытается произвести другой поток, либо монитор пере-резервируется для него (rebias), либо монитор переходит в состояние thin (revoke bias). Также есть дополнительные оптимизации, которые действуют сразу на все экземпляры класса объекта, монитор которого пытаются захватить (bulk revoke/rebias)</li>
<li><h5 id="thin-">thin:</h5>
монитор пытаются захватить несколько потоков, но contention нет (т.е. они захватывают его не одновременно, либо с очень маленьким нахлёстом). В таком случае захват выполняется с помощью сравнительно дешёвых CAS. Если возникает contention, то монитор переходит в состояние inflated</li>
<li><h5 id="fat-inflated-">fat/inflated:</h5>
синхронизация производится на уровне операционной системы. Поток паркуется и спит до тех пор, пока не настанет его очередь захватить монитор. Даже если забыть про стоимость смены контекста, то когда поток получит управление, зависит ещё и от системного шедулера, и потому времени может пройти существенно больше, чем хотелось бы. При исчезновении contention монитор может вернуться в состояние thin</li>
</ul>
', ' - ##### init: 
монитор только что создан, и пока никем не был захвачен
 - ##### biased: 
(умная оптимизация, появившаяся далеко не сразу) Монитор «зарезервирован» под первый поток, который его захватил. В дальнейшем для захвата этому потоку не нужны дорогие операции, и захват происходит очень быстро. Когда захват пытается произвести другой поток, либо монитор пере-резервируется для него (rebias), либо монитор переходит в состояние thin (revoke bias). Также есть дополнительные оптимизации, которые действуют сразу на все экземпляры класса объекта, монитор которого пытаются захватить (bulk revoke/rebias)
 - ##### thin: 
монитор пытаются захватить несколько потоков, но contention нет (т.е. они захватывают его не одновременно, либо с очень маленьким нахлёстом). В таком случае захват выполняется с помощью сравнительно дешёвых CAS. Если возникает contention, то монитор переходит в состояние inflated
 - ##### fat/inflated: 
синхронизация производится на уровне операционной системы. Поток паркуется и спит до тех пор, пока не настанет его очередь захватить монитор. Даже если забыть про стоимость смены контекста, то когда поток получит управление, зависит ещё и от системного шедулера, и потому времени может пройти существенно больше, чем хотелось бы. При исчезновении contention монитор может вернуться в состояние thin', '2c88afe9-fb1a-41a2-99f8-30c2c44b80bb', 'e0a2a0cd-fc43-42d7-86b0-de305275579f');


--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 178
-- Name: articles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articles_id_seq', 413, true);


--
-- TOC entry 2067 (class 0 OID 49176)
-- Dependencies: 174
-- Data for Name: graph_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO graph_data VALUES ('e3d4a80f-141e-475d-8494-1774cb6b513b', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 525.425031551558277, 403.010027517766389);
INSERT INTO graph_data VALUES ('8db843e9-945d-4ffb-8c38-dc9ad33ba630', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 454.021242535630051, 247.570570665477305);
INSERT INTO graph_data VALUES ('e35c8f08-8ee6-47d3-9fcd-715faf357292', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 474.143716376745374, 64.9164502485633079);
INSERT INTO graph_data VALUES ('d276cb80-13eb-434d-8a6c-e511661ce9d5', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 314.525116598649674, 273.132647500388202);
INSERT INTO graph_data VALUES ('39670d72-eec7-4dfb-82b3-2b22cbfed5df', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 141.559705818140685, 393.105044639708296);
INSERT INTO graph_data VALUES ('4b5c2a8c-eaf3-41c2-b23c-a1e96cba789b', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 590.982825989841444, 221.990377411591538);
INSERT INTO graph_data VALUES ('7337fa95-e324-4bd2-a422-553c65e58909', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 249.800782803415586, 532.003089212627515);
INSERT INTO graph_data VALUES ('7bc3131e-e2a4-44a5-a6a6-8502933e8163', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 192.902312380599284, 696.348435944015364);
INSERT INTO graph_data VALUES ('28ea5ae7-1d4c-4032-9206-d315b847416a', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 210.150608531494328, 186.260863050284968);
INSERT INTO graph_data VALUES ('e38b468e-94f2-4f87-b6d2-90d8242730cb', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 325.233953576537772, 408.601675875741819);
INSERT INTO graph_data VALUES ('bc836640-0283-48fa-94c1-880867e90877', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 369.763512148564473, 636.826846035461926);
INSERT INTO graph_data VALUES ('83c3eeea-9091-42ca-b775-eb405adac1ed', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 40.6111771581823149, 525.593915296702335);
INSERT INTO graph_data VALUES ('8ff606d6-85c1-4b92-bf62-d0afb49abffd', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 282.942441009075253, 28.7048803418817222);
INSERT INTO graph_data VALUES ('8b5d9aa7-6245-4615-b31d-139d972862a5', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 35.456442868510166, 280.549846969188309);
INSERT INTO graph_data VALUES ('2c88afe9-fb1a-41a2-99f8-30c2c44b80bb', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 110.961195157160375, 64.0743227332073673);
INSERT INTO graph_data VALUES ('712aa493-ea6e-43d0-94a3-8f99773e3a14', 'e0a2a0cd-fc43-42d7-86b0-de305275579f', 16.7327318055208458, -53.5196091014938062);


--
-- TOC entry 2068 (class 0 OID 49179)
-- Dependencies: 175
-- Data for Name: notes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO notes VALUES ('e0a2a0cd-fc43-42d7-86b0-de305275579f', 'd9c9507b-5208-463a-8ff2-9dec01a7b27d', 18);


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 176
-- Name: notes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notes_id_seq', 18, true);


--
-- TOC entry 1952 (class 2606 OID 49389)
-- Name: articles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT articles_pkey PRIMARY KEY (uid);


--
-- TOC entry 1946 (class 2606 OID 49266)
-- Name: graph_data_article_uid_note_uid_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY graph_data
    ADD CONSTRAINT graph_data_article_uid_note_uid_pk PRIMARY KEY (article_uid, note_uid);


--
-- TOC entry 1949 (class 2606 OID 49268)
-- Name: notes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notes
    ADD CONSTRAINT notes_pkey PRIMARY KEY (uid);


--
-- TOC entry 1950 (class 1259 OID 49390)
-- Name: articles_id_uindex; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX articles_id_uindex ON articles USING btree (id);


--
-- TOC entry 1947 (class 1259 OID 49270)
-- Name: notes_id_uindex; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX notes_id_uindex ON notes USING btree (id);


--
-- TOC entry 1956 (class 2606 OID 49391)
-- Name: articles_notes_uid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT articles_notes_uid_fk FOREIGN KEY (noteuid) REFERENCES notes(uid);


--
-- TOC entry 1957 (class 2606 OID 49396)
-- Name: articles_parentuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT articles_parentuid_fkey FOREIGN KEY (parentuid) REFERENCES articles(uid);


--
-- TOC entry 1954 (class 2606 OID 49401)
-- Name: graph_data_articles_uid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY graph_data
    ADD CONSTRAINT graph_data_articles_uid_fk FOREIGN KEY (article_uid) REFERENCES articles(uid);


--
-- TOC entry 1953 (class 2606 OID 49286)
-- Name: graph_data_notes_uid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY graph_data
    ADD CONSTRAINT graph_data_notes_uid_fk FOREIGN KEY (note_uid) REFERENCES notes(uid);


--
-- TOC entry 1955 (class 2606 OID 49406)
-- Name: notes_mainarticleuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notes
    ADD CONSTRAINT notes_mainarticleuid_fkey FOREIGN KEY (mainarticleuid) REFERENCES articles(uid);


--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 8
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-02-06 22:13:11 MSK

--
-- PostgreSQL database dump complete
--

