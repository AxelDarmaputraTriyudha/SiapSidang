--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

-- Started on 2024-11-29 09:38:58 WIB

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
    bobot numeric(4,2) NOT NULL,
    semester character varying(6),
    tahun_ajaran character varying(10)
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

INSERT INTO dosen (nik, nama, kode_nama, email, password) VALUES
('20010001', 'Vania Natalie', 'VAN', 'vania.natali@unpar.ac.id', 'password123'),
('20010002', 'Keenan', 'KLM', 'keenan.leman@unpar.ac.id', 'password124'),
('20010003', 'Pascal', 'PAN', 'pascal@unpar.ac.id', 'password125'),
('20010004', 'Mariskha', 'MTA', 'mariskha@unpar.ac.id', 'password126'),
('20010005', 'Elisati', 'ELI', 'elisatih@unpar.ac.id', 'password127'),
('20010006', 'Maria Veronica', 'MVC', 'maria.veronica@unpar.ac.id', 'password128'),
('20010007', 'Lionov', 'LNV', 'lionov@unpar.ac.id', 'password129'),
('20010008', 'Raymond', 'RCP', 'raymond.chandra@unpar.ac.id', 'password130'),
('20010009', 'Rosa De Lima', 'RDL', 'padmowati@gmail.com', 'password131'),
('20010010', 'Husnul', 'HUH', 'husnulhakim@unpar.ac.id', 'password132');


--
-- TOC entry 3634 (class 0 OID 16494)
-- Dependencies: 217
-- Data for Name: komponen_nilai; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.komponen_nilai (id_komp, deskripsi, peran, bobot, semester, tahun_ajaran) VALUES
(1, 'Tata Tulis Laporan', 'PB1', 10.00, 'Ganjil', '2023'),
(2, 'Tata Tulis Laporan', 'PB2', 10.00, 'Ganjil', '2023'),
(3, 'Tata Tulis Laporan', 'PU1', 20.00, 'Ganjil', '2023'),
(4, 'Tata Tulis Laporan', 'PU2', 10.00, 'Ganjil', '2023'),
(5, 'Presentasi', 'PB1', 20.00, 'Ganjil', '2023'),
(6, 'Presentasi', 'PB2', 20.00, 'Ganjil', '2023'),
(7, 'Presentasi', 'PU1', 30.00, 'Ganjil', '2023'),
(8, 'Presentasi', 'PU2', 30.00, 'Ganjil', '2023'),
(9, 'Poster', 'PB1', 30.00, 'Ganjil', '2023'),
(10, 'Poster', 'PB2', 30.00, 'Ganjil', '2023'),
(11, 'Poster', 'PU1', 10.00, 'Ganjil', '2023'),
(12, 'Poster', 'PU2', 20.00, 'Ganjil', '2023'),
(13, 'Tata Bahasa, Kesesuaian Topik', 'PB1', 40.00, 'Ganjil', '2023'),
(14, 'Tata Bahasa, Kesesuaian Topik', 'PB2', 40.00, 'Ganjil', '2023'),
(15, 'Tata Bahasa, Kesesuaian Topik', 'PU1', 40.00, 'Ganjil', '2023'),
(16, 'Tata Bahasa, Kesesuaian Topik', 'PU2', 40.00, 'Ganjil', '2023');




