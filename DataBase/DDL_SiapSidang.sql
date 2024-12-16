-- UNTUK MEMASTIKAN BAHWA TIDAK ADA VIEW DAN TABEL DENGAN NAMA YANG SAMA
DO $$ 
BEGIN
    EXECUTE (
        SELECT string_agg('DROP VIEW IF EXISTS "' || schemaname || '"."' || viewname || '" CASCADE;', ' ')
        FROM pg_views
        WHERE schemaname NOT IN ('pg_catalog', 'information_schema') -- Exclude system schemas
    );
END $$;

DROP TABLE IF EXISTS Nilai_ta;
DROP TABLE IF EXISTS Komponen_nilai;
DROP TABLE IF EXISTS Sidang_ta;
DROP TABLE IF EXISTS Dosen;
DROP TABLE IF EXISTS Mahasiswa;
DROP TABLE IF EXISTS Tugas_akhir;


CREATE TABLE Mahasiswa (
    npm VARCHAR(10) PRIMARY KEY  NOT NULL,
    nama VARCHAR(50) NOT NULL,
    email VARCHAR(60),
    password VARCHAR(50) NOT NULL
);


CREATE TABLE Dosen (
    nik VARCHAR(20) PRIMARY KEY,
    nama VARCHAR(50) NOT NULL,
    kode_nama VARCHAR(5),
    email VARCHAR(60),
    password VARCHAR(50) NOT NULL,
    isKoord BIT
);

CREATE TABLE Komponen_nilai (
    id_komp SERIAL PRIMARY KEY,
    deskripsi VARCHAR(100) NOT NULL,
    bobotPenguji NUMERIC(4,2) NOT NULL,
    bobotPembimbing NUMERIC(4,2) NOT NULL,
    semester VARCHAR(6),
    tahun_ajaran VARCHAR(10)
);

CREATE TABLE Tugas_akhir (
    id_ta SERIAL PRIMARY KEY,
    jenis VARCHAR(3) NOT NULL,
    judul VARCHAR(255) NOT NULL,
    semester_akd VARCHAR(6) NOT NULL,
    tahun_akd INTEGER NOT NULL,
    nilai_pb1 NUMERIC(5,2),
    nilai_pu1 NUMERIC(5,2),
    nilai_pu2 NUMERIC(5,2),
    nilai_koord NUMERIC(5,2),
    nilai_akhir NUMERIC(5,2),
    angka_akhir CHAR(2),
    id_mahasiswa VARCHAR(10)
);

CREATE TABLE Nilai_ta (
    id_ta INTEGER NOT NULL,
    id_komp INTEGER NOT NULL,
    nilaiPelnguji1 NUMERIC(5,2) NOT NULL,
    nilaiPenguji2 NUMERIC(5,2) NOT NULL,
    nilaiPembimbing1 NUMERIC(5,2) NOT NULL,
    PRIMARY KEY (id_ta, id_komp),
    FOREIGN KEY (id_komp) REFERENCES Komponen_nilai (id_komp),
    FOREIGN KEY (id_ta) REFERENCES Tugas_akhir (id_ta)
);

CREATE TABLE Sidang_ta (
    id_sidang INTEGER NOT NULL,
    nik VARCHAR(20),
    id_ta INTEGER,
    peran VARCHAR(15),
    hari VARCHAR(10),
    tanggal DATE NOT NULL,
    waktu TIME NOT NULL,
    tempat VARCHAR(15),
    catatan_sidang VARCHAR(100),
    status_bap BOOLEAN,
    PRIMARY KEY (id_sidang,peran),
    FOREIGN KEY (nik) REFERENCES Dosen(nik),
    FOREIGN KEY (id_ta) REFERENCES Tugas_akhir(id_ta)
);

