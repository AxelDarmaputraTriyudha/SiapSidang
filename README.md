# SiapSidang
Tugas besar RPL : Siap Sidang
**Kelompok 3 - RPL B**
1. ⁠6182201002 - Fernando Indrawan
2. ⁠6182201017 - Tiffany Tasya Agatha
3. ⁠6182201053 - Axel Darmaputra Triyudha
4. 6182201061 - Naia Azzahra Sugandi
5. ⁠6182201101 - Hayya Vevila Faisal

## Deskripsi User dan Fitur
1. Koordinator TA
- Membuat jadwal sidang dari data tugas akhir mahasiswa yang sudah terdaftar
- Membuat komponen nilai 
- Melakukan generate BAP dari setiap sidang
- Manajemen BAP (download dan upload)
- Melihat informasi mengenai setiap sidang
- Memberikan nilai kedisplinan mahasiswa
2. Penguji
- Melihat jadwal sidang 
- Memberikan nilai
- Manajemen BAP (download dan upload)
3. Pembimbing
- Melihat jadwal sidang 
- Memberikan nilai
- Manajemen BAP (download dan upload)
- Menambahkan catatan sidang
4. Mahasiswa
- Melihat jadwal sidang
- Melihat nilai sidang
- Manajemen BAP (download dan upload)
- Melihat Catatan Sidang 

## Spesifikasi Sistem
- **Gradle** - sistem build pada proyek
- **Java** - bahasa pemrograman utama
- **PostgreSQL** - database untuk menyimpan data 
- **Spring Boot** - framework untuk membangun aplikasi berbasis java

## Persyaratan Sistem 
1. Java versi 17 
2. Gradle versi 8.0 atau terbaru
3. Database PostgreSQL

# Instalasi dan Pengaturan
## 1. Persiapan Database 
1. Masuk ke dalam PostgreSQL menggunakan terminal atau pgAdmin
2. Membuat database dengan nama `SiapSidang`
3. Run Tabel dan Dummy Data yang sudah tersedia pada folder `Database`. Lakukan proses run `DDL_SiapSidang` terlebih dahulu, kemudian `ViewQuery` 
## 2. Konfigurasi dengan Database
Buka file `src/main/resources/application.properties` dan sesuaikan dengan pengaturan database Anda.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/SiapSidang
spring.datasource.username=(username postgresql Anda)
spring.datasource.password=(password postgresql Anda)
```
## 3. Proses Build Aplikasi
Setelah konfigurasi selesai, Anda bisa membangun terlebih dahulu aplikasi menggunakan Gradle. 
Di dalam direktori proyek, buka terminal dan jalankan perintah berikut:
```
gradlew clean build
```
## 4. Proses Running Aplikasi
Setelah proses build berhasil, Anda bisa menjalankan aplikasi di dalam direktori proyek, buka terminal dan jalankan perintah berikut:
```
./gradlew bootRun
```
Aplikasi akan berjalan dan bisa akses melalui browser pada alamat `http://localhost:8080/login`. Anda akan langsung diarahakan pada halaman login dari aplikasi SiapSidang. 

# Pengujian 
Untuk menjalankan pengujian unit dan integrasi, gunakan perintah berikut:
```
./gradlew test
```
Ini akan menjalankan pengujian `SetJadwalTest` yang telah disiapkan di dalam proyek.