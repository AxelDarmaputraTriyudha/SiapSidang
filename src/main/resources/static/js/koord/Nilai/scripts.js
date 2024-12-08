window.addEventListener("DOMContentLoaded", (event) => {
    const notification = document.getElementById('notification');
    if (notification) {
        setTimeout(() => {
            notification.classList.add('hide');
        }, 1000); // Notifikasi hilang setelah 3 detik
    } else {
    }
});
