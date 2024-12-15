document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const inputs = document.querySelectorAll("input[type='text']");
  const saveButton = document.querySelector(".save-btn");

  // Validate input values (0-100)
  const validateInputs = () => {
    let isValid = true;
    inputs.forEach((input) => {
      let value = parseInt(input.value);
      if (isNaN(value) || value < 0 || value > 100) {
        input.style.borderColor = "red"; // Highlight invalid inputs
        isValid = false;
      } else {
        input.style.borderColor = "#CCCCCC"; // Reset border color if valid
      }
    });
    return isValid;
  };

  // handle form submission
  form.addEventListener("submit", (event) => {
    if (!validateInputs()) {
      event.preventDefault(); // stop submission if validation fails
      alert("Harap periksa kembali nilai yang dimasukkan (0-100).");
      return;
    }

    // display success message, redirect logic
    event.preventDefault(); 
    const formData = new FormData(form);

    fetch(form.action, {
      method: "POST",
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          alert("Nilai berhasil disimpan!"); // Success message
          window.location.href = `/dosen/DetailJadwal?npm=${formData.get("npm")}&peran=${formData.get("peran")}`;
        } else {
          alert("Terjadi kesalahan. Silakan coba lagi.");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("Terjadi kesalahan pada jaringan. Silakan coba lagi.");
      });
  });
});
