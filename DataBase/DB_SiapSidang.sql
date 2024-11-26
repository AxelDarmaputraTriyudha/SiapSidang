--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

-- Started on 2024-11-26 19:04:18 WIB

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
-- TOC entry 221 (class 1259 OID 16560)
-- Name: dosen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dosen (
    nik character varying(20) NOT NULL,
    nama character varying(50) NOT NULL,
    kode_nama character varying(5),
    email character varying(60),
    password character varying(50) NOT NULL
);


ALTER TABLE public.dosen OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16494)
-- Name: komponen_nilai; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.komponen_nilai (
    id_komp integer NOT NULL,
    deskripsi character varying(100) NOT NULL,
    peran character varying(15),
    bobot numeric(4,2) NOT NULL
);


ALTER TABLE public.komponen_nilai OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16493)
-- Name: komponen_nilai_id_komp_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.komponen_nilai_id_komp_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.komponen_nilai_id_komp_seq OWNER TO postgres;

--
-- TOC entry 3646 (class 0 OID 0)
-- Dependencies: 216
-- Name: komponen_nilai_id_komp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.komponen_nilai_id_komp_seq OWNED BY public.komponen_nilai.id_komp;


--
-- TOC entry 215 (class 1259 OID 16435)
-- Name: mahasiswa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mahasiswa (
    npm character varying(10) NOT NULL,
    nama character varying(50) NOT NULL,
    email character varying(60),
    password character varying(50) NOT NULL
);


ALTER TABLE public.mahasiswa OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16527)
-- Name: nilai_ta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nilai_ta (
    id_ta integer NOT NULL,
    id_komp integer NOT NULL,
    nilai numeric(5,2) NOT NULL
);


ALTER TABLE public.nilai_ta OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16568)
-- Name: sidang_ta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sidang_ta (
    id_sidang integer NOT NULL,
    nik character varying(20),
    id_ta integer,
    peran character varying(15),
    hari character varying(10),
    tanggal date NOT NULL,
    waktu time without time zone NOT NULL,
    tempat character varying(15)
);


ALTER TABLE public.sidang_ta OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16567)
-- Name: sidang_ta_id_sidang_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sidang_ta_id_sidang_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sidang_ta_id_sidang_seq OWNER TO postgres;

--
-- TOC entry 3647 (class 0 OID 0)
-- Dependencies: 222
-- Name: sidang_ta_id_sidang_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sidang_ta_id_sidang_seq OWNED BY public.sidang_ta.id_sidang;


--
-- TOC entry 219 (class 1259 OID 16516)
-- Name: tugas_akhir; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tugas_akhir (
    id_ta integer NOT NULL,
    jenis character varying(3) NOT NULL,
    judul character varying(255) NOT NULL,
    semester_akd character varying(6) NOT NULL,
    tahun_akd integer NOT NULL,
    nilai_pb1 numeric(5,2),
    nilai_pb2 numeric(5,2),
    nilai_pu1 numeric(5,2),
    nilai_pu2 numeric(5,2),
    nilai_koord numeric(5,2),
    nilai_akhir numeric(5,2),
    angka_akhir character(2),
    id_mahasiswa character varying(10)
);


ALTER TABLE public.tugas_akhir OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16515)
-- Name: tugas_akhir_id_ta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tugas_akhir_id_ta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tugas_akhir_id_ta_seq OWNER TO postgres;

--
-- TOC entry 3648 (class 0 OID 0)
-- Dependencies: 218
-- Name: tugas_akhir_id_ta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tugas_akhir_id_ta_seq OWNED BY public.tugas_akhir.id_ta;


--
-- TOC entry 3465 (class 2604 OID 16497)
-- Name: komponen_nilai id_komp; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.komponen_nilai ALTER COLUMN id_komp SET DEFAULT nextval('public.komponen_nilai_id_komp_seq'::regclass);


--
-- TOC entry 3467 (class 2604 OID 16571)
-- Name: sidang_ta id_sidang; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sidang_ta ALTER COLUMN id_sidang SET DEFAULT nextval('public.sidang_ta_id_sidang_seq'::regclass);


--
-- TOC entry 3466 (class 2604 OID 16519)
-- Name: tugas_akhir id_ta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tugas_akhir ALTER COLUMN id_ta SET DEFAULT nextval('public.tugas_akhir_id_ta_seq'::regclass);


--
-- TOC entry 3638 (class 0 OID 16560)
-- Dependencies: 221
-- Data for Name: dosen; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dosen (nik, nama, kode_nama, email, password) VALUES
('20010001', 'Vania Natalie', 'VAN', 'vania.natali@unpar.ac.id', 'password123'),
('20010002', 'Keenan', 'KLM', 'keenan.leman@unpar.ac.id', 'password124'),
('20010003', 'pascal', 'PAN', 'pascal@unpar.ac.id', 'password125'),
('20010004', 'mariskha', 'MTA', 'mariskha@unpar.ac.id', 'password126'),
('20010005', 'elisati', 'ELI', 'elisatih@unpar.ac.id', 'password127'),
('20010006', 'maria veronica', 'MVC', 'maria.veronica@unpar.ac.id', 'password128'),
('20010007', 'lionov', 'LNV', 'lionov@unpar.ac.id', 'password129'),
('20010008', 'raymond', 'RCP', 'raymond.chandra@unpar.ac.id', 'password130'),
('20010009', 'rosa de lima', 'RDL', 'padmowati@gmail.com', 'password131'),
('20010010', 'husnul', 'HUH', 'husnulhakim@unpar.ac.id', 'password132');