INSERT INTO Dosen (nik, nama, kode_nama, email, password, isKoord) VALUES
('20010001', 'Vania Natalie', 'VAN', 'vania.natali@unpar.ac.id', 'password123',B'1'),
('20010002', 'Keenan', 'KLM', 'keenan.leman@unpar.ac.id', 'password124', B'0'),
('20010003', 'Pascal', 'PAN', 'pascal@unpar.ac.id', 'password125', B'0'),
('20010004', 'Mariskha', 'MTA', 'mariskha@unpar.ac.id', 'password126', B'0'),
('20010005', 'Elisati', 'ELI', 'elisatih@unpar.ac.id', 'password127', B'0'),
('20010006', 'Maria Veronica', 'MVC', 'maria.veronica@unpar.ac.id', 'password128', B'0'),
('20010007', 'Lionov', 'LNV', 'lionov@unpar.ac.id', 'password129', B'0'),
('20010008', 'Raymond', 'RCP', 'raymond.chandra@unpar.ac.id', 'password130', B'0'),
('20010009', 'Rosa De Lima', 'RDL', 'padmowati@gmail.com', 'password131', B'0'),
('20010010', 'Husnul', 'HUH', 'husnulhakim@unpar.ac.id', 'password132', B'0');


INSERT INTO Mahasiswa (npm, nama, email, password) VALUES
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

INSERT INTO Komponen_nilai (deskripsi, bobotPenguji, bobotPembimbing, semester, tahun_ajaran) VALUES
('Tata Tulis Laporan', 10.00, 10.00, 'Ganjil', '2023'),
('Tata Bahasa', 20.00, 20.00, 'Ganjil', '2023'),
('Presentasi', 20.00, 20.00, 'Ganjil', '2023'),
('Pemahaman Materi', 30.00, 30.00, 'Ganjil', '2023'),
('Poster', 10.00, 10.00, 'Ganjil', '2023'),
('Kemampuan Tanya Jawab', 10.00, 10.00, 'Ganjil', '2023');

INSERT INTO tugas_akhir (id_ta, jenis, judul, semester_akd, tahun_akd, nilai_pb1, nilai_pu1, nilai_pu2, nilai_koord, nilai_akhir, angka_akhir, id_mahasiswa) VALUES
(1, 'TA2', 'Analisis Sistem Informasi Akademik', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201001),
(2, 'TA2', 'Pengembangan Aplikasi Mobile Pemesanan', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201002),
(3, 'TA2', 'Optimasi Algoritma Pencarian Data', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201003),
(4, 'TA2', 'Perancangan Sistem Keamanan Jaringan', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201004),
(5, 'TA2', 'Implementasi Teknologi Blockchain', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201005),
(6, 'TA2', 'Studi Kasus Pengelolaan Data', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201006),
(7, 'TA2', 'Penggunaan Machine Learning dalam Prediksi', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201007),
(8, 'TA2', 'Pengembangan Sistem E-Voting', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201008),
(9, 'TA2', 'Analisis Efisiensi Pemrograman Berbasis Web', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201009),
(10, 'TA2', 'Perancangan Jaringan untuk Smart City', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201010),
(11, 'TA2', 'Sistem Penilaian Online', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201011),
(12, 'TA2', 'Studi Teknologi IoT pada Pertanian', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201012),
(13, 'TA2', 'Analisis Keamanan Sistem Cloud Computing', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201013),
(14, 'TA2', 'Pengembangan Sistem Database Terdistribusi', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201014),
(15, 'TA2', 'Implementasi Virtual Reality pada E-Learning', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201015),
(16, 'TA2', 'Perancangan Sistem Big Data untuk E-Commerce', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201016),
(17, 'TA2', 'Pengembangan Chatbot Menggunakan AI', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201017),
(18, 'TA2', 'Optimalisasi Algoritma Genetik', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201018),
(19, 'TA2', 'Studi Kinerja Sistem Komputasi Terdistribusi', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201019),
(20, 'TA2', 'Penerapan Sistem Komunikasi Antena Mikrostrip', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201020),
(21, 'TA2', 'Pengembangan Sistem Pemesanan Tiket Bioskop', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201021),
(22, 'TA2', 'Perancangan Aplikasi Manajemen Karyawan', 'Ganjil', 2023, 0, 0, 0, 0, 0, '-', 6182201022);