--
-- TOC entry 3632 (class 0 OID 16435)
-- Dependencies: 215
-- Data for Name: mahasiswa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO mahasiswa (npm, nama, email, password) VALUES
(6182201001, 'Ahmad Fauzi', 'ahmad.fauzi01@gmail.com', 'pass12345'),
(6182201002, 'Bella Ramadhani', 'bella.rama02@gmail.com', 'pass67890'),
(6182201003, 'Charlie Pratama', 'charlie.pratama03@gmail.com', 'securePass03'),
(6182201004, 'Dina Wijaya', 'dina.wijaya04@gmail.com', 'myPassDina04'),
(6182201005, 'Eko Saputra', 'eko.saputra05@gmail.com', 'ekoSap05'),
(6182201006, 'Fina Anggraini', 'fina.anggraini06@gmail.com', 'fina123'),
(6182201007, 'Gilang Permana', 'gilang.permana07@gmail.com', 'gilpass07'),
(6182201008, 'Hana Salsabila', 'hana.salsabila08@gmail.com', 'hanaPass08'),
(6182201009, 'Irfan Maulana', 'irfan.maulana09@gmail.com', 'irfan09pass'),
(6182201010, 'Joko Santoso', 'joko.santoso10@gmail.com', 'jokoSecure10'),
(6182201011, 'Kiki Amalia', 'kiki.amalia11@gmail.com', 'kikiPass11'),
(6182201012, 'Lutfi Rizki', 'lutfi.rizki12@gmail.com', 'lutfi12pass'),
(6182201013, 'Mega Putri', 'mega.putri13@gmail.com', 'megaPass13'),
(6182201014, 'Nanda Wijaya', 'nanda.wijaya14@gmail.com', 'nanda14secure'),
(6182201015, 'Oka Pradipta', 'oka.pradipta15@gmail.com', 'okaPass15'),
(6182201016, 'Putri Ayu', 'putri.ayu16@gmail.com', 'putri16'),
(6182201017, 'Qory Indah', 'qory.indah17@gmail.com', 'qory17pass'),
(6182201018, 'Rian Syahputra', 'rian.syahputra18@gmail.com', 'rian18secure'),
(6182201019, 'Sari Utami', 'sari.utami19@gmail.com', 'sari19'),
(6182201020, 'Tomi Harun', 'tomi.harun20@gmail.com', 'tomi20pass'),
(6182201021, 'Umi Zahra', 'umi.zahra21@gmail.com', 'umi21secure'),
(6182201022, 'Vina Lestari', 'vina.lestari22@gmail.com', 'vina22pass'),
(6182201023, 'Wahyu Hidayat', 'wahyu.hidayat23@gmail.com', 'wahyu23pass'),
(6182201024, 'Xena Alifah', 'xena.alifah24@gmail.com', 'xena24secure'),
(6182201025, 'Yoga Kurniawan', 'yoga.kurniawan25@gmail.com', 'yoga25pass'),
(6182201026, 'Zahra Aulia', 'zahra.aulia26@gmail.com', 'zahra26pass'),
(6182201027, 'Ali Akbar', 'ali.akbar27@gmail.com', 'ali27pass'),
(6182201028, 'Budi Cahyono', 'budi.cahyono28@gmail.com', 'budi28secure'),
(6182201029, 'Citra Kirana', 'citra.kirana29@gmail.com', 'citra29pass'),
(6182201030, 'Dewi Rahayu', 'dewi.rahayu30@gmail.com', 'dewi30pass'),
(6182201031, 'Erlangga Putra', 'erlangga.putra31@gmail.com', 'erlangga31secure'),
(6182201032, 'Farah Nadia', 'farah.nadia32@gmail.com', 'farah32pass'),
(6182201033, 'Gita Pratiwi', 'gita.pratiwi33@gmail.com', 'gita33pass'),
(6182201034, 'Hendra Saputra', 'hendra.saputra34@gmail.com', 'hendra34pass'),
(6182201035, 'Ida Fitriani', 'ida.fitriani35@gmail.com', 'ida35secure'),
(6182201036, 'Jamil Abdullah', 'jamil.abdullah36@gmail.com', 'jamil36pass'),
(6182201037, 'Kartika Sari', 'kartika.sari37@gmail.com', 'kartika37secure'),
(6182201038, 'Lina Marlina', 'lina.marlina38@gmail.com', 'lina38pass'),
(6182201039, 'Miko Aditya', 'miko.aditya39@gmail.com', 'miko39secure'),
(6182201040, 'Nina Aprilia', 'nina.aprilia40@gmail.com', 'nina40pass'),
(6182201041, 'Omar Faisal', 'omar.faisal41@gmail.com', 'omar41pass'),
(6182201042, 'Prita Anggraeni', 'prita.anggraeni42@gmail.com', 'prita42secure'),
(6182201043, 'Rika Nurhaliza', 'rika.nurhaliza43@gmail.com', 'rika43pass'),
(6182201044, 'Samsul Bahri', 'samsul.bahri44@gmail.com', 'samsul44pass'),
(6182201045, 'Tasya Ayunda', 'tasya.ayunda45@gmail.com', 'tasya45secure'),
(6182201046, 'Usman Hakim', 'usman.hakim46@gmail.com', 'usman46pass'),
(6182201047, 'Viona Utami', 'viona.utami47@gmail.com', 'viona47secure'),
(6182201048, 'Wulan Sari', 'wulan.sari48@gmail.com', 'wulan48pass'),
(6182201049, 'Xavier Rahman', 'xavier.rahman49@gmail.com', 'xavier49pass'),
(6182201050, 'Yuni Lestari', 'yuni.lestari50@gmail.com', 'yuni50secure');



