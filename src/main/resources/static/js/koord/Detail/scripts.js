function previewPdf() {
    const previewUrl = '/generatePDF'; // URL untuk preview PDF
    const win = window.open(previewUrl, '_blank'); // Buka di tab baru
    if (win) {
        win.focus(); // Fokuskan pada tab baru
    } else {
        alert("Pop-up blocker mencegah membuka PDF. Harap izinkan pop-up untuk situs ini.");
    }
}