--
-- TOC entry 3634 (class 0 OID 16494)
-- Dependencies: 217
-- Data for Name: komponen_nilai; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.komponen_nilai (id_komp, deskripsi, peran, bobot) VALUES
(1, 'Tata Tulis Laporan', 'PB1', 30.00),
(2, 'Tata Tulis Laporan', 'PB2', 25.00),
(3, 'Tata Tulis Laporan', 'PU1', 20.00),
(4, 'Presentasi', 'PB1', 30.00),
(5, 'Presentasi', 'PB2', 25.00),
(6, 'Presentasi', 'PU1', 20.00),
(7, 'Tata Bahasa', 'PB1', 20.00),
(8, 'Tata Bahasa', 'PB2', 15.00),
(9, 'Tata Bahasa', 'Koordinator', 10.00),
(10, 'Penguasaan Materi', 'PB1', 30.00),
(11, 'Penguasaan Materi', 'PB2', 25.00),
(12, 'Penguasaan Materi', 'PU1', 20.00),
(13, 'Kejelasan Penyampaian', 'PB1', 20.00),
(14, 'Kejelasan Penyampaian', 'PB2', 15.00),
(15, 'Kejelasan Penyampaian', 'Koordinator', 10.00),
(16, 'Kreativitas', 'PB1', 25.00),
(17, 'Kreativitas', 'PB2', 20.00),
(18, 'Kreativitas', 'PU1', 15.00),
(19, 'Kreativitas', 'Koordinator', 10.00),
(20, 'Kesesuaian Format', 'PB1', 15.00),
(21, 'Kesesuaian Format', 'PB2', 10.00),
(22, 'Kesesuaian Format', 'PU1', 10.00),
(23, 'Kesesuaian Format', 'Koordinator', 5.00),
(24, 'Relevansi Konten', 'PB1', 30.00),
(25, 'Relevansi Konten', 'PB2', 25.00);



--
-- TOC entry 3632 (class 0 OID 16435)
-- Dependencies: 215
-- Data for Name: mahasiswa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.mahasiswa (npm, nama, email, password) VALUES
('6182201001', 'Ahmad Fauzi', 'ahmad.fauzi01@gmail.com', 'pass12345'),
('6182201002', 'Bella Ramadhani', 'bella.rama02@gmail.com', 'pass67890'),
('6182201003', 'Charlie Pratama', 'charlie.pratama03@gmail.com', 'securePass03'),
('6182201004', 'Dina Wijaya', 'dina.wijaya04@gmail.com', 'myPassDina04'),
('6182201005', 'Eko Saputra', 'eko.saputra05@gmail.com', 'ekoSap05'),
('6182201006', 'Fina Anggraini', 'fina.anggraini06@gmail.com', 'fina123'),
('6182201007', 'Gilang Permana', 'gilang.permana07@gmail.com', 'gilpass07'),
('6182201008', 'Hana Salsabila', 'hana.salsabila08@gmail.com', 'hanaPass08'),
('6182201009', 'Irfan Maulana', 'irfan.maulana09@gmail.com', 'irfan09pass'),
('6182201010', 'Joko Santoso', 'joko.santoso10@gmail.com', 'jokoSecure10'),
('6182201011', 'Kiki Amalia', 'kiki.amalia11@gmail.com', 'kikiPass11'),
('6182201012', 'Lutfi Rizki', 'lutfi.rizki12@gmail.com', 'lutfi12pass'),
('6182201013', 'Mega Putri', 'mega.putri13@gmail.com', 'megaPass13'),
('6182201014', 'Nanda Wijaya', 'nanda.wijaya14@gmail.com', 'nanda14secure'),
('6182201015', 'Oka Pradipta', 'oka.pradipta15@gmail.com', 'okaPass15'),
('6182201016', 'Putri Ayu', 'putri.ayu16@gmail.com', 'putri16'),
('6182201017', 'Qory Indah', 'qory.indah17@gmail.com', 'qory17pass'),
('6182201018', 'Rian Syahputra', 'rian.syahputra18@gmail.com', 'rian18secure'),
('6182201019', 'Sari Utami', 'sari.utami19@gmail.com', 'sari19'),
('6182201020', 'Tomi Harun', 'tomi.harun20@gmail.com', 'tomi20pass'),
('6182201021', 'Umi Zahra', 'umi.zahra21@gmail.com', 'umi21secure'),
('6182201022', 'Vina Lestari', 'vina.lestari22@gmail.com', 'vina22pass'),
('6182201023', 'Wahyu Hidayat', 'wahyu.hidayat23@gmail.com', 'wahyu23pass'),
('6182201024', 'Xena Alifah', 'xena.alifah24@gmail.com', 'xena24secure'),
('6182201025', 'Yoga Kurniawan', 'yoga.kurniawan25@gmail.com', 'yoga25pass'),
('6182201026', 'Zahra Aulia', 'zahra.aulia26@gmail.com', 'zahra26pass'),
('6182201027', 'Ali Akbar', 'ali.akbar27@gmail.com', 'ali27pass'),
('6182201028', 'Budi Cahyono', 'budi.cahyono28@gmail.com', 'budi28secure'),
('6182201029', 'Citra Kirana', 'citra.kirana29@gmail.com', 'citra29pass'),
('6182201030', 'Dewi Rahayu', 'dewi.rahayu30@gmail.com', 'dewi30pass'),
('6182201031', 'Erlangga Putra', 'erlangga.putra31@gmail.com', 'erlangga31secure'),
('6182201032', 'Farah Nadia', 'farah.nadia32@gmail.com', 'farah32pass'),
('6182201033', 'Gita Pratiwi', 'gita.pratiwi33@gmail.com', 'gita33pass'),
('6182201034', 'Hendra Saputra', 'hendra.saputra34@gmail.com', 'hendra34pass'),
('6182201035', 'Ida Fitriani', 'ida.fitriani35@gmail.com', 'ida35secure'),
('6182201036', 'Jamil Abdullah', 'jamil.abdullah36@gmail.com', 'jamil36pass'),
('6182201037', 'Kartika Sari', 'kartika.sari37@gmail.com', 'kartika37secure'),
('6182201038', 'Lina Marlina', 'lina.marlina38@gmail.com', 'lina38pass'),
('6182201039', 'Miko Aditya', 'miko.aditya39@gmail.com', 'miko39secure'),
('6182201040', 'Nina Aprilia', 'nina.aprilia40@gmail.com', 'nina40pass'),
('6182201041', 'Omar Faisal', 'omar.faisal41@gmail.com', 'omar41pass'),
('6182201042', 'Prita Anggraeni', 'prita.anggraeni42@gmail.com', 'prita42secure'),
('6182201043', 'Rika Nurhaliza', 'rika.nurhaliza43@gmail.com', 'rika43pass'),
('6182201044', 'Samsul Bahri', 'samsul.bahri44@gmail.com', 'samsul44pass'),
('6182201045', 'Tasya Ayunda', 'tasya.ayunda45@gmail.com', 'tasya45secure'),
('6182201046', 'Usman Hakim', 'usman.hakim46@gmail.com', 'usman46pass'),
('6182201047', 'Viona Utami', 'viona.utami47@gmail.com', 'viona47secure'),
('6182201048', 'Wulan Sari', 'wulan.sari48@gmail.com', 'wulan48pass'),
('6182201049', 'Xavier Rahman', 'xavier.rahman49@gmail.com', 'xavier49pass'),
('6182201050', 'Yuni Lestari', 'yuni.lestari50@gmail.com', 'yuni50secure');


