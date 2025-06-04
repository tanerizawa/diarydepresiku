# Rancangan Fitur dan Alur Kerja Aplikasi "Diary Depresiku"

Dokumen ini menjelaskan fitur-fitur utama dan alur kerja pengguna (user flow) untuk aplikasi "Diary Depresiku", berdasarkan analisis kebutuhan yang telah dilakukan.

## 1. Fitur Utama

Aplikasi akan memiliki modul-modul fungsional berikut:

1.  **Pencatatan Harian (Diary Entry):** Inti aplikasi, tempat pengguna mencatat emosi dan pengalaman.
2.  **Pelacakan Konteks Otomatis:** Mengumpulkan data pendukung (lokasi, aktivitas, detak jantung) secara pasif.
3.  **Dasbor & Analisis AI:** Menampilkan ringkasan kondisi emosional, pola, dan insight dari AI.
4.  **Rekomendasi Konten:** Menyajikan artikel/sumber daya relevan.
5.  **Penilaian Psikologis:** Menyediakan akses ke alat tes terstandarisasi.
6.  **Koneksi Profesional:** Menghubungkan pengguna dengan psikolog (fitur lanjutan).
7.  **Pengaturan & Profil:** Manajemen akun dan preferensi pengguna.

## 2. Alur Kerja Pengguna (User Flow)

Berikut adalah gambaran alur kerja untuk fitur-fitur utama:

**2.1. Onboarding & Pengaturan Awal**

1.  **Buka Aplikasi Pertama Kali:** Pengguna disambut dengan layar perkenalan singkat tentang tujuan aplikasi.
2.  **Registrasi/Login:** Pengguna membuat akun baru (email/password atau Google/Apple Sign-in) atau masuk ke akun yang sudah ada.
3.  **Permintaan Izin:** Aplikasi meminta izin yang diperlukan secara bertahap dan dengan penjelasan yang jelas:
    *   Izin Lokasi (Always Allow/While Using App) -> Untuk mendeteksi tipe lokasi.
    *   Izin Aktivitas Fisik (Google Fit/Health Connect) -> Untuk data langkah, dll.
    *   Izin Notifikasi -> Untuk pengingat atau insight.
    *   (Opsional) Izin Koneksi ke Smartwatch/Wearable -> Untuk data detak jantung.
    *   Pengguna dapat menolak izin, namun dijelaskan dampaknya pada fitur tertentu.
4.  **Pengaturan Profil Awal:** (Opsional) Mengisi nama panggilan, avatar.
5.  **Pengantar Singkat Fitur:** Tur singkat (opsional) mengenai cara menggunakan fitur utama.

**2.2. Pencatatan Emosi Harian**