--
-- TOC entry 3637 (class 0 OID 16527)
-- Dependencies: 220
-- Data for Name: nilai_ta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.nilai_ta (id_ta, id_komp, nilai) VALUES
(1, 1, 85),
(1, 6, 90),
(1, 3, 88),
(1, 12, 92),
(1, 5, 87),
(2, 9, 80),
(2, 13, 85),
(2, 4, 90),
(2, 11, 82),
(2, 8, 89),
(3, 2, 83),
(3, 7, 86),
(3, 10, 91),
(3, 14, 84),
(3, 16, 80),
(4, 3, 88),
(4, 5, 92),
(4, 7, 86),
(4, 15, 83),
(5, 6, 90),
(5, 8, 87),
(5, 11, 91),
(5, 9, 89),
(6, 1, 80),
(6, 4, 85),
(6, 16, 88),
(7, 10, 84),
(7, 2, 89),
(7, 12, 91),
(7, 13, 82),
(8, 5, 90),
(8, 9, 87),
(8, 3, 80),
(8, 7, 92),
(9, 14, 88),
(9, 15, 90),
(9, 8, 91),
(9, 1, 86),
(10, 13, 80),
(10, 16, 89),
(10, 2, 90),
(10, 4, 92);


--
-- TOC entry 3640 (class 0 OID 16568)
-- Dependencies: 223
-- Data for Name: sidang_ta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES
(1, 20010001, 1, 'PB1', 'Senin', '2024-12-01', '09:00:00', '09.00.0018'),
(1, 20010002, 1, 'PB2', 'Senin', '2024-12-01', '09:00:00', '09.00.0018'),
(1, 20010003, 1, 'PU1', 'Senin', '2024-12-01', '09:00:00', '09.00.0018'),
(1, 20010004, 1, 'PU2', 'Senin', '2024-12-01', '09:00:00', '09.00.0018'),
(1, 20010005, 1, 'Koordinator', 'Senin', '2024-12-01', '09:00:00', '09.00.0018'),
(2, 20010006, 2, 'PB1', 'Selasa', '2024-12-02', '10:00:00', '09.00.0017'),
(2, 20010007, 2, 'PB2', 'Selasa', '2024-12-02', '10:00:00', '09.00.0017'),
(2, 20010008, 2, 'PU1', 'Selasa', '2024-12-02', '10:00:00', '09.00.0017'),
(2, 20010009, 2, 'PU2', 'Selasa', '2024-12-02', '10:00:00', '09.00.0017'),
(2, 20010010, 2, 'Koordinator', 'Selasa', '2024-12-02', '10:00:00', '09.00.0017'),
(3, 20010001, 3, 'PB1', 'Rabu', '2024-12-03', '13:00:00', '09.00.0020'),
(3, 20010002, 3, 'PB2', 'Rabu', '2024-12-03', '13:00:00', '09.00.0020'),
(3, 20010003, 3, 'PU1', 'Rabu', '2024-12-03', '13:00:00', '09.00.0020'),
(3, 20010004, 3, 'PU2', 'Rabu', '2024-12-03', '13:00:00', '09.00.0020'),
(3, 20010005, 3, 'Koordinator', 'Rabu', '2024-12-03', '13:00:00', '09.00.0020'),
(4, 20010006, 4, 'PB1', 'Kamis', '2024-12-04', '14:00:00', '09.00.0021'),
(4, 20010007, 4, 'PB2', 'Kamis', '2024-12-04', '14:00:00', '09.00.0021'),
(4, 20010008, 4, 'PU1', 'Kamis', '2024-12-04', '14:00:00', '09.00.0021'),
(4, 20010009, 4, 'PU2', 'Kamis', '2024-12-04', '14:00:00', '09.00.0021'),
(4, 20010010, 4, 'Koordinator', 'Kamis', '2024-12-04', '14:00:00', '09.00.0021');