--
-- TOC entry 3637 (class 0 OID 16527)
-- Dependencies: 220
-- Data for Name: nilai_ta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.nilai_ta (id_ta, id_komp, nilai) VALUES
(1, 1, 89.00),
(1, 5, 87.50),
(1, 8, 90.00),
(1, 12, 88.50),
(1, 17, 91.00),
(2, 3, 90.50),
(2, 6, 88.00),
(2, 9, 86.50),
(2, 11, 87.00),
(2, 14, 89.00),
(3, 4, 88.50),
(3, 7, 90.00),
(3, 10, 87.50),
(3, 13, 89.50),
(3, 16, 88.00),
(4, 2, 91.00),
(4, 5, 87.00),
(4, 8, 90.00),
(4, 12, 89.50),
(4, 15, 88.00),
(5, 3, 90.00),
(5, 6, 88.50),
(5, 9, 89.00),
(5, 11, 86.50),
(5, 14, 90.50),
(6, 4, 87.00),
(6, 7, 88.00),
(6, 10, 90.00),
(6, 13, 89.50),
(6, 16, 88.50),
(7, 2, 89.50),
(7, 5, 86.00),
(7, 8, 90.00),
(7, 12, 89.00),
(7, 15, 88.50),
(8, 3, 88.50),
(8, 6, 89.50),
(8, 9, 90.50),
(8, 11, 87.00),
(8, 14, 89.00),
(9, 4, 90.00),
(9, 7, 88.50),
(9, 10, 87.50),
(9, 13, 90.00),
(9, 16, 88.50),
(10, 2, 91.00),
(10, 5, 89.00),
(10, 8, 90.00),
(10, 12, 87.50),
(10, 15, 88.00),
(11, 1, 88.00),
(11, 4, 90.00),
(11, 7, 89.00),
(11, 10, 87.50),
(11, 13, 90.00),
(12, 2, 87.50),
(12, 5, 88.50),
(12, 8, 89.00),
(12, 11, 86.00),
(12, 14, 90.50),
(13, 3, 88.50),
(13, 6, 90.00),
(13, 9, 89.50),
(13, 12, 87.00),
(13, 15, 89.00),
(14, 1, 90.00),
(14, 4, 89.50),
(14, 7, 87.00),
(14, 10, 88.50),
(14, 13, 90.00),
(15, 2, 91.00),
(15, 5, 87.50),
(15, 8, 90.50),
(15, 11, 86.50),
(15, 14, 89.00),
(16, 3, 88.00),
(16, 6, 90.00),
(16, 9, 87.50),
(16, 12, 89.00),
(16, 15, 88.50),
(17, 1, 90.50),
(17, 4, 89.00),
(17, 7, 88.50),
(17, 10, 90.00),
(17, 13, 89.50),
(18, 2, 86.50),
(18, 5, 87.00),
(18, 8, 89.00),
(18, 11, 90.00),
(18, 14, 88.00),
(19, 3, 89.50),
(19, 6, 88.50),
(19, 9, 90.00),
(19, 12, 87.00),
(19, 15, 89.00),
(20, 1, 90.00),
(20, 4, 89.00),
(20, 7, 88.50),
(20, 10, 87.50),
(20, 13, 90.00),
(21, 2, 89.00),
(21, 5, 87.50),
(21, 8, 88.00),
(21, 11, 90.00),
(21, 14, 89.50),
(22, 3, 86.50),
(22, 6, 90.50),
(22, 9, 89.00),
(22, 12, 87.50),
(22, 15, 88.50),
(23, 1, 88.00),
(23, 4, 89.50),
(23, 7, 88.00),
(23, 10, 90.00),
(23, 13, 89.50),
(24, 2, 87.00),
(24, 5, 89.00),
(24, 8, 90.00),
(24, 11, 86.50),
(24, 14, 88.00),
(25, 3, 89.00),
(25, 6, 88.50),
(25, 9, 87.00),
(25, 12, 90.00),
(25, 15, 89.50),
(26, 1, 90.00),
(26, 4, 88.50),
(26, 7, 89.50),
(26, 10, 87.00),
(26, 13, 90.00),
(27, 2, 88.00),
(27, 5, 87.50),
(27, 8, 89.00),
(27, 11, 86.50),
(27, 14, 90.50),
(28, 3, 89.00),
(28, 6, 90.00),
(28, 9, 88.50),
(28, 12, 87.00),
(28, 15, 89.50),
(29, 1, 86.50),
(29, 4, 89.50),
(29, 7, 88.00),
(29, 10, 90.00),
(29, 13, 89.00),
(30, 2, 88.50),
(30, 5, 87.00),
(30, 8, 90.00),
(30, 11, 86.50),
(30, 14, 89.00),
(31, 3, 89.50),
(31, 6, 88.00),
(31, 9, 87.00),
(31, 12, 90.00),
(31, 15, 89.00),
(32, 1, 90.00),
(32, 4, 89.00),
(32, 7, 88.00),
(32, 10, 90.50),
(32, 13, 87.50),
(33, 2, 88.00),
(33, 5, 89.00),
(33, 8, 87.50),
(33, 11, 90.00),
(33, 14, 88.50),
(34, 3, 89.50),
(34, 6, 88.00),
(34, 9, 90.00),
(34, 12, 87.00),
(34, 15, 89.00),
(35, 1, 90.50),
(35, 4, 89.00),
(35, 7, 88.50),
(35, 10, 90.00),
(35, 13, 89.00),
(36, 2, 89.50),
(36, 5, 90.00),
(36, 8, 87.50),
(36, 11, 88.50),
(36, 14, 89.00),
(37, 3, 88.00),
(37, 6, 90.50),
(37, 9, 89.00),
(37, 12, 88.00),
(37, 15, 90.00),
(38, 1, 87.50),
(38, 4, 88.00),
(38, 7, 90.00),
(38, 10, 89.00),
(38, 13, 88.50),
(39, 2, 89.00),
(39, 5, 87.50),
(39, 8, 90.50),
(39, 11, 88.00),
(39, 14, 89.00),
(40, 3, 88.50),
(40, 6, 89.00),
(40, 9, 90.00),
(40, 12, 87.00),
(40, 15, 88.50),
(41, 1, 89.50),
(41, 4, 90.00),
(41, 7, 87.50),
(41, 10, 88.00),
(41, 13, 89.00),
(42, 2, 88.00),
(42, 5, 87.00),
(42, 8, 89.00),
(42, 11, 90.00),
(42, 14, 88.50),
(43, 3, 89.00),
(43, 6, 90.00),
(43, 9, 87.50),
(43, 12, 88.50),
(43, 15, 89.00),
(44, 1, 88.00),
(44, 4, 90.00),
(44, 7, 89.00),
(44, 10, 87.50),
(44, 13, 88.50),
(45, 2, 89.00),
(45, 5, 90.00),
(45, 8, 88.50),
(45, 11, 89.50),
(45, 14, 87.00),
(46, 3, 89.50),
(46, 6, 88.00),
(46, 9, 90.00),
(46, 12, 89.00),
(46, 15, 88.50),
(47, 1, 88.50),
(47, 4, 90.00),
(47, 7, 89.50),
(47, 10, 87.50),
(47, 13, 89.00),
(48, 2, 88.00),
(48, 5, 89.00),
(48, 8, 90.50),
(48, 11, 88.00),
(48, 14, 89.00),
(49, 3, 89.50),
(49, 6, 88.50),
(49, 9, 90.00),
(49, 12, 89.00),
(49, 15, 88.50),
(50, 1, 89.00),
(50, 4, 90.00),
(50, 7, 88.50),
(50, 10, 89.00),
(50, 13, 90.00);



