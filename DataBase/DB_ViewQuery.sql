CREATE VIEW data_sidang_view AS
SELECT
	tugas_akhir.jenis,
	mahasiswa.nama,
	tugas_akhir.semester_akd,
	tugas_akhir.tahun_akd,
	sidang_ta.waktu,
	sidang_ta.tanggal
FROM
	tugas_akhir
	JOIN mahasiswa ON mahasiswa.npm = tugas_akhir.id_mahasiswa
	JOIN sidang_ta ON sidang_ta.id_ta = tugas_akhir.id_ta;

CREATE VIEW lihat_komponen AS
SELECT
    deskripsi,
    MAX(bobot) FILTER (WHERE peran = 'PB1') AS bobot_ketua_penguji,
    MAX(bobot) FILTER (WHERE peran = 'PB2') AS bobot_anggota_penguji,
    MAX(bobot) FILTER (WHERE peran = 'PU1') AS bobot_pembimbing, 
	semester_akd,
	tahun_akd
FROM
    komponen_nilai
GROUP BY
    deskripsi, semester_akd, tahun_akd;