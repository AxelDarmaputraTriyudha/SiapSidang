window.addEventListener("DOMContentLoaded", () => {
    // Notifikasi Error
    const notificationError = document.getElementById('notificationError');
    if (notificationError) {
        setTimeout(() => {
            notificationError.classList.add('hide');
        }, 1000); // Notifikasi hilang setelah 1 detik
    }

    // Notifikasi Success
    const notificationSuccess = document.getElementById('notificationSuccess');
    if (notificationSuccess) {
        setTimeout(() => {
            notificationSuccess.classList.add('hide');
            window.location.href = "/koord/home/tabelJadwal";
        }, 1500); // Notifikasi hilang setelah 1 detik
    }
});