document.addEventListener("DOMContentLoaded", () => {
    const namaElement = document.getElementById("nama");
    const npmElement = document.getElementById("npm");
    const judulElement = document.getElementById("judul");
    const catatanElement = document.getElementById("catatan");
    const saveButton = document.getElementById("saveButton");

    const idTugasAkhir = new URLSearchParams(window.location.search).get("idTugasAkhir");

    // Fetch data mahasiswa dan tugas akhir
    fetch(`/pembimbing/catatan/data?idTugasAkhir=${idTugasAkhir}`)
        .then(response => response.json())
        .then(data => {
            namaElement.textContent = data.nama;
            npmElement.textContent = data.npm;
            judulElement.textContent = data.judul;
        })
        .catch(error => {
            console.error("Error fetching data:", error);
        });

    // Save catatan
    saveButton.addEventListener("click", () => {
        const catatan = catatanElement.value;

        fetch(`/pembimbing/catatan/save?idTugasAkhir=${idTugasAkhir}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(catatan),
        })
            .then(response => {
                if (response.ok) {
                    alert("Catatan berhasil disimpan!");
                } else {
                    alert("Gagal menyimpan catatan.");
                }
            })
            .catch(error => {
                console.error("Error saving catatan:", error);
                alert("Terjadi kesalahan.");
            });
    });
});
