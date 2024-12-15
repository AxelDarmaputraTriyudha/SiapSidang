function showUploadPopup() {
    document.getElementById('uploadPopup').style.display = 'block';
  }
  
  function closeUploadPopup() {
    document.getElementById('uploadPopup').style.display = 'none';
  }   
  
  window.addEventListener('load', function () {
    const notifikasi = document.getElementById('notifikasiUpload');

    // Cek apakah elemen notifikasi memiliki konten
    if (notifikasi && notifikasi.innerText.trim() !== '') {
        // Tampilkan notifikasi
        notifikasi.style.display = 'block';

        // Hilangkan notifikasi setelah 3 detik
        setTimeout(() => {
            notifikasi.style.display = 'none';
        }, 3000);
    }
  });  
  
  function showNotification(message, isError = false) {
      const notifikasi = document.getElementById("notifikasi");
      const pesan = document.getElementById("notifikasiPesan");
  
      pesan.textContent = message;
      notifikasi.style.display = "block";
  
      // Tambahkan gaya berdasarkan jenis notifikasi
      notifikasi.style.backgroundColor = isError ? "#f8d7da" : "#d4edda";
      notifikasi.style.color = isError ? "#721c24" : "#155724";
  
      // Sembunyikan notifikasi setelah 3 detik
      setTimeout(() => {
          notifikasi.style.display = "none";
      }, 3000);
  }  