INSERT INTO Sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES
(1, 20010002, 1, 'PB1', 'Senin', '2023-11-27', '09:00:00', '9018'),
(1, 20010003, 1, 'PU1', 'Senin', '2023-11-27', '09:00:00', '9018'),
(1, 20010004, 1, 'PU2', 'Senin', '2023-11-27', '09:00:00', '9018'),
(1, 20010001, 1, 'Koordinator', 'Senin', '2023-11-27', '09:00:00', '9018'),
(2, 20010005, 2, 'PB1', 'Selasa', '2023-11-28', '10:00:00', '9019'),
(2, 20010006, 2, 'PU1', 'Selasa', '2023-11-28', '10:00:00', '9019'),
(2, 20010007, 2, 'PU2', 'Selasa', '2023-11-28', '10:00:00', '9019'),
(2, 20010001, 2, 'Koordinator', 'Selasa', '2023-11-28', '10:00:00', '9019'),
(3, 20010008, 3, 'PB1', 'Rabu', '2023-11-29', '13:00:00', '9020'),
(3, 20010009, 3, 'PU1', 'Rabu', '2023-11-29', '13:00:00', '9020'),
(3, 20010010, 3, 'PU2', 'Rabu', '2023-11-29', '13:00:00', '9020'),
(3, 20010001, 3, 'Koordinator', 'Rabu', '2023-11-29', '13:00:00', '9020'),
(4, 20010005, 4, 'PB1', 'Kamis', '2023-11-30', '14:00:00', '9021'),
(4, 20010006, 4, 'PU1', 'Kamis', '2023-11-30', '14:00:00', '9021'),
(4, 20010002, 4, 'PU2', 'Kamis', '2023-11-30', '14:00:00', '9021'),
(4, 20010001, 4, 'Koordinator', 'Kamis', '2023-11-30', '14:00:00', '9021'),
(5, 20010003, 5, 'PB1', 'Jumat', '2023-12-01', '09:00:00', '9018'),
(5, 20010004, 5, 'PU1', 'Jumat', '2023-12-01', '09:00:00', '9018'),
(5, 20010005, 5, 'PU2', 'Jumat', '2023-12-01', '09:00:00', '9018'),
(5, 20010001, 5, 'Koordinator', 'Jumat', '2023-12-01', '09:00:00', '9018'),
(6, 20010006, 6, 'PB1', 'Senin', '2023-12-04', '10:00:00', '9019'),
(6, 20010007, 6, 'PU1', 'Senin', '2023-12-04', '10:00:00', '9019'),
(6, 20010008, 6, 'PU2', 'Senin', '2023-12-04', '10:00:00', '9019'),
(6, 20010001, 6, 'Koordinator', 'Senin', '2023-12-04', '10:00:00', '9019'),
(7, 20010009, 7, 'PB1', 'Selasa', '2023-12-05', '13:00:00', '9020'),
(7, 20010010, 7, 'PU1', 'Selasa', '2023-12-05', '13:00:00', '9020'),
(7, 20010002, 7, 'PU2', 'Selasa', '2023-12-05', '13:00:00', '9020'),
(7, 20010001, 7, 'Koordinator', 'Selasa', '2023-12-05', '13:00:00', '9020'),
(8, 20010003, 8, 'PB1', 'Rabu', '2023-12-06', '14:00:00', '9021'),
(8, 20010004, 8, 'PU1', 'Rabu', '2023-12-06', '14:00:00', '9021'),
(8, 20010005, 8, 'PU2', 'Rabu', '2023-12-06', '14:00:00', '9021'),
(8, 20010001, 8, 'Koordinator', 'Rabu', '2023-12-06', '14:00:00', '9021'),
(9, 20010006, 9, 'PB1', 'Kamis', '2023-12-07', '09:00:00', '9018'),
(9, 20010007, 9, 'PU1', 'Kamis', '2023-12-07', '09:00:00', '9018'),
(9, 20010008, 9, 'PU2', 'Kamis', '2023-12-07', '09:00:00', '9018'),
(9, 20010001, 9, 'Koordinator', 'Kamis', '2023-12-07', '09:00:00', '9018'),
(10, 20010002, 10, 'PB1', 'Jumat', '2023-12-08', '10:00:00', '9019'),
(10, 20010003, 10, 'PU1', 'Jumat', '2023-12-08', '10:00:00', '9019'),
(10, 20010004, 10, 'PU2', 'Jumat', '2023-12-08', '10:00:00', '9019'),
(10, 20010001, 10, 'Koordinator', 'Jumat', '2023-12-08', '10:00:00', '9019'),
(11, 20010005, 11, 'PB1', 'Senin', '2023-12-11', '13:00:00', '9020'),
(11, 20010006, 11, 'PU1', 'Senin', '2023-12-11', '13:00:00', '9020'),
(11, 20010007, 11, 'PU2', 'Senin', '2023-12-11', '13:00:00', '9020'),
(11, 20010001, 11, 'Koordinator', 'Senin', '2023-12-11', '13:00:00', '9020'),
(12, 20010008, 12, 'PB1', 'Selasa', '2023-12-12', '14:00:00', '9021'),
(12, 20010009, 12, 'PU1', 'Selasa', '2023-12-12', '14:00:00', '9021'),
(12, 20010010, 12, 'PU2', 'Selasa', '2023-12-12', '14:00:00', '9021'),
(12, 20010001, 12, 'Koordinator', 'Selasa', '2023-12-12', '14:00:00', '9021');

