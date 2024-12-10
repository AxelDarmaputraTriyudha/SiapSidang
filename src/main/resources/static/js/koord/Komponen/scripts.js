document.getElementById('addRowButton').addEventListener('click', function() {
    // Ambil body tabel
    var tbody = document.getElementById('nilaiTableBody');
    
    // Buat elemen row baru
    var newRow = document.createElement('tr');
    
    // Kolom untuk nomor urut
    var tdNo = document.createElement('td');
    tdNo.innerHTML = tbody.rows.length + 1; // Menghitung nomor urut berdasarkan jumlah baris
    newRow.appendChild(tdNo);
  
    // Kolom untuk Nama Komponen
    var tdNama = document.createElement('td');
    var inputNama = document.createElement('input');
    inputNama.type = 'text';
    inputNama.name = 'deskripsi[]'; // Nama array untuk menangkap input dinamis
    inputNama.placeholder = 'Nama Komponen';
    tdNama.appendChild(inputNama);
    newRow.appendChild(tdNama);
  
    // Kolom untuk Bobot Penguji
    var tdPenguji = document.createElement('td');
    var inputPenguji = document.createElement('input');
    inputPenguji.type = 'number';
    inputPenguji.name = 'penguji[]'; // Nama array untuk menangkap input dinamis
    inputPenguji.placeholder = 'Bobot Penguji';
    tdPenguji.appendChild(inputPenguji);
    newRow.appendChild(tdPenguji);
  
    // Kolom untuk Bobot Pembimbing
    var tdPembimbing = document.createElement('td');
    var inputPembimbing = document.createElement('input');
    inputPembimbing.type = 'number';
    inputPembimbing.name = 'pembimbing[]'; // Nama array untuk menangkap input dinamis
    inputPembimbing.placeholder = 'Bobot Pembimbing';
    tdPembimbing.appendChild(inputPembimbing);
    newRow.appendChild(tdPembimbing);
  
    // Tambahkan row baru ke dalam tbody
    tbody.appendChild(newRow);
  });
  
  window.addEventListener("DOMContentLoaded", (event) => {
      const notification = document.getElementById('notificationError');
      if (notification) {
          setTimeout(() => {
              notification.classList.add('hide');
          }, 2000); // Notifikasi hilang setelah 3 detik
      } else {
      }
  });