--
-- TOC entry 3636 (class 0 OID 16516)
-- Dependencies: 219
-- Data for Name: tugas_akhir; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tugas_akhir (id_ta, jenis, judul, semester_akd, tahun_akd, nilai_pb1, nilai_pb2, nilai_pu1, nilai_pu2, nilai_koord, nilai_akhir, angka_akhir, id_mahasiswa) VALUES
(1, 'TA1', 'Analisis Sistem Informasi Akademik', 'Ganjil', 2023, 85.00, 88.00, 80.00, 82.50, 84.00, 83.50, 'A', 6182201001),
(2, 'TA1', 'Pengembangan Aplikasi Mobile Pemesanan', 'Genap', 2023, 75.50, 70.00, 72.50, 74.00, 73.00, 73.20, 'B', 6182201002),
(3, 'TA2', 'Optimasi Algoritma Pencarian Data', 'Ganjil', 2024, 90.00, 89.50, 88.00, 87.50, 89.00, 88.80, 'A-', 6182201003),
(4, 'TA1', 'Perancangan Sistem Keamanan Jaringan', 'Genap', 2023, 80.50, 82.00, 83.50, 81.00, 82.50, 82.50, 'B+', 6182201004),
(5, 'TA1', 'Implementasi Teknologi Blockchain', 'Ganjil', 2023, 85.50, 88.00, 86.00, 87.50, 88.50, 87.50, 'A', 6182201005),
(6, 'TA2', 'Studi Kasus Pengelolaan Data', 'Genap', 2024, 70.50, 75.00, 72.50, 73.00, 74.00, 73.50, 'B-', 6182201006),
(7, 'TA1', 'Penggunaan Machine Learning dalam Prediksi', 'Ganjil', 2023, 89.00, 85.50, 86.00, 88.50, 87.00, 87.40, 'A-', 6182201007),
(8, 'TA1', 'Pengembangan Sistem E-Voting', 'Genap', 2024, 84.50, 83.00, 82.50, 85.00, 84.00, 83.80, 'B+', 6182201008),
(9, 'TA2', 'Analisis Efisiensi Pemrograman Berbasis Web', 'Ganjil', 2023, 78.00, 76.50, 75.00, 77.50, 78.50, 77.30, 'B', 6182201009),
(10, 'TA1', 'Perancangan Jaringan untuk Smart City', 'Genap', 2024, 92.00, 91.50, 90.00, 91.00, 91.50, 91.40, 'A', 6182201010),
(11, 'TA1', 'Sistem Penilaian Online', 'Ganjil', 2023, 80.00, 79.50, 82.00, 81.50, 80.50, 81.00, 'B+', 6182201011),
(12, 'TA2', 'Studi Teknologi IoT pada Pertanian', 'Genap', 2024, 74.50, 73.00, 75.50, 76.00, 75.00, 74.80, 'B', 6182201012),
(13, 'TA1', 'Analisis Keamanan Sistem Cloud Computing', 'Ganjil', 2023, 88.00, 87.50, 89.00, 88.50, 88.00, 88.20, 'A-', 6182201013),
(14, 'TA1', 'Pengembangan Sistem Database Terdistribusi', 'Genap', 2024, 72.50, 71.50, 74.00, 73.00, 72.50, 72.70, 'B-', 6182201014),
(15, 'TA2', 'Implementasi Virtual Reality pada E-Learning', 'Ganjil', 2024, 91.50, 90.00, 89.50, 90.50, 91.00, 90.70, 'A', 6182201015),
(16, 'TA1', 'Perancangan Sistem Big Data untuk E-Commerce', 'Genap', 2024, 86.00, 87.50, 85.50, 84.00, 86.50, 86.30, 'A-', 6182201016),
(17, 'TA1', 'Pengembangan Chatbot Menggunakan AI', 'Ganjil', 2023, 83.50, 84.00, 82.50, 83.00, 84.50, 83.50, 'B+', 6182201017),
(18, 'TA2', 'Optimalisasi Algoritma Genetik', 'Genap', 2024, 79.50, 78.00, 80.00, 81.50, 79.50, 79.90, 'B', 6182201018),
(19, 'TA1', 'Studi Kinerja Sistem Komputasi Terdistribusi', 'Ganjil', 2023, 84.00, 83.50, 86.00, 85.50, 84.50, 84.90, 'B+', 6182201019),
(20, 'TA1', 'Penerapan Sistem Komunikasi Antena Mikrostrip', 'Genap', 2024, 92.50, 91.00, 90.50, 91.50, 92.00, 91.50, 'A', 6182201020),
(21, 'TA1', 'Pengembangan Sistem Pemesanan Tiket Bioskop', 'Ganjil', 2023, 88.50, 86.00, 89.00, 87.50, 88.50, 88.20, 'A-', 6182201021),
(22, 'TA1', 'Perancangan Aplikasi Manajemen Karyawan', 'Genap', 2024, 83.00, 82.50, 84.00, 83.50, 83.50, 83.50, 'B+', 6182201022),
(23, 'TA2', 'Implementasi Deep Learning untuk Prediksi Cuaca', 'Ganjil', 2023, 89.50, 88.00, 87.50, 89.00, 88.50, 88.50, 'A-', 6182201023),
(24, 'TA1', 'Sistem E-Library untuk Universitas', 'Genap', 2024, 75.50, 78.00, 77.50, 76.00, 77.00, 76.60, 'B', 6182201024),
(25, 'TA2', 'Analisis Performa Sistem IoT untuk Smart Home', 'Ganjil', 2023, 90.00, 89.50, 88.00, 87.50, 89.00, 88.80, 'A-', 6182201025),
(26, 'TA1', 'Penerapan Sistem ERP pada Perusahaan Menengah', 'Genap', 2024, 82.00, 83.50, 84.00, 83.50, 83.50, 83.50, 'B+', 6182201026),
(27, 'TA2', 'Optimasi Sistem Machine Learning', 'Ganjil', 2023, 88.50, 86.50, 87.00, 88.50, 87.50, 87.50, 'A-', 6182201027),
(28, 'TA1', 'Perancangan Aplikasi Mobile untuk Fitness', 'Genap', 2024, 80.50, 79.50, 82.00, 81.50, 80.50, 81.20, 'B+', 6182201028),
(29, 'TA1', 'Studi Penerapan Sistem Pembelajaran Berbasis AI', 'Ganjil', 2023, 86.50, 85.00, 84.50, 86.00, 85.50, 85.70, 'A-', 6182201029),
(30, 'TA2', 'Pengembangan Sistem Smart Farming', 'Genap', 2024, 78.50, 76.50, 77.50, 79.00, 78.00, 77.80, 'B', 6182201030),
(31, 'TA1', 'Optimasi Kinerja Server dengan Load Balancing', 'Ganjil', 2023, 81.00, 82.50, 80.00, 81.00, 81.50, 81.20, 'B+', 6182201031),
(32, 'TA1', 'Perancangan Sistem Informasi Perpustakaan', 'Genap', 2024, 88.00, 89.00, 87.50, 88.00, 88.50, 88.20, 'A-', 6182201032),
(33, 'TA2', 'Sistem Pengendalian Keuangan dengan AI', 'Ganjil', 2024, 79.50, 80.00, 78.50, 77.00, 79.00, 78.80, 'B', 6182201033),
(34, 'TA1', 'Perancangan Aplikasi E-Commerce Berbasis Web', 'Genap', 2024, 85.00, 84.50, 83.00, 84.00, 85.00, 84.50, 'A', 6182201034),
(35, 'TA2', 'Pengembangan Game Edukasi Berbasis VR', 'Ganjil', 2023, 90.50, 91.00, 89.50, 90.00, 90.00, 90.20, 'A', 6182201035),
(36, 'TA1', 'Sistem Pembayaran Digital untuk E-Commerce', 'Genap', 2024, 86.50, 85.00, 84.50, 85.50, 86.00, 85.90, 'A-', 6182201036),
(37, 'TA1', 'Aplikasi Kesehatan Berbasis IoT', 'Ganjil', 2023, 80.50, 81.00, 79.50, 80.00, 80.50, 80.30, 'B+', 6182201037),
(38, 'TA1', 'Studi Penggunaan Sistem Cloud untuk Data Center', 'Genap', 2024, 91.00, 89.50, 90.00, 90.50, 91.00, 90.60, 'A', 6182201038),
(39, 'TA1', 'Perancangan Aplikasi Mobile untuk Transportasi', 'Ganjil', 2023, 84.50, 85.00, 83.00, 84.50, 85.00, 84.60, 'B+', 6182201039),
(40, 'TA2', 'Pengembangan Aplikasi Smart City', 'Genap', 2024, 77.50, 76.00, 75.50, 77.00, 76.50, 76.70, 'B', 6182201040),
(41, 'TA1', 'Aplikasi Pemantauan Kesehatan Mental', 'Ganjil', 2023, 88.50, 89.00, 87.00, 86.50, 87.50, 87.80, 'A-', 6182201041),
(42, 'TA1', 'Pengembangan Aplikasi Smartwatch untuk Olahraga', 'Genap', 2024, 92.00, 93.00, 90.00, 91.00, 92.50, 91.80, 'A', 6182201042),
(43, 'TA2', 'Penerapan Deep Learning dalam Pengolahan Citra', 'Ganjil', 2023, 79.00, 80.50, 78.00, 79.50, 79.50, 79.30, 'B', 6182201043),
(44, 'TA1', 'Sistem Pengelolaan Proyek dengan Agile', 'Genap', 2024, 85.00, 84.00, 83.00, 85.00, 84.00, 84.40, 'A-', 6182201044),
(45, 'TA1', 'Desain Web untuk Platform E-Learning', 'Ganjil', 2023, 77.50, 75.00, 78.00, 76.50, 77.50, 77.20, 'B', 6182201045),
(46, 'TA2', 'Pengembangan Chatbot untuk Layanan Pelanggan', 'Genap', 2024, 90.00, 89.00, 88.50, 87.50, 88.00, 88.40, 'A', 6182201046),
(47, 'TA1', 'Sistem Rekomendasi untuk E-Commerce', 'Ganjil', 2023, 84.00, 83.50, 82.00, 83.50, 83.00, 83.20, 'B+', 6182201047),
(48, 'TA1', 'Sistem Pemesanan Makanan Berbasis Aplikasi', 'Genap', 2024, 79.50, 81.00, 80.50, 78.00, 79.50, 79.80, 'B', 6182201048),
(49, 'TA2', 'Penerapan Sistem Analisis Sentimen pada Media Sosial', 'Ganjil', 2024, 91.50, 92.00, 90.00, 91.50, 91.00, 91.20, 'A', 6182201049),
(50, 'TA1', 'Pengembangan Aplikasi Pembelajaran Bahasa Asing', 'Genap', 2024, 82.50, 83.00, 81.50, 82.00, 82.50, 82.30, 'B+', 6182201050);



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
-- TOC entry 3483 (class 2606 OID 16688)
-- Name: sidang_ta sidang_ta_unique_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sidang_ta
    ADD CONSTRAINT sidang_ta_unique_key UNIQUE (id_sidang, peran);


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


-- Completed on 2024-11-29 09:38:58 WIB

--
-- PostgreSQL database dump complete
--