INSERT INTO Nilai_ta (id_ta, id_komp, nilaiPenguji1, nilaiPenguji2, nilaiPembimbing1) VALUES
(1, 1, 0, 0, 0),
(1, 2, 0, 0, 0),
(1, 3, 0, 0, 0),
(1, 4, 0, 0, 0),
(1, 5, 0, 0, 0),
(1, 6, 0, 0, 0),

(2, 1, 0, 0, 0),
(2, 2, 0, 0, 0),
(2, 3, 0, 0, 0),
(2, 4, 0, 0, 0),
(2, 5, 0, 0, 0),
(2, 6, 0, 0, 0),

(3, 1, 0, 0, 0),
(3, 2, 0, 0, 0),
(3, 3, 0, 0, 0),
(3, 4, 0, 0, 0),
(3, 5, 0, 0, 0),
(3, 6, 0, 0, 0),

(4, 1, 0, 0, 0),
(4, 2, 0, 0, 0),
(4, 3, 0, 0, 0),
(4, 4, 0, 0, 0),
(4, 5, 0, 0, 0),
(4, 6, 0, 0, 0),

(5, 1, 0, 0, 0),
(5, 2, 0, 0, 0),
(5, 3, 0, 0, 0),
(5, 4, 0, 0, 0),
(5, 5, 0, 0, 0),
(5, 6, 0, 0, 0),

(6, 1, 0, 0, 0),
(6, 2, 0, 0, 0),
(6, 3, 0, 0, 0),
(6, 4, 0, 0, 0),
(6, 5, 0, 0, 0),
(6, 6, 0, 0, 0),

(7, 1, 0, 0, 0),
(7, 2, 0, 0, 0),
(7, 3, 0, 0, 0),
(7, 4, 0, 0, 0),
(7, 5, 0, 0, 0),
(7, 6, 0, 0, 0),

(8, 1, 0, 0, 0),
(8, 2, 0, 0, 0),
(8, 3, 0, 0, 0),
(8, 4, 0, 0, 0),
(8, 5, 0, 0, 0),
(8, 6, 0, 0, 0),

(9, 1, 0, 0, 0),
(9, 2, 0, 0, 0),
(9, 3, 0, 0, 0),
(9, 4, 0, 0, 0),
(9, 5, 0, 0, 0),
(9, 6, 0, 0, 0),

(10, 1, 0, 0, 0),
(10, 2, 0, 0, 0),
(10, 3, 0, 0, 0),
(10, 4, 0, 0, 0),
(10, 5, 0, 0, 0),
(10, 6, 0, 0, 0),

(11, 1, 0, 0, 0),
(11, 2, 0, 0, 0),
(11, 3, 0, 0, 0),
(11, 4, 0, 0, 0),
(11, 5, 0, 0, 0),
(11, 6, 0, 0, 0),

(12, 1, 0, 0, 0),
(12, 2, 0, 0, 0),
(12, 3, 0, 0, 0),
(12, 4, 0, 0, 0),
(12, 5, 0, 0, 0),
(12, 6, 0, 0, 0);