--
-- TOC entry 3640 (class 0 OID 16568)
-- Dependencies: 223
-- Data for Name: sidang_ta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES
(1, '20010001', 1, 'PB1', 'Senin', '2024-12-01', '09:00:00', '09.00.0018'),
(2, '20010002', 2, 'PB2', 'Selasa', '2024-12-02', '10:00:00', '09.00.0017'),
(3, '20010003', 3, 'PU1', 'Rabu', '2024-12-03', '11:00:00', '09.00.0020'),
(4, '20010004', 4, 'PU2', 'Kamis', '2024-12-04', '13:00:00', '09.00.0020'),
(5, '20010005', 5, 'Koord', 'Jumat', '2024-12-05', '14:00:00', '09.00.0018'),
(6, '20010006', 6, 'PB1', 'Senin', '2024-12-08', '09:00:00', '09.00.0017'),
(7, '20010007', 7, 'PB2', 'Selasa', '2024-12-09', '10:00:00', '09.00.0020'),
(8, '20010008', 8, 'PU1', 'Rabu', '2024-12-10', '11:00:00', '09.00.0020'),
(9, '20010009', 9, 'PU2', 'Kamis', '2024-12-11', '13:00:00', '09.00.0018'),
(10, '20010010', 10, 'Koord', 'Jumat', '2024-12-12', '14:00:00', '09.00.0017'),
(11, '20010001', 11, 'PB1', 'Senin', '2024-12-15', '09:00:00', '09.00.0020'),
(12, '20010002', 12, 'PB2', 'Selasa', '2024-12-16', '10:00:00', '09.00.0020'),
(13, '20010003', 13, 'PU1', 'Rabu', '2024-12-17', '11:00:00', '09.00.0018'),
(14, '20010004', 14, 'PU2', 'Kamis', '2024-12-18', '13:00:00', '09.00.0017'),
(15, '20010005', 15, 'Koord', 'Jumat', '2024-12-19', '14:00:00', '09.00.0020'),
(16, '20010006', 16, 'PB1', 'Senin', '2024-12-22', '09:00:00', '09.00.0020'),
(17, '20010007', 17, 'PB2', 'Selasa', '2024-12-23', '10:00:00', '09.00.0018'),
(18, '20010008', 18, 'PU1', 'Rabu', '2024-12-24', '11:00:00', '09.00.0017'),
(19, '20010009', 19, 'PU2', 'Kamis', '2024-12-25', '13:00:00', '09.00.0020'),
(20, '20010010', 20, 'Koord', 'Jumat', '2024-12-26', '14:00:00', '09.00.0020'),
(21, '20010001', 21, 'PB1', 'Senin', '2025-01-01', '09:00:00', '09.00.0018'),
(22, '20010002', 22, 'PB2', 'Selasa', '2025-01-02', '10:00:00', '09.00.0017'),
(23, '20010003', 23, 'PU1', 'Rabu', '2025-01-03', '11:00:00', '09.00.0020'),
(24, '20010004', 24, 'PU2', 'Kamis', '2025-01-04', '13:00:00', '09.00.0020'),
(25, '20010005', 25, 'Koord', 'Jumat', '2025-01-05', '14:00:00', '09.00.0018'),
(26, '20010006', 26, 'PB1', 'Senin', '2025-01-08', '09:00:00', '09.00.0017'),
(27, '20010007', 27, 'PB2', 'Selasa', '2025-01-09', '10:00:00', '09.00.0020'),
(28, '20010008', 28, 'PU1', 'Rabu', '2025-01-10', '11:00:00', '09.00.0020'),
(29, '20010009', 29, 'PU2', 'Kamis', '2025-01-11', '13:00:00', '09.00.0018'),
(30, '20010010', 30, 'Koord', 'Jumat', '2025-01-12', '14:00:00', '09.00.0017'),
(31, '20010001', 31, 'PB1', 'Senin', '2025-01-15', '09:00:00', '09.00.0020'),
(32, '20010002', 32, 'PB2', 'Selasa', '2025-01-16', '10:00:00', '09.00.0020'),
(33, '20010003', 33, 'PU1', 'Rabu', '2025-01-17', '11:00:00', '09.00.0018'),
(34, '20010004', 34, 'PU2', 'Kamis', '2025-01-18', '13:00:00', '09.00.0017'),
(35, '20010005', 35, 'Koord', 'Jumat', '2025-01-19', '14:00:00', '09.00.0020'),
(36, '20010006', 36, 'PB1', 'Senin', '2025-01-22', '09:00:00', '09.00.0020'),
(37, '20010007', 37, 'PB2', 'Selasa', '2025-01-23', '10:00:00', '09.00.0018'),
(38, '20010008', 38, 'PU1', 'Rabu', '2025-01-24', '11:00:00', '09.00.0017'),
(39, '20010009', 39, 'PU2', 'Kamis', '2025-01-25', '13:00:00', '09.00.0020'),
(40, '20010010', 40, 'Koord', 'Jumat', '2025-01-26', '14:00:00', '09.00.0020'),
(41, '20010001', 41, 'PB1', 'Senin', '2025-02-01', '09:00:00', '09.00.0018'),
(42, '20010002', 42, 'PB2', 'Selasa', '2025-02-02', '10:00:00', '09.00.0017'),
(43, '20010003', 43, 'PU1', 'Rabu', '2025-02-03', '11:00:00', '09.00.0020'),
(44, '20010004', 44, 'PU2', 'Kamis', '2025-02-04', '13:00:00', '09.00.0020'),
(45, '20010005', 45, 'Koord', 'Jumat', '2025-02-05', '14:00:00', '09.00.0018'),
(46, '20010006', 46, 'PB1', 'Senin', '2025-02-08', '09:00:00', '09.00.0017'),
(47, '20010007', 47, 'PB2', 'Selasa', '2025-02-09', '10:00:00', '09.00.0020'),
(48, '20010008', 48, 'PU1', 'Rabu', '2025-02-10', '11:00:00', '09.00.0020'),
(49, '20010009', 49, 'PU2', 'Kamis', '2025-02-11', '13:00:00', '09.00.0018'),
(50, '20010010', 50, 'Koord', 'Jumat', '2025-02-12', '14:00:00', '09.00.0017');


