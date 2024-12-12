document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const inputs = document.querySelectorAll("input[type='text']");
    const saveButton = document.querySelector(".save-btn");
    
    // Validasi input angka (0-100)
    inputs.forEach((input) => {
      input.addEventListener("input", () => {
        let value = parseInt(input.value);
        if (isNaN(value) || value < 0 || value > 100) {
          input.style.borderColor = "red";
        } else {
          input.style.borderColor = "#CCCCCC";
        }
      });
    });
  
    // Aksi saat klik tombol simpan
    saveButton.addEventListener("click", (event) => {
      event.preventDefault();
  
      let isValid = true;
  
      inputs.forEach((input) => {
        let value = parseInt(input.value);
        if (isNaN(value) || value < 0 || value > 100) {
          isValid = false;
        }
      });
  
      if (isValid) {
        // Konfirmasi penyimpanan
        const confirmed = confirm("Apakah Anda yakin ingin menyimpan data ini?");
        if (confirmed) {
          alert("Data berhasil disimpan!");
          // Anda bisa tambahkan logika pengiriman data ke server di sini.
          
        }
      } else {
        alert("Pastikan semua nilai diisi dengan angka antara 0 hingga 100.");
      }
    });
  });
  