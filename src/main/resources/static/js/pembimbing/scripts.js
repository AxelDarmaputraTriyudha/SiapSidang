document.addEventListener("DOMContentLoaded", () => {
    const saveButton = document.getElementById("saveButton");
  
    saveButton.addEventListener("click", (event) => {
      const catatan = document.getElementById("catatan").value.trim();
  
      if (!catatan) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Catatan sidang tidak boleh kosong.'
        });
        event.preventDefault();
      }
    });
  });
  