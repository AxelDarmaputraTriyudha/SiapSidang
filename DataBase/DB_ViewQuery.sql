CREATE VIEW data_sidang_view AS
SELECT
	tugas_akhir.jenis,
	tugas_akhir.judul,
	mahasiswa.nama,
	mahasiswa.npm,
	tugas_akhir.semester_akd,
	tugas_akhir.tahun_akd,
	sidang_ta.waktu,
	sidang_ta.tanggal,
	sidang_ta.tempat,
	sidang_ta.nik,
	dosen.nama AS nama_dosen,
	sidang_ta.peran	
FROM
	tugas_akhir
	JOIN mahasiswa ON mahasiswa.npm = tugas_akhir.id_mahasiswa
	JOIN sidang_ta ON sidang_ta.id_ta = tugas_akhir.id_ta
	JOIN dosen ON dosen.nik = sidang_ta.nik;

CREATE VIEW last_id_sidang AS 
SELECT id_sidang FROM sidang_ta ORDER BY id_sidang DESC LIMIT 1;


CREATE VIEW komponen_nilai_view AS
SELECT 
    DISTINCT(kn.deskripsi)
FROM 
    Tugas_akhir ta
JOIN 
    Komponen_nilai kn
ON 
    ta.semester_akd = kn.semester
    AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran;

CREATE VIEW komponen_nilai_view2 AS
SELECT 
    DISTINCT(kn.deskripsi), ta.nilai_pu1
FROM 
    Tugas_akhir ta
JOIN 
    Komponen_nilai kn
ON 
    ta.semester_akd = kn.semester
    AND ta.tahun_akd::VARCHAR = kn.tahun_ajaran;

CREATE VIEW data_mahasiswa AS
SELECT 
    ms.npm, ms.nama, ta.judul, ta.id_ta
FROM 
    Mahasiswa ms
JOIN 
    Tugas_akhir ta
ON 
    ms.npm = ta.id_mahasiswa
    AND ms.npm::VARCHAR = ta.id_mahasiswa;
    
CREATE VIEW bap_view AS
SELECT
	tugas_akhir.jenis,
	tugas_akhir.judul,
	mahasiswa.nama,
	mahasiswa.npm,
	tugas_akhir.semester_akd,
	tugas_akhir.tahun_akd,
	sidang_ta.waktu,
	sidang_ta.tanggal,
	sidang_ta.tempat,
	sidang_ta.nik,
	dosen.nama AS nama_dosen,
	sidang_ta.peran, 
	tugas_akhir.nilai_pb1,
	tugas_akhir.nilai_pu1,
	tugas_akhir.nilai_pu2,
	tugas_akhir.nilai_koord
FROM
	tugas_akhir
	JOIN mahasiswa ON mahasiswa.npm = tugas_akhir.id_mahasiswa
	JOIN sidang_ta ON sidang_ta.id_ta = tugas_akhir.id_ta
	JOIN dosen ON dosen.nik = sidang_ta.nik;
  
CREATE VIEW view_sidang_mahasiswa AS
SELECT 
    m.npm,
    m.nama,
    ta.id_ta,
	ta.judul,
    s.id_sidang,
    s.catatan_sidang
FROM mahasiswa m
JOIN tugas_akhir ta ON m.npm = ta.id_mahasiswa
JOIN sidang_ta s ON ta.id_ta = s.id_ta;