--
-- TOC entry 3636 (class 0 OID 16516)
-- Dependencies: 219
-- Data for Name: tugas_akhir; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tugas_akhir (id_ta, jenis, judul, semester_akd, tahun_akd, nilai_pb1, nilai_pb2, nilai_pu1, nilai_pu2, nilai_koord, nilai_akhir, angka_akhir, id_mahasiswa) VALUES
(1, 'TA1', 'Analisis Sistem Informasi Akademik', 'Ganjil', 2023, 85.00, 88.00, 80.00, 82.50, 84.00, 83.50, 'A', '6182201001'),
(2, 'TA1', 'Pengembangan Aplikasi Mobile Pemesanan', 'Genap', 2023, 75.50, 70.00, 72.50, 74.00, 73.00, 73.20, 'B', '6182201002'),
(3, 'TA2', 'Optimasi Algoritma Pencarian Data', 'Ganjil', 2024, 90.00, 89.50, 88.00, 87.50, 89.00, 88.80, 'A-', '6182201003'),
(4, 'TA1', 'Perancangan Sistem Keamanan Jaringan', 'Genap', 2023, 80.50, 82.00, 83.50, 81.00, 82.50, 82.50, 'B+', '6182201004'),
(5, 'TA1', 'Implementasi Teknologi Blockchain', 'Ganjil', 2023, 85.50, 88.00, 86.00, 87.50, 88.50, 87.50, 'A', '6182201005'),
(6, 'TA2', 'Studi Kasus Pengelolaan Data', 'Genap', 2024, 70.50, 75.00, 72.50, 73.00, 74.00, 73.50, 'B-', '6182201006'),
(7, 'TA1', 'Penggunaan Machine Learning dalam Prediksi', 'Ganjil', 2023, 89.00, 85.50, 86.00, 88.50, 87.00, 87.40, 'A-', '6182201007'),
(8, 'TA1', 'Pengembangan Sistem E-Voting', 'Genap', 2024, 84.50, 83.00, 82.50, 85.00, 84.00, 83.80, 'B+', '6182201008'),
(9, 'TA2', 'Analisis Efisiensi Pemrograman Berbasis Web', 'Ganjil', 2023, 78.00, 76.50, 75.00, 77.50, 78.50, 77.30, 'B', '6182201009'),
(10, 'TA1', 'Perancangan Jaringan untuk Smart City', 'Genap', 2024, 92.00, 91.50, 90.00, 91.00, 91.50, 91.40, 'A', '6182201010'),
(11, 'TA1', 'Sistem Penilaian Online', 'Ganjil', 2023, 80.00, 79.50, 82.00, 81.50, 80.50, 81.00, 'B+', '6182201011'),
(12, 'TA2', 'Studi Teknologi IoT pada Pertanian', 'Genap', 2024, 74.50, 73.00, 75.50, 76.00, 75.00, 74.80, 'B', '6182201012'),
(13, 'TA1', 'Analisis Keamanan Sistem Cloud Computing', 'Ganjil', 2023, 88.00, 87.50, 89.00, 88.50, 88.00, 88.20, 'A-', '6182201013'),
(14, 'TA1', 'Pengembangan Sistem Database Terdistribusi', 'Genap', 2024, 72.50, 71.50, 74.00, 73.00, 72.50, 72.70, 'B-', '6182201014'),
(15, 'TA2', 'Implementasi Virtual Reality pada E-Learning', 'Ganjil', 2024, 91.50, 90.00, 89.50, 90.50, 91.00, 90.70, 'A', '6182201015'),
(16, 'TA1', 'Perancangan Sistem Big Data untuk E-Commerce', 'Genap', 2024, 86.00, 87.50, 85.50, 84.00, 86.50, 86.30, 'A-', '6182201016'),
(17, 'TA1', 'Pengembangan Chatbot Menggunakan AI', 'Ganjil', 2023, 83.50, 84.00, 82.50, 83.00, 84.50, 83.50, 'B+', '6182201017'),
(18, 'TA2', 'Optimalisasi Algoritma Genetik', 'Genap', 2024, 79.50, 78.00, 80.00, 81.50, 79.50, 79.90, 'B', '6182201018'),
(19, 'TA1', 'Studi Kinerja Sistem Komputasi Terdistribusi', 'Ganjil', 2023, 84.00, 83.50, 86.00, 85.50, 84.50, 84.90, 'B+', '6182201019'),
(20, 'TA1', 'Penerapan Sistem Komunikasi Antena Mikrostrip', 'Genap', 2024, 92.50, 91.00, 90.50, 91.50, 92.00, 91.50, 'A', '6182201020'),
(21, 'TA1', 'Pengembangan Sistem Pemesanan Tiket Bioskop', 'Ganjil', 2023, 88.50, 86.00, 89.00, 87.50, 88.50, 88.20, 'A-', '6182201021'),
(22, 'TA1', 'Perancangan Aplikasi Manajemen Karyawan', 'Genap', 2024, 83.00, 82.50, 84.00, 83.50, 83.50, 83.50, 'B+', '6182201022'),
(23, 'TA2', 'Implementasi Deep Learning untuk Prediksi Cuaca', 'Ganjil', 2023, 89.50, 88.00, 87.50, 89.00, 88.50, 88.50, 'A-', '6182201023'),
(24, 'TA1', 'Sistem E-Library untuk Universitas', 'Genap', 2024, 75.50, 78.00, 77.50, 76.00, 77.00, 76.60, 'B', '6182201024'),
(25, 'TA2', 'Analisis Performa Sistem IoT untuk Smart Home', 'Ganjil', 2023, 90.00, 89.50, 88.00, 87.50, 89.00, 88.80, 'A-', '6182201025'),
(26, 'TA1', 'Penerapan Sistem ERP pada Perusahaan Menengah', 'Genap', 2024, 82.00, 83.50, 84.00, 83.50, 83.50, 83.50, 'B+', '6182201026'),
(27, 'TA2', 'Optimasi Sistem Machine Learning', 'Ganjil', 2023, 88.50, 86.50, 87.00, 88.50, 87.50, 87.50, 'A-', '6182201027'),
(28, 'TA1', 'Perancangan Aplikasi Mobile untuk Fitness', 'Genap', 2024, 80.50, 79.50, 82.00, 81.50, 80.50, 81.20, 'B+', '6182201028'),
(29, 'TA1', 'Studi Penerapan Sistem Pembelajaran Berbasis AI', 'Ganjil', 2023, 86.50, 85.00, 84.50, 86.00, 85.50, 85.70, 'A-', '6182201029'),
(30, 'TA2', 'Pengembangan Sistem Smart Farming', 'Genap', 2024, 78.50, 76.50, 77.50, 79.00, 78.00, 77.80, 'B', '6182201030'),
(31, 'TA1', 'Optimasi Kinerja Server dengan Load Balancing', 'Ganjil', 2023, 88.50, 87.00, 89.50, 88.00, 88.50, 88.50, 'A-', '6182201031'),
(32, 'TA2', 'Implementasi Sistem Rekomendasi pada E-Commerce', 'Genap', 2024, 83.00, 82.50, 81.50, 83.50, 84.00, 82.90, 'B+', '6182201032'),
(33, 'TA1', 'Perancangan Sistem Absensi Berbasis QR Code', 'Ganjil', 2023, 79.50, 78.00, 80.50, 79.00, 78.50, 79.50, 'B', '6182201033'),
(34, 'TA1', 'Analisis Data Mining pada Sistem Akademik', 'Genap', 2024, 90.50, 89.00, 91.00, 90.00, 89.50, 90.00, 'A', '6182201034'),
(35, 'TA2', 'Penerapan Teknologi AR pada E-Commerce', 'Ganjil', 2023, 86.00, 85.50, 87.50, 86.50, 85.00, 86.30, 'A-', '6182201035'),
(36, 'TA1', 'Sistem Manajemen Parkir Otomatis', 'Genap', 2024, 81.00, 82.50, 80.50, 83.00, 81.50, 81.90, 'B+', '6182201036'),
(37, 'TA1', 'Penggunaan Big Data untuk Prediksi Tren Pasar', 'Ganjil', 2023, 77.50, 76.00, 78.50, 77.00, 76.50, 77.10, 'B-', '6182201037'),
(38, 'TA2', 'Pengembangan Sistem Tracking Logistik', 'Genap', 2024, 88.50, 87.00, 89.50, 88.00, 88.50, 88.50, 'A-', '6182201038'),
(39, 'TA1', 'Studi Penerapan Teknologi Drone pada Pertanian', 'Ganjil', 2023, 85.00, 84.50, 83.00, 84.00, 85.50, 84.40, 'B+', '6182201039'),
(40, 'TA2', 'Optimasi Sistem Pencarian Informasi', 'Genap', 2024, 92.00, 90.50, 91.50, 92.50, 91.00, 91.50, 'A', '6182201040'),
(41, 'TA1', 'Analisis Keamanan Data pada Sistem Cloud', 'Ganjil', 2023, 78.50, 79.00, 77.50, 80.00, 78.50, 78.90, 'B', '6182201041'),
(42, 'TA1', 'Perancangan Sistem E-Wallet untuk UMKM', 'Genap', 2024, 83.00, 84.50, 82.00, 81.50, 84.00, 83.00, 'B+', '6182201042'),
(43, 'TA2', 'Implementasi Blockchain untuk Manajemen Rantai Pasok', 'Ganjil', 2023, 89.50, 87.50, 88.00, 89.00, 88.50, 88.30, 'A-', '6182201043'),
(44, 'TA1', 'Pengembangan Sistem Informasi Manajemen Rumah Sakit', 'Genap', 2024, 85.00, 83.50, 86.00, 84.50, 85.00, 84.80, 'B+', '6182201044'),
(45, 'TA2', 'Studi Penggunaan AI dalam Prediksi Kesehatan', 'Ganjil', 2023, 90.00, 89.50, 88.50, 89.00, 90.50, 89.50, 'A', '6182201045'),
(46, 'TA1', 'Perancangan Sistem Monitoring Energi untuk Rumah Tangga', 'Genap', 2024, 77.50, 75.50, 78.00, 76.50, 77.00, 76.90, 'B-', '6182201046'),
(47, 'TA2', 'Implementasi Chatbot pada Layanan Pelanggan', 'Ganjil', 2023, 82.00, 83.50, 84.00, 82.50, 83.00, 83.00, 'B+', '6182201047'),
(48, 'TA1', 'Studi Efektivitas Algoritma Machine Learning', 'Genap', 2024, 87.00, 85.50, 88.00, 86.50, 87.50, 86.90, 'A-', '6182201048'),
(49, 'TA2', 'Perancangan Sistem Pemantauan Kesehatan Berbasis IoT', 'Ganjil', 2023, 90.00, 88.50, 89.50, 90.50, 89.00, 89.50, 'A', '6182201049'),
(50, 'TA1', 'Pengembangan Sistem Manajemen untuk Pendidikan Online', 'Genap', 2024, 80.50, 82.00, 81.50, 80.00, 81.00, 81.40, 'B+', '6182201050');



