window.addEventListener("DOMContentLoaded", (event) => {
    console.log("Script berjalan!");
    const notification = document.getElementById('notification');
    if (notification) {
        console.log("Notifikasi ditemukan");
        setTimeout(() => {
            notification.classList.add('hide');
            console.log("Kelas 'hide' ditambahkan");
        }, 1000); // Notifikasi hilang setelah 3 detik
    } else {
        console.log("Notifikasi tidak ditemukan");
    }
});
