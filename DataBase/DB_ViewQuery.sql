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
	sidang_ta.tempat
FROM
	tugas_akhir
	JOIN mahasiswa ON mahasiswa.npm = tugas_akhir.id_mahasiswa
	JOIN sidang_ta ON sidang_ta.id_ta = tugas_akhir.id_ta;

CREATE VIEW last_id_sidang AS 
SELECT id_sidang FROM sidang_ta ORDER BY id_sidang DESC LIMIT 1;