--
-- TOC entry 3649 (class 0 OID 0)
-- Dependencies: 216
-- Name: komponen_nilai_id_komp_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.komponen_nilai_id_komp_seq', 25, true);


--
-- TOC entry 3650 (class 0 OID 0)
-- Dependencies: 222
-- Name: sidang_ta_id_sidang_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sidang_ta_id_sidang_seq', 50, true);


--
-- TOC entry 3651 (class 0 OID 0)
-- Dependencies: 218
-- Name: tugas_akhir_id_ta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tugas_akhir_id_ta_seq', 50, true);


--
-- TOC entry 3479 (class 2606 OID 16566)
-- Name: dosen dosen_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dosen
    ADD CONSTRAINT dosen_email_key UNIQUE (email);


--
-- TOC entry 3481 (class 2606 OID 16564)
-- Name: dosen dosen_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dosen
    ADD CONSTRAINT dosen_pkey PRIMARY KEY (nik);


--
-- TOC entry 3473 (class 2606 OID 16499)
-- Name: komponen_nilai komponen_nilai_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.komponen_nilai
    ADD CONSTRAINT komponen_nilai_pkey PRIMARY KEY (id_komp);


--
-- TOC entry 3469 (class 2606 OID 16441)
-- Name: mahasiswa mahasiswa_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mahasiswa
    ADD CONSTRAINT mahasiswa_email_key UNIQUE (email);


