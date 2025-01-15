--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: emprunts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprunts (
    idemprunt integer NOT NULL,
    membreid integer,
    livreid integer,
    dateemprunt date,
    dateretourprevu date,
    dateretoureffective date
);


ALTER TABLE public.emprunts OWNER TO postgres;

--
-- Name: emprunts_idemprunt_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprunts_idemprunt_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.emprunts_idemprunt_seq OWNER TO postgres;

--
-- Name: emprunts_idemprunt_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprunts_idemprunt_seq OWNED BY public.emprunts.idemprunt;


--
-- Name: livres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livres (
    id integer NOT NULL,
    titre character varying(500),
    auteur character varying(100),
    categorie character varying(100),
    nombreexemplaire integer,
    nombreexemplairepresent integer
);


ALTER TABLE public.livres OWNER TO postgres;

--
-- Name: livres_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livres_id_seq OWNER TO postgres;

--
-- Name: livres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livres_id_seq OWNED BY public.livres.id;


--
-- Name: membres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membres (
    idmembre integer NOT NULL,
    nom character varying(200),
    prenom character varying(200),
    email character(30),
    adhesiondate date
);


ALTER TABLE public.membres OWNER TO postgres;

--
-- Name: membres_idmembre_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membres_idmembre_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.membres_idmembre_seq OWNER TO postgres;

--
-- Name: membres_idmembre_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.membres_idmembre_seq OWNED BY public.membres.idmembre;


--
-- Name: emprunts idemprunt; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunts ALTER COLUMN idemprunt SET DEFAULT nextval('public.emprunts_idemprunt_seq'::regclass);


--
-- Name: livres id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livres ALTER COLUMN id SET DEFAULT nextval('public.livres_id_seq'::regclass);


--
-- Name: membres idmembre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membres ALTER COLUMN idmembre SET DEFAULT nextval('public.membres_idmembre_seq'::regclass);


--
-- Data for Name: emprunts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprunts (idemprunt, membreid, livreid, dateemprunt, dateretourprevu, dateretoureffective) FROM stdin;
1	3	2	2025-01-11	2025-01-21	\N
2	1	2	2025-01-02	2025-01-12	\N
3	1	1	2025-01-04	2025-01-05	\N
4	7	3	2024-10-11	2025-01-02	\N
5	1	4	2024-11-12	2025-02-01	\N
6	1	3	2024-11-12	2025-01-03	\N
7	1	9	2024-01-01	2024-01-15	\N
\.


--
-- Data for Name: livres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livres (id, titre, auteur, categorie, nombreexemplaire, nombreexemplairepresent) FROM stdin;
0	La chèvre de ma mère	Ricardo Kaniamma	développement personnel	3	\N
1	La puissance du subsconscient	Joseph Murphy	développement personnel	2	\N
2	Le Petit Prince	Antoine de Saint-Exupéry	Jeunesse	10	\N
3	le questionnement	Paul Fokam	collection eveil	50	\N
4	initiation à la bureautique	noumbissie joseph	informatique	5	\N
5	contes du Africains	inconnue	histoire-culture	5	\N
6	Mon livre unique	Aimé Cessaire	education	2	\N
7	Quelle éducation pour créer des emploi et des richesses	Simon OUEDRAOGO	developpement personnel	1	\N
8	la programmation pour les nuls	inconnue	informatique	3	\N
9	mon ami	mutaguigna	musique	10	9
\.


--
-- Data for Name: membres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membres (idmembre, nom, prenom, email, adhesiondate) FROM stdin;
1	jj	karine	kmedom@joseph.com             	2024-01-12
2	Loic	mon voisin	jjloic                        	2025-11-18
3	kk	jj	jkjs                          	2025-01-11
4	noumbissie ngamini	joseph	noumbissiej@gmail.com         	2024-04-19
5	ekwe kake	Charlemagne	ewe@gmail.com                 	2024-01-12
6	toto	toka	totoka@gmail.com              	2025-01-11
7	Tchinda	Jean-Marc	tchindajm@yahoo.fr            	2024-11-14
8	Kengne 	Poyel	kpoyel@gmail.com              	2024-01-17
\.


--
-- Name: emprunts_idemprunt_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprunts_idemprunt_seq', 7, true);


--
-- Name: livres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livres_id_seq', 9, true);


--
-- Name: membres_idmembre_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membres_idmembre_seq', 8, true);


--
-- Name: emprunts emprunts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT emprunts_pkey PRIMARY KEY (idemprunt);


--
-- Name: livres livres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livres
    ADD CONSTRAINT livres_pkey PRIMARY KEY (id);


--
-- Name: membres membres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membres
    ADD CONSTRAINT membres_pkey PRIMARY KEY (idmembre);


--
-- Name: emprunts fk_livre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT fk_livre FOREIGN KEY (livreid) REFERENCES public.livres(id);


--
-- Name: emprunts fk_membre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT fk_membre FOREIGN KEY (membreid) REFERENCES public.membres(idmembre);


--
-- PostgreSQL database dump complete
--

