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
  
});
