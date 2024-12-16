document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("form");
  const inputs = document.querySelectorAll("input[type='text']");
  const saveButton = document.querySelector(".save-btn");

  // Add real-time validation to inputs
  document.querySelectorAll("input").forEach((input) => {
    input.addEventListener("input", () => {
      const value = parseInt(input.value);
      if (isNaN(value) || value < 0 || value > 100) {
        input.style.borderColor = "red"; // Highlight invalid inputs
      } else {
        input.style.borderColor = "blue"; // Reset border color if valid
      }
    });
  });

  // Validate all inputs when needed
  const validateInputs = () => {
    let isValid = true;
    inputs.forEach((input) => {
      const value = parseInt(input.value);
      if (isNaN(value) || value < 0 || value > 100) {
        input.style.borderColor = "red";
        isValid = false;
      }
    });
    return isValid;
  };

  // Handle form submission
  form.addEventListener("submit", async (event) => {
    event.preventDefault(); // Prevent default submission

    if (!validateInputs()) {
      alert("Harap periksa kembali nilai yang dimasukkan (0-100).");
      return;
    }

    const formData = new FormData(form);

    try {
      const response = await fetch(form.action, {
        method: "POST",
        body: formData,
      });

      if (response.ok) {
        alert("Nilai berhasil disimpan!"); // Success message
        window.location.href = `/dosen/DetailJadwal?npm=${formData.get("npm")}&peran=${formData.get("peran")}`;
      } else {
        alert("Terjadi kesalahan. Silakan coba lagi.");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Terjadi kesalahan pada jaringan. Silakan coba lagi.");
    }
  });
});
