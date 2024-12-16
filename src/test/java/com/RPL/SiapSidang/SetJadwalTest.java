package com.RPL.SiapSidang;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.RPL.SiapSidang.Koord.BuatJadwal.KoordImplementation;
import com.RPL.SiapSidang.Koord.BuatJadwal.Sidang;

@SpringBootTest
class SetJadwalTest {
	private JdbcTemplate jdbcTemplate;
	private KoordImplementation koordImplementation;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		jdbcTemplate = mock(JdbcTemplate.class); // Mock JdbcTemplate
		koordImplementation = new KoordImplementation(jdbcTemplate); // Berikan jdbcTemplate ke koordImplementation
	}

	@Test
	public void testSetJadwal() {
		// Mock data sidang yang akan dimasukkan
		Sidang koord = new Sidang(0, "NIK001", 1, "Koordinator", "Senin", LocalDate.of(2024, 12, 15), LocalTime.of(9, 0), "Ruang A");
		Sidang penguji1 = new Sidang(0, "NIK002", 1, "PU1", "Senin", LocalDate.of(2024, 12, 15), LocalTime.of(9, 0), "Ruang A");
		Sidang penguji2 = new Sidang(0, "NIK003", 1, "PU2", "Senin", LocalDate.of(2024, 12, 15), LocalTime.of(9, 0), "Ruang A");
		Sidang pembimbing1 = new Sidang(0, "NIK004", 1, "PB1", "Senin", LocalDate.of(2024, 12, 15), LocalTime.of(9, 0), "Ruang A");
		Sidang pembimbing2 = new Sidang(0, "NIK005", 1, "PB2", "Senin", LocalDate.of(2024, 12, 15), LocalTime.of(9, 0), "Ruang A");

		when(jdbcTemplate.queryForObject("SELECT * FROM last_id_sidang;", Integer.class)).thenReturn(100);

		when(jdbcTemplate.update(
				eq("INSERT INTO sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
				anyInt(), anyString(), anyInt(), anyString(), anyString(), any(LocalDate.class), any(LocalTime.class), anyString()
		)).thenReturn(1); // Jika proses insert berhasil, maka akan return 1 dan terus bertambah 

		// Test method setJadwal 
		koordImplementation.setJadwal(koord, penguji1, penguji2, pembimbing1, pembimbing2);

		// Proses verifikasi insert yang dilakukan
		verify(jdbcTemplate, times(5)).update(
				eq("INSERT INTO sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
				anyInt(), anyString(), anyInt(), anyString(), anyString(), any(LocalDate.class), any(LocalTime.class), anyString()
		);
	}
}