--
-- TOC entry 3471 (class 2606 OID 16439)
-- Name: mahasiswa mahasiswa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mahasiswa
    ADD CONSTRAINT mahasiswa_pkey PRIMARY KEY (npm);


--
-- TOC entry 3477 (class 2606 OID 16531)
-- Name: nilai_ta nilai_ta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nilai_ta
    ADD CONSTRAINT nilai_ta_pkey PRIMARY KEY (id_ta, id_komp);


--
-- TOC entry 3483 (class 2606 OID 16573)
-- Name: sidang_ta sidang_ta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sidang_ta
    ADD CONSTRAINT sidang_ta_pkey PRIMARY KEY (id_sidang);


--
-- TOC entry 3475 (class 2606 OID 16521)
-- Name: tugas_akhir tugas_akhir_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tugas_akhir
    ADD CONSTRAINT tugas_akhir_pkey PRIMARY KEY (id_ta);


--
-- TOC entry 3485 (class 2606 OID 16537)
-- Name: nilai_ta nilai_ta_id_komp_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nilai_ta
    ADD CONSTRAINT nilai_ta_id_komp_fkey FOREIGN KEY (id_komp) REFERENCES public.komponen_nilai(id_komp) ON DELETE CASCADE;


--
-- TOC entry 3486 (class 2606 OID 16532)
-- Name: nilai_ta nilai_ta_id_ta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nilai_ta
    ADD CONSTRAINT nilai_ta_id_ta_fkey FOREIGN KEY (id_ta) REFERENCES public.tugas_akhir(id_ta) ON DELETE CASCADE;


--
-- TOC entry 3487 (class 2606 OID 16579)
-- Name: sidang_ta sidang_ta_id_ta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sidang_ta
    ADD CONSTRAINT sidang_ta_id_ta_fkey FOREIGN KEY (id_ta) REFERENCES public.tugas_akhir(id_ta) ON DELETE CASCADE;


--
-- TOC entry 3488 (class 2606 OID 16574)
-- Name: sidang_ta sidang_ta_nik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sidang_ta
    ADD CONSTRAINT sidang_ta_nik_fkey FOREIGN KEY (nik) REFERENCES public.dosen(nik) ON DELETE CASCADE;


--
-- TOC entry 3484 (class 2606 OID 16522)
-- Name: tugas_akhir tugas_akhir_id_mahasiswa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tugas_akhir
    ADD CONSTRAINT tugas_akhir_id_mahasiswa_fkey FOREIGN KEY (id_mahasiswa) REFERENCES public.mahasiswa(npm) ON DELETE CASCADE;


-- Completed on 2024-11-26 19:04:18 WIB

--
-- PostgreSQL database dump complete
--

