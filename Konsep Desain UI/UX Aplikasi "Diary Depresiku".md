# Konsep Desain UI/UX Aplikasi "Diary Depresiku"

Dokumen ini menguraikan konsep desain antarmuka pengguna (UI) dan pengalaman pengguna (UX) untuk aplikasi "Diary Depresiku", dengan fokus pada penciptaan suasana yang lembut, menenangkan, profesional, dan mendukung.

## 1. Prinsip Desain Utama

*   **Menenangkan & Mendukung:** Desain harus menciptakan rasa aman dan nyaman, menghindari elemen yang terlalu mencolok atau memicu stres.
*   **Sederhana & Intuitif:** Navigasi dan interaksi harus mudah dipahami dan digunakan, bahkan oleh pengguna yang mungkin sedang merasa tidak baik.
*   **Profesional & Terpercaya:** Tampilan harus bersih, terstruktur, dan memberikan kesan kredibilitas, terutama karena menyangkut data sensitif dan potensi interaksi profesional.
*   **Personal & Empatik:** Pengalaman pengguna harus terasa personal, dengan bahasa yang mendukung dan tidak menghakimi.
*   **Jelas & Informatif:** Informasi disajikan dengan jelas, terutama data analisis dan hasil tes, dengan visualisasi yang mudah dimengerti.

## 2. Palet Warna

Palet warna dipilih untuk menciptakan suasana tenang dan profesional:

*   **Primary:** Soft Blue (misal: `#A0C4FF` atau `#B2D8FF`) - Memberikan kesan tenang, damai, dan dapat dipercaya. Digunakan untuk elemen navigasi utama, header, atau tombol penting.
*   **Secondary:** Gentle Teal/Mint Green (misal: `#B5E4CF` atau `#C8F7E4`) - Melambangkan pertumbuhan, kesegaran, dan ketenangan. Digunakan untuk highlight, ilustrasi pendukung, atau latar belakang kartu.
*   **Backgrounds:** Off-white (misal: `#F8F9FA`) atau Light Grey (misal: `#E9ECEF`) - Menciptakan tampilan yang bersih, lapang, dan tidak melelahkan mata.
*   **Text Color:** Dark Grey (misal: `#495057` atau `#343A40`) - Mudah dibaca dan lebih lembut dibandingkan hitam pekat.
*   **Accent Color:** Warm Peach/Soft Coral (misal: `#FFDAB9` atau `#FFBFB5`) - Memberikan sentuhan hangat, optimisme, dan digunakan secara strategis untuk Call-to-Action (CTA) positif, ikon penting, atau elemen yang membutuhkan perhatian lembut.

## 3. Tipografi

*   **Font Utama:** Dipilih font Sans Serif yang modern, bersih, dan sangat mudah dibaca, seperti `Nunito Sans`, `Lato`, atau `Poppins`. Font ini memiliki karakter yang ramah namun tetap profesional.
*   **Ukuran & Hirarki:** Menggunakan ukuran font yang cukup besar untuk keterbacaan, dengan hirarki yang jelas antara judul, subjudul, dan teks isi untuk memandu mata pengguna.

## 4. Ikonografi & Ilustrasi

*   **Ikon:** Menggunakan gaya ikon yang sederhana, bersih, dan konsisten (misal: outline style atau soft-filled style). Ikon harus mudah dikenali maknanya.
*   **Ilustrasi:** Jika digunakan (misal: pada layar onboarding atau bagian rekomendasi), ilustrasi sebaiknya memiliki gaya yang lembut, abstrak atau semi-abstrak, dan mendukung tema ketenangan atau pertumbuhan personal. Hindari ilustrasi yang terlalu kompleks atau kekanak-kanakan.

## 5. Tata Letak & Navigasi

*   **Navigasi Utama:** Menggunakan Bottom Navigation Bar untuk akses cepat ke bagian utama aplikasi. Contoh tab: `Dasbor`, `Catatan`, `Analisis`, `Sumber Daya`, `Profil/Pengaturan`.
*   **Struktur Layar:** Menggunakan layout berbasis kartu (card-based layout) untuk menyajikan informasi secara terorganisir (misal: entri diary, artikel rekomendasi, hasil tes). Kartu memiliki sudut membulat (rounded corners) untuk kesan lebih lembut.
*   **Whitespace:** Penggunaan ruang kosong (whitespace) yang cukup untuk memberikan kesan lapang, mengurangi kepadatan visual, dan meningkatkan fokus pada konten.
*   **Tombol (Buttons):** Tombol utama (seperti 'Simpan Catatan' atau 'Mulai Tes') jelas terlihat dengan warna aksen atau primer, dengan teks yang jelas. Tombol sekunder memiliki tampilan yang kurang menonjol.

## 6. Deskripsi Layar Kunci (Konseptual)

*   **Onboarding:** Layar selamat datang dengan ilustrasi lembut, penjelasan singkat manfaat aplikasi, diikuti permintaan izin yang jelas dan bertahap.
*   **Dasbor (Layar Utama):** Tampilan ringkasan. Menampilkan grafik mood sederhana (misal: line chart mingguan), tombol CTA besar untuk 'Tambah Catatan Baru', kartu insight singkat dari AI, dan mungkin ringkasan aktivitas terakhir atau rekomendasi cepat.
*   **Pencatatan Emosi (Form):** Layar bersih dengan pilihan emosi (ikon + teks), slider intensitas (jika ada), input teks opsional, dan tombol 'Simpan' yang jelas.
*   **Pencatatan Emosi (Teks Bebas):** Editor teks sederhana dengan opsi formatting dasar, deteksi emosi AI (jika aktif) ditampilkan secara halus, tombol 'Simpan'.
*   **Analisis:** Menampilkan grafik tren emosi yang lebih detail (pilihan rentang waktu), korelasi dengan konteks (jika data tersedia) dalam bentuk visualisasi sederhana, dan daftar insight yang dihasilkan AI.
*   **Rekomendasi Konten:** Tampilan daftar kartu berisi judul artikel/konten, gambar mini (jika ada), dan ringkasan singkat. Ada filter atau kategori.
*   **Layar Tes:** Instruksi jelas, pertanyaan disajikan satu per satu atau dalam bagian yang terstruktur, indikator progres, dan layar hasil yang menampilkan skor dan interpretasi dengan disclaimer.
*   **Pengaturan:** Daftar opsi pengaturan yang terstruktur dengan baik, menggunakan toggle switch untuk izin, dan navigasi yang jelas ke bagian profil, privasi, dll.

## 7. Pengalaman Pengguna (UX)

*   **Bahasa:** Menggunakan bahasa yang empatik, mendukung, dan positif. Hindari istilah klinis yang kaku kecuali dalam konteks tes standar (dengan penjelasan).
*   **Umpan Balik:** Memberikan feedback instan untuk aksi pengguna (misal: animasi halus saat menyimpan catatan, pesan konfirmasi).
*   **Personalisasi:** Dasbor dan rekomendasi terasa personal berdasarkan data pengguna.
*   **Privasi & Kontrol:** Pengguna selalu merasa memiliki kontrol atas data mereka dengan pengaturan privasi yang mudah diakses dan dipahami.
*   **Aksesibilitas:** Mempertimbangkan kontras warna yang cukup, ukuran font yang dapat disesuaikan (jika memungkinkan), dan dukungan untuk fitur aksesibilitas Android.

Konsep desain ini bertujuan menciptakan aplikasi yang tidak hanya fungsional tetapi juga menjadi ruang digital yang aman dan mendukung bagi penggunanya.
