 // Fungsi untuk toggle password visibility
    function togglePassword() {
        const passwordField = document.getElementById('password');
        const eyeIcon = document.querySelector('.eye-icon');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            eyeIcon.textContent = '👁️'; // ubah icon untuk melihat password
        } else {
            passwordField.type = 'password';
            eyeIcon.textContent = '👁️'; // ubah icon untuk menyembunyikan password
        }
    }
    
// Event listener untuk tombol login
    document.addEventListener('DOMContentLoaded', () => {
        document.getElementById('login').addEventListener('click', (e) => {
            e.preventDefault(); // Mencegah aksi default form submission
    
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
    
            if (email && password) {
                // Arahkan ke halaman absensi.html jika nomor email dan password valid
                window.location.href = '/templates/koord/Home/tabelJadwal.html';
            } else if (email){
                alert('Masukkan password!');
            } else if (password){
                alert('Masukkan email!');
            } else {
                alert('Masukkan email dan password!');
            }
        });
    });