*   **Alur 1: Input Cepat (Form)**
    1.  Pengguna menekan tombol '+' atau 'Tambah Catatan Baru' di layar utama (Dasbor).
    2.  Muncul layar input cepat.
    3.  Pengguna memilih emosi utama dari daftar ikon/label (misal: Senang, Sedih, Marah, Cemas, Biasa Saja, dll.).
    4.  (Opsional) Pengguna bisa menambahkan intensitas emosi (misal: slider 1-5).
    5.  (Opsional) Pengguna bisa menambahkan tag/aktivitas singkat yang berkaitan (misal: #kerja, #keluarga, #olahraga).
    6.  (Opsional) Pengguna bisa menambahkan catatan teks singkat.
    7.  Pengguna menekan 'Simpan'. Data tersimpan dengan timestamp otomatis.
*   **Alur 2: Input Teks Bebas (Catatan Diary)**
    1.  Pengguna memilih opsi 'Tulis Catatan Lengkap' atau sejenisnya.
    2.  Muncul editor teks rich-text sederhana.
    3.  Pengguna menuliskan pengalamannya secara bebas.
    4.  (Otomatis/Disarankan AI) Aplikasi mencoba mendeteksi emosi dominan dari teks.
    5.  (Opsional) Pengguna mengkonfirmasi/mengubah emosi yang terdeteksi AI.
    6.  (Opsional) Pengguna menambahkan tag.
    7.  Pengguna menekan 'Simpan'. Data tersimpan dengan timestamp otomatis.

**2.3. Melihat Dasbor & Analisis**

1.  Pengguna membuka aplikasi atau navigasi ke tab 'Dasbor'.
2.  Dasbor menampilkan:
    *   Ringkasan emosi hari ini/minggu ini (misal: grafik sederhana).
    *   Entri terakhir.
    *   (Jika data tersedia) Insight singkat dari AI, misal: "Emosi positif Anda cenderung muncul saat Anda berada di taman" atau "Tingkat stres tampak lebih tinggi di hari kerja".
    *   Tombol akses cepat ke 'Tambah Catatan' dan 'Tes Psikologi'.
3.  Pengguna dapat memilih rentang waktu (harian, mingguan, bulanan) untuk melihat tren emosi.
4.  Pengguna dapat tap pada insight AI untuk melihat detail analisis (jika ada).

**2.4. Mengakses Rekomendasi Konten**

1.  Pengguna navigasi ke tab 'Untuk Anda' atau 'Rekomendasi'.
2.  Aplikasi menampilkan daftar artikel, tips, video singkat, atau latihan relaksasi yang dikurasi berdasarkan:
    *   Analisis pola emosi terakhir.
    *   Hasil tes psikologi (jika ada).
    *   Preferensi pengguna (jika ada).
3.  Konten dikategorikan (misal: Mengelola Stres, Meningkatkan Mood, Informasi Depresi, Relaksasi).
4.  Pengguna dapat tap untuk membaca/melihat konten.
5.  (Opsional) Pengguna dapat memberi feedback (suka/tidak suka) pada konten.

**2.5. Mengambil Tes Psikologi**

1.  Pengguna navigasi ke tab 'Penilaian' atau 'Tes'.
2.  Daftar tes yang tersedia ditampilkan (awal: BDI).
3.  Pengguna memilih tes yang ingin diambil.
4.  Instruksi tes ditampilkan.
5.  Pengguna menjawab pertanyaan satu per satu.
6.  Setelah selesai, skor otomatis dihitung dan ditampilkan beserta interpretasi singkat (misal: tingkat depresi ringan, sedang, berat - *disclaimer bahwa ini bukan diagnosis*).
7.  Hasil disimpan dalam riwayat tes pengguna.
8.  (Opsional) Berdasarkan hasil, AI dapat menyarankan konten relevan atau (jika skor tinggi) menyarankan untuk mempertimbangkan konsultasi profesional.

**2.6. Menghubungi Profesional (Fitur Lanjutan)**

*   *(Alur ini perlu dirinci lebih lanjut saat pengembangan fitur ini)*
1.  Pengguna (mungkin dipicu oleh hasil tes tinggi atau pilihan manual) navigasi ke bagian 'Bantuan Profesional'.
2.  Menampilkan daftar psikolog mitra (jika ada direktori) atau mekanisme permintaan koneksi.
3.  Pengguna memilih psikolog atau mengirim permintaan.
4.  Proses verifikasi/penjadwalan (mungkin melibatkan admin atau sistem otomatis).
5.  Pengguna memberikan izin eksplisit untuk berbagi data relevan (ringkasan emosi, hasil tes) dengan psikolog terpilih.
6.  Sesi konsultasi terjadi (melalui chat/video dalam aplikasi atau platform eksternal yang disepakati).
7.  Psikolog dapat memberikan feedback/catatan melalui aplikasi (jika fitur ini ada).

**2.7. Mengelola Pengaturan**

1.  Pengguna navigasi ke tab 'Profil' atau 'Pengaturan'.
2.  Pengguna dapat:
    *   Mengedit profil (nama, avatar).
    *   Mengelola izin (lokasi, kesehatan, notifikasi).
    *   Mengatur pengingat untuk mencatat emosi.
    *   Melihat kebijakan privasi dan ketentuan layanan.
    *   Mengelola akun (ubah password, hapus akun).
    *   Melihat status sinkronisasi data.

## 3. Pertimbangan Alur

*   **Kesederhanaan:** Alur kerja inti (pencatatan emosi) harus sangat mudah dan cepat.
*   **Privasi:** Permintaan izin dan penjelasan penggunaan data harus transparan di setiap langkah.
*   **Umpan Balik:** Memberikan umpan balik visual atau teks singkat setelah tindakan pengguna (misal: 'Catatan disimpan').
*   **Fleksibilitas:** Memungkinkan pengguna untuk menggunakan fitur sesuai kebutuhan mereka tanpa merasa terbebani.
