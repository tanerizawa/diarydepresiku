# Proposal Aplikasi Android: Diary Depresiku

**Versi:** 0.1 (Draf Awal)
**Tanggal:** 3 Juni 2025

## Ringkasan Eksekutif

Proposal ini menguraikan konsep, desain, arsitektur teknis, dan rencana pengembangan untuk aplikasi Android bernama "Diary Depresiku". Aplikasi ini bertujuan untuk menjadi alat bantu digital yang komprehensif bagi individu yang ingin memantau kesehatan mental dan emosional mereka. Dengan fitur pencatatan emosi harian, pelacakan konteks (lokasi, aktivitas fisik, data biometrik), analisis berbasis kecerdasan buatan (AI), rekomendasi konten yang dipersonalisasi, alat tes psikologi terstandarisasi, dan potensi koneksi ke profesional, "Diary Depresiku" dirancang untuk memberdayakan pengguna dalam memahami diri mereka lebih baik dan mencari dukungan yang tepat saat dibutuhkan. Dokumen ini mencakup analisis kebutuhan pengguna, rancangan fitur dan alur kerja, konsep desain UI/UX yang menenangkan, prototipe arsitektur teknis yang skalabel, serta rencana pengembangan bertahap dari Minimum Viable Product (MVP) hingga potensi komersialisasi.

---




## Bagian 1: Analisis Kebutuhan

Bagian ini merangkum kebutuhan fungsional dan non-fungsional untuk aplikasi Android "Diary Depresiku" berdasarkan permintaan awal.

### 1.1 Tujuan Aplikasi

*   Menciptakan sebuah platform digital (aplikasi Android) yang berfungsi sebagai buku harian psikologis pribadi.
*   Membantu pengguna melacak dan memahami pola emosi mereka dari waktu ke waktu.
*   Mengintegrasikan data kontekstual (lokasi, aktivitas fisik, data kesehatan) untuk analisis emosi yang lebih kaya.
*   Memanfaatkan kecerdasan buatan (AI) untuk memberikan analisis prediktif/deskriptif mengenai kondisi emosional pengguna.
*   Menyediakan konten (artikel, informasi, hiburan) yang relevan dengan kondisi emosional pengguna berdasarkan analisis AI.
*   Menawarkan alat tes psikologi terstandarisasi (contoh: BDI) untuk penilaian mandiri kondisi psikologis.
*   Menyimpan riwayat hasil tes sebagai gambaran kondisi psikologis pengguna.
*   Memfasilitasi koneksi antara pengguna (terutama yang terindikasi membutuhkan bantuan lebih lanjut) dengan psikolog profesional untuk konsultasi atau pendampingan.
*   Menjadi asisten AI sekaligus jembatan ke bantuan profesional.

### 1.2 Pengguna Target

*   Individu yang ingin memantau kesehatan mental dan emosional mereka.
*   Individu yang mengalami fluktuasi emosi atau gejala depresi ringan hingga sedang.
*   Individu yang tertarik menggunakan teknologi untuk self-help dan pemahaman diri.
*   Individu yang mungkin memerlukan dukungan psikologis profesional namun belum mengambil langkah.

### 1.3 Kebutuhan Fungsional

*   **Pencatatan Emosi:**
    *   Input emosi melalui form pilihan (misal: Sedih, Gembira, Marah, Cemas, dll.).
    *   Input emosi melalui catatan teks bebas (catatan harian).
    *   Timestamp otomatis untuk setiap entri.
*   **Pelacakan Kontekstual (Otomatis/Semi-otomatis):**
    *   Pelacakan Lokasi: Mendeteksi tipe lokasi (rumah, kantor, tempat hiburan, keramaian, kafe, dll.) dengan izin pengguna.
    *   Pelacakan Aktivitas Fisik: Mengakses data kesehatan digital (misal: Google Fit, Samsung Health) untuk jumlah langkah, jenis aktivitas, durasi, dll., dengan izin pengguna.
    *   Integrasi Smartwatch: Mengakses data detak jantung (jika tersedia dan terhubung) dengan izin pengguna.
*   **Analisis AI:**
    *   Menganalisis korelasi antara emosi yang dicatat dengan data kontekstual (lokasi, aktivitas, detak jantung).
    *   Memberikan ringkasan/prediksi pola emosional pengguna.
    *   Mengidentifikasi potensi pemicu emosi tertentu.
*   **Rekomendasi Konten:**
    *   Menyajikan artikel, tips, atau konten hiburan yang relevan berdasarkan analisis AI terhadap kondisi emosional pengguna.
*   **Alat Tes Psikologi:**
    *   Integrasi alat tes terstandarisasi (minimal BDI - Beck Depression Inventory).
    *   Kemungkinan penambahan alat tes lain di masa depan.
    *   Perhitungan skor otomatis.
    *   Penyimpanan riwayat hasil tes.
*   **Fitur Profesional:**
    *   Mekanisme untuk menghubungkan pengguna dengan psikolog profesional (perlu dirinci: direktori, penjadwalan, chat/video call, dll.).
    *   Sistem berbagi data (dengan izin eksplisit pengguna) antara pengguna dan psikolog.
    *   Fitur bagi psikolog untuk memberikan feedback/advice/analisis.
*   **Manajemen Akun:**
    *   Registrasi dan Login pengguna.
    *   Pengaturan profil.
    *   Pengaturan privasi dan izin akses data (lokasi, kesehatan, dll.).

### 1.4 Kebutuhan Non-Fungsional

*   **Platform:** Android.
*   **UI/UX:**
    *   Desain antarmuka yang lembut, menenangkan, dan profesional.
    *   Navigasi yang intuitif dan mudah digunakan.
    *   Pengalaman pengguna yang mendukung dan tidak menghakimi.
*   **Konektivitas & Penyimpanan:**
    *   Aplikasi berjalan secara online.
    *   Membutuhkan koneksi internet untuk sinkronisasi data, analisis AI, dan fitur profesional.
    *   Kemampuan menyimpan data sementara secara lokal (offline caching) jika tidak ada koneksi internet, dan sinkronisasi saat kembali online.
    *   Backend server diperlukan untuk database pusat, sinkronisasi, dan komunikasi antar fitur/platform.
*   **Integrasi AI:**
    *   Menggunakan layanan AI eksternal (misal: OpenAI API atau sejenisnya) melalui token/API key.
    *   Perlu mempertimbangkan manajemen biaya dan kuota API.
*   **Keamanan & Privasi:**
    *   Keamanan data pengguna yang tinggi, terutama data sensitif (emosi, lokasi, kesehatan, hasil tes).
    *   Enkripsi data saat transit dan saat disimpan.
    *   Kepatuhan terhadap regulasi privasi data yang berlaku (misal: GDPR jika relevan, atau regulasi lokal di Indonesia).
    *   Mekanisme persetujuan (consent) yang jelas untuk pengumpulan dan penggunaan data, terutama untuk berbagi dengan profesional.
*   **Skalabilitas:**
    *   Arsitektur yang dirancang untuk dapat dikembangkan lebih lanjut (penambahan fitur, peningkatan jumlah pengguna).
*   **Performa:**
    *   Aplikasi yang responsif dan tidak menguras baterai secara berlebihan.

### 1.5 Rencana Pengembangan Awal (Ringkasan)

*   **Tahun 1:** Fokus pada pengembangan Minimum Viable Product (MVP) oleh pengembang tunggal atau freelance. Mencakup fitur inti.
*   **Tahun 2:** Jika menunjukkan potensi komersial, lakukan scaling fitur, pembentukan tim full-time, dan eksplorasi model bisnis.

### 1.6 Pertimbangan Lain

*   **Etika AI:** Penggunaan AI harus etis dan transparan.
*   **Kemitraan:** Strategi kerjasama dengan psikolog profesional.
*   **Regulasi Kesehatan:** Kepatuhan terhadap regulasi aplikasi kesehatan mental.

---




## Bagian 2: Rancangan Fitur dan Alur Kerja

Bagian ini menjelaskan fitur-fitur utama dan alur kerja pengguna (user flow) untuk aplikasi "Diary Depresiku", berdasarkan analisis kebutuhan yang telah dilakukan.

### 2.1 Fitur Utama

Aplikasi akan memiliki modul-modul fungsional berikut:

1.  **Pencatatan Harian (Diary Entry):** Inti aplikasi, tempat pengguna mencatat emosi dan pengalaman.
2.  **Pelacakan Konteks Otomatis:** Mengumpulkan data pendukung (lokasi, aktivitas, detak jantung) secara pasif.
3.  **Dasbor & Analisis AI:** Menampilkan ringkasan kondisi emosional, pola, dan insight dari AI.
4.  **Rekomendasi Konten:** Menyajikan artikel/sumber daya relevan.
5.  **Penilaian Psikologis:** Menyediakan akses ke alat tes terstandarisasi.
6.  **Koneksi Profesional:** Menghubungkan pengguna dengan psikolog (fitur lanjutan).
7.  **Pengaturan & Profil:** Manajemen akun dan preferensi pengguna.

### 2.2 Alur Kerja Pengguna (User Flow)

Berikut adalah gambaran alur kerja untuk fitur-fitur utama:

**2.2.1. Onboarding & Pengaturan Awal**

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

**2.2.2. Pencatatan Emosi Harian**

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

**2.2.3. Melihat Dasbor & Analisis**

1.  Pengguna membuka aplikasi atau navigasi ke tab 'Dasbor'.
2.  Dasbor menampilkan:
    *   Ringkasan emosi hari ini/minggu ini (misal: grafik sederhana).
    *   Entri terakhir.
    *   (Jika data tersedia) Insight singkat dari AI, misal: "Emosi positif Anda cenderung muncul saat Anda berada di taman" atau "Tingkat stres tampak lebih tinggi di hari kerja".
    *   Tombol akses cepat ke 'Tambah Catatan' dan 'Tes Psikologi'.
3.  Pengguna dapat memilih rentang waktu (harian, mingguan, bulanan) untuk melihat tren emosi.
4.  Pengguna dapat tap pada insight AI untuk melihat detail analisis (jika ada).

**2.2.4. Mengakses Rekomendasi Konten**

1.  Pengguna navigasi ke tab 'Untuk Anda' atau 'Rekomendasi'.
2.  Aplikasi menampilkan daftar artikel, tips, video singkat, atau latihan relaksasi yang dikurasi berdasarkan:
    *   Analisis pola emosi terakhir.
    *   Hasil tes psikologi (jika ada).
    *   Preferensi pengguna (jika ada).
3.  Konten dikategorikan (misal: Mengelola Stres, Meningkatkan Mood, Informasi Depresi, Relaksasi).
4.  Pengguna dapat tap untuk membaca/melihat konten.
5.  (Opsional) Pengguna dapat memberi feedback (suka/tidak suka) pada konten.

**2.2.5. Mengambil Tes Psikologi**

1.  Pengguna navigasi ke tab 'Penilaian' atau 'Tes'.
2.  Daftar tes yang tersedia ditampilkan (awal: BDI).
3.  Pengguna memilih tes yang ingin diambil.
4.  Instruksi tes ditampilkan.
5.  Pengguna menjawab pertanyaan satu per satu.
6.  Setelah selesai, skor otomatis dihitung dan ditampilkan beserta interpretasi singkat (misal: tingkat depresi ringan, sedang, berat - *disclaimer bahwa ini bukan diagnosis*).
7.  Hasil disimpan dalam riwayat tes pengguna.
8.  (Opsional) Berdasarkan hasil, AI dapat menyarankan konten relevan atau (jika skor tinggi) menyarankan untuk mempertimbangkan konsultasi profesional.

**2.2.6. Menghubungi Profesional (Fitur Lanjutan)**

*   *(Alur ini perlu dirinci lebih lanjut saat pengembangan fitur ini)*
1.  Pengguna (mungkin dipicu oleh hasil tes tinggi atau pilihan manual) navigasi ke bagian 'Bantuan Profesional'.
2.  Menampilkan daftar psikolog mitra (jika ada direktori) atau mekanisme permintaan koneksi.
3.  Pengguna memilih psikolog atau mengirim permintaan.
4.  Proses verifikasi/penjadwalan (mungkin melibatkan admin atau sistem otomatis).
5.  Pengguna memberikan izin eksplisit untuk berbagi data relevan (ringkasan emosi, hasil tes) dengan psikolog terpilih.
6.  Sesi konsultasi terjadi (melalui chat/video dalam aplikasi atau platform eksternal yang disepakati).
7.  Psikolog dapat memberikan feedback/catatan melalui aplikasi (jika fitur ini ada).

**2.2.7. Mengelola Pengaturan**

1.  Pengguna navigasi ke tab 'Profil' atau 'Pengaturan'.
2.  Pengguna dapat:
    *   Mengedit profil (nama, avatar).
    *   Mengelola izin (lokasi, kesehatan, notifikasi).
    *   Mengatur pengingat untuk mencatat emosi.
    *   Melihat kebijakan privasi dan ketentuan layanan.
    *   Mengelola akun (ubah password, hapus akun).
    *   Melihat status sinkronisasi data.

### 2.3 Pertimbangan Alur

*   **Kesederhanaan:** Alur kerja inti (pencatatan emosi) harus sangat mudah dan cepat.
*   **Privasi:** Permintaan izin dan penjelasan penggunaan data harus transparan di setiap langkah.
*   **Umpan Balik:** Memberikan umpan balik visual atau teks singkat setelah tindakan pengguna (misal: 'Catatan disimpan').
*   **Fleksibilitas:** Memungkinkan pengguna untuk menggunakan fitur sesuai kebutuhan mereka tanpa merasa terbebani.

---




## Bagian 3: Konsep Desain UI/UX

Bagian ini menguraikan konsep desain antarmuka pengguna (UI) dan pengalaman pengguna (UX) untuk aplikasi "Diary Depresiku", dengan fokus pada penciptaan suasana yang lembut, menenangkan, profesional, dan mendukung.

### 3.1 Prinsip Desain Utama

*   **Menenangkan & Mendukung:** Desain harus menciptakan rasa aman dan nyaman, menghindari elemen yang terlalu mencolok atau memicu stres.
*   **Sederhana & Intuitif:** Navigasi dan interaksi harus mudah dipahami dan digunakan, bahkan oleh pengguna yang mungkin sedang merasa tidak baik.
*   **Profesional & Terpercaya:** Tampilan harus bersih, terstruktur, dan memberikan kesan kredibilitas, terutama karena menyangkut data sensitif dan potensi interaksi profesional.
*   **Personal & Empatik:** Pengalaman pengguna harus terasa personal, dengan bahasa yang mendukung dan tidak menghakimi.
*   **Jelas & Informatif:** Informasi disajikan dengan jelas, terutama data analisis dan hasil tes, dengan visualisasi yang mudah dimengerti.

### 3.2 Palet Warna

Palet warna dipilih untuk menciptakan suasana tenang dan profesional:

*   **Primary:** Soft Blue (misal: `#A0C4FF` atau `#B2D8FF`) - Memberikan kesan tenang, damai, dan dapat dipercaya. Digunakan untuk elemen navigasi utama, header, atau tombol penting.
*   **Secondary:** Gentle Teal/Mint Green (misal: `#B5E4CF` atau `#C8F7E4`) - Melambangkan pertumbuhan, kesegaran, dan ketenangan. Digunakan untuk highlight, ilustrasi pendukung, atau latar belakang kartu.
*   **Backgrounds:** Off-white (misal: `#F8F9FA`) atau Light Grey (misal: `#E9ECEF`) - Menciptakan tampilan yang bersih, lapang, dan tidak melelahkan mata.
*   **Text Color:** Dark Grey (misal: `#495057` atau `#343A40`) - Mudah dibaca dan lebih lembut dibandingkan hitam pekat.
*   **Accent Color:** Warm Peach/Soft Coral (misal: `#FFDAB9` atau `#FFBFB5`) - Memberikan sentuhan hangat, optimisme, dan digunakan secara strategis untuk Call-to-Action (CTA) positif, ikon penting, atau elemen yang membutuhkan perhatian lembut.

### 3.3 Tipografi

*   **Font Utama:** Dipilih font Sans Serif yang modern, bersih, dan sangat mudah dibaca, seperti `Nunito Sans`, `Lato`, atau `Poppins`. Font ini memiliki karakter yang ramah namun tetap profesional.
*   **Ukuran & Hirarki:** Menggunakan ukuran font yang cukup besar untuk keterbacaan, dengan hirarki yang jelas antara judul, subjudul, dan teks isi untuk memandu mata pengguna.

### 3.4 Ikonografi & Ilustrasi

*   **Ikon:** Menggunakan gaya ikon yang sederhana, bersih, dan konsisten (misal: outline style atau soft-filled style). Ikon harus mudah dikenali maknanya.
*   **Ilustrasi:** Jika digunakan (misal: pada layar onboarding atau bagian rekomendasi), ilustrasi sebaiknya memiliki gaya yang lembut, abstrak atau semi-abstrak, dan mendukung tema ketenangan atau pertumbuhan personal. Hindari ilustrasi yang terlalu kompleks atau kekanak-kanakan.

### 3.5 Tata Letak & Navigasi

*   **Navigasi Utama:** Menggunakan Bottom Navigation Bar untuk akses cepat ke bagian utama aplikasi. Contoh tab: `Dasbor`, `Catatan`, `Analisis`, `Sumber Daya`, `Profil/Pengaturan`.
*   **Struktur Layar:** Menggunakan layout berbasis kartu (card-based layout) untuk menyajikan informasi secara terorganisir (misal: entri diary, artikel rekomendasi, hasil tes). Kartu memiliki sudut membulat (rounded corners) untuk kesan lebih lembut.
*   **Whitespace:** Penggunaan ruang kosong (whitespace) yang cukup untuk memberikan kesan lapang, mengurangi kepadatan visual, dan meningkatkan fokus pada konten.
*   **Tombol (Buttons):** Tombol utama (seperti 'Simpan Catatan' atau 'Mulai Tes') jelas terlihat dengan warna aksen atau primer, dengan teks yang jelas. Tombol sekunder memiliki tampilan yang kurang menonjol.

### 3.6 Deskripsi Layar Kunci (Konseptual)

*   **Onboarding:** Layar selamat datang dengan ilustrasi lembut, penjelasan singkat manfaat aplikasi, diikuti permintaan izin yang jelas dan bertahap.
*   **Dasbor (Layar Utama):** Tampilan ringkasan. Menampilkan grafik mood sederhana (misal: line chart mingguan), tombol CTA besar untuk 'Tambah Catatan Baru', kartu insight singkat dari AI, dan mungkin ringkasan aktivitas terakhir atau rekomendasi cepat.
*   **Pencatatan Emosi (Form):** Layar bersih dengan pilihan emosi (ikon + teks), slider intensitas (jika ada), input teks opsional, dan tombol 'Simpan' yang jelas.
*   **Pencatatan Emosi (Teks Bebas):** Editor teks sederhana dengan opsi formatting dasar, deteksi emosi AI (jika aktif) ditampilkan secara halus, tombol 'Simpan'.
*   **Analisis:** Menampilkan grafik tren emosi yang lebih detail (pilihan rentang waktu), korelasi dengan konteks (jika data tersedia) dalam bentuk visualisasi sederhana, dan daftar insight yang dihasilkan AI.
*   **Rekomendasi Konten:** Tampilan daftar kartu berisi judul artikel/konten, gambar mini (jika ada), dan ringkasan singkat. Ada filter atau kategori.
*   **Layar Tes:** Instruksi jelas, pertanyaan disajikan satu per satu atau dalam bagian yang terstruktur, indikator progres, dan layar hasil yang menampilkan skor dan interpretasi dengan disclaimer.
*   **Pengaturan:** Daftar opsi pengaturan yang terstruktur dengan baik, menggunakan toggle switch untuk izin, dan navigasi yang jelas ke bagian profil, privasi, dll.

### 3.7 Pengalaman Pengguna (UX)

*   **Bahasa:** Menggunakan bahasa yang empatik, mendukung, dan positif. Hindari istilah klinis yang kaku kecuali dalam konteks tes standar (dengan penjelasan).
*   **Umpan Balik:** Memberikan feedback instan untuk aksi pengguna (misal: animasi halus saat menyimpan catatan, pesan konfirmasi).
*   **Personalisasi:** Dasbor dan rekomendasi terasa personal berdasarkan data pengguna.
*   **Privasi & Kontrol:** Pengguna selalu merasa memiliki kontrol atas data mereka dengan pengaturan privasi yang mudah diakses dan dipahami.
*   **Aksesibilitas:** Mempertimbangkan kontras warna yang cukup, ukuran font yang dapat disesuaikan (jika memungkinkan), dan dukungan untuk fitur aksesibilitas Android.

Konsep desain ini bertujuan menciptakan aplikasi yang tidak hanya fungsional tetapi juga menjadi ruang digital yang aman dan mendukung bagi penggunanya.

---




## Bagian 4: Prototipe Arsitektur Teknis & Skema Database

Bagian ini menguraikan usulan arsitektur teknis dan skema database awal untuk aplikasi "Diary Depresiku", dirancang untuk mendukung fitur yang dibutuhkan serta skalabilitas di masa depan.

### 4.1 Gambaran Umum Arsitektur

Aplikasi akan mengadopsi model **Client-Server**:

*   **Client:** Aplikasi Android native yang berjalan di perangkat pengguna. Bertanggung jawab untuk antarmuka pengguna, pengumpulan data awal (input pengguna, sensor), penyimpanan lokal (offline caching), dan komunikasi dengan backend.
*   **Server (Backend):** Layanan pusat yang berjalan di cloud. Bertanggung jawab untuk otentikasi pengguna, pemrosesan data, penyimpanan data persisten di database, integrasi dengan layanan AI eksternal, logika bisnis inti, dan penyediaan API untuk aplikasi klien.

*(Catatan: Diagram visual arsitektur akan sangat membantu dalam proposal final untuk memperjelas hubungan antar komponen).* 

### 4.2 Arsitektur Klien (Aplikasi Android)

*   **Bahasa Pemrograman:** **Kotlin** (Modern, aman, didukung penuh oleh Google untuk pengembangan Android).
*   **Pola Arsitektur:** **MVVM (Model-View-ViewModel)**. Memisahkan UI (View) dari logika bisnis dan data (ViewModel & Model), meningkatkan testability dan maintainability.
*   **UI Toolkit:** **Jetpack Compose** (Toolkit UI deklaratif modern dari Google, mempercepat pengembangan UI dan memungkinkan desain yang kaya).
*   **Komponen Kunci & Library:**
    *   **Networking:** Retrofit atau Ktor (Untuk komunikasi HTTP yang efisien dengan backend API).
    *   **Local Storage:** Room Persistence Library (Bagian dari Jetpack, menyediakan abstraksi di atas SQLite untuk penyimpanan data lokal yang robust dan offline caching).
    *   **Background Processing:** WorkManager (Bagian dari Jetpack, untuk tugas latar belakang yang terjamin seperti sinkronisasi data, pelacakan konteks berkala).
    *   **Data Kesehatan:** Health Connect SDK / Google Fit API (Untuk mengakses data aktivitas fisik dan kesehatan lainnya dengan izin pengguna).
    *   **Lokasi:** Fused Location Provider API (Bagian dari Google Play Services, untuk mendapatkan data lokasi yang efisien).
    *   **Asynchronous Programming:** Kotlin Coroutines & Flow (Untuk menangani operasi I/O dan tugas latar belakang tanpa memblokir thread utama).
    *   **Dependency Injection:** Hilt (Bagian dari Jetpack, menyederhanakan manajemen dependensi).
    *   **Navigasi:** Jetpack Navigation Compose (Untuk mengelola alur navigasi antar layar).

### 4.3 Arsitektur Server (Backend)

*   **Bahasa/Framework:** **Python** dengan **Flask** atau **Django**. Python dipilih karena ekosistem library AI/ML yang matang. Flask lebih ringan untuk memulai, Django lebih *batteries-included*.
*   **Database:** **PostgreSQL**. Database relasional yang kuat, mendukung tipe data JSONB untuk fleksibilitas, memiliki ekstensi geospasial (PostGIS) jika diperlukan di masa depan, dan handal untuk data terstruktur.
*   **Integrasi AI:** Melalui pemanggilan API ke layanan eksternal (misal: OpenAI API). Perlu modul khusus di backend untuk mengelola request, response, dan API keys secara aman.
*   **Otentikasi & Otorisasi:** **JWT (JSON Web Tokens)**. Standar industri untuk mengamankan API antara klien dan server.
*   **Deployment:** Platform Cloud (AWS, Google Cloud, atau Azure).
    *   **Compute:** EC2 (AWS), Compute Engine (GCP), atau Virtual Machines (Azure) untuk menjalankan aplikasi Flask/Django. Opsi lain: App Engine (GCP), Elastic Beanstalk (AWS) untuk PaaS, atau Serverless Functions (Lambda/Cloud Functions) untuk endpoint API tertentu.
    *   **Database:** RDS for PostgreSQL (AWS), Cloud SQL for PostgreSQL (GCP), Azure Database for PostgreSQL.
    *   **Penyimpanan Objek:** S3 (AWS) atau Cloud Storage (GCP) untuk file statis atau backup.
*   **API:** RESTful API atau GraphQL API untuk komunikasi antara aplikasi Android dan backend.

### 4.4 Skema Database Awal (PostgreSQL - Konseptual)

```sql
-- Tabel Pengguna
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(100),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Entri Diary
CREATE TABLE diary_entries (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    entry_timestamp TIMESTAMPTZ NOT NULL,
    emotion_category VARCHAR(50), -- Misal: 'Senang', 'Sedih', 'Marah', dll.
    emotion_intensity INT CHECK (emotion_intensity >= 1 AND emotion_intensity <= 5), -- Skala 1-5
    notes TEXT,
    detected_location_type VARCHAR(100), -- Misal: 'Rumah', 'Kantor', 'Kafe'
    detected_activity_type VARCHAR(100), -- Misal: 'Berjalan', 'Duduk'
    heart_rate_bpm INT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Riwayat Lokasi (jika pelacakan detail diperlukan)
CREATE TABLE location_history (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    log_timestamp TIMESTAMPTZ NOT NULL,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6),
    location_type VARCHAR(100), -- Hasil klasifikasi
    source VARCHAR(50), -- Misal: 'GPS', 'Network'
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Riwayat Aktivitas Fisik
CREATE TABLE activity_history (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    activity_type VARCHAR(100) NOT NULL, -- Misal: 'walking', 'running', 'stationary'
    steps INT,
    distance_meters INT,
    calories_burned FLOAT,
    source VARCHAR(50), -- Misal: 'Google Fit', 'Health Connect'
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Riwayat Detak Jantung
CREATE TABLE heart_rate_history (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    log_timestamp TIMESTAMPTZ NOT NULL,
    bpm INT NOT NULL,
    source VARCHAR(50), -- Misal: 'Smartwatch'
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Definisi Tes Psikologi
CREATE TABLE psychological_tests (
    id SERIAL PRIMARY KEY,
    test_code VARCHAR(20) UNIQUE NOT NULL, -- Misal: 'BDI'
    test_name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Tabel Hasil Tes Psikologi Pengguna
CREATE TABLE test_results (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    test_id INT NOT NULL REFERENCES psychological_tests(id),
    completion_timestamp TIMESTAMPTZ NOT NULL,
    score INT NOT NULL,
    interpretation_level VARCHAR(50), -- Misal: 'Minimal', 'Ringan', 'Sedang', 'Berat'
    raw_answers JSONB, -- Menyimpan jawaban mentah jika diperlukan
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Insight dari AI
CREATE TABLE ai_insights (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    generation_timestamp TIMESTAMPTZ NOT NULL,
    insight_text TEXT NOT NULL,
    insight_type VARCHAR(50), -- Misal: 'Pola Emosi', 'Pemicu', 'Saran'
    related_entry_ids INT[], -- Array ID entri diary yang relevan
    related_test_result_id INT REFERENCES test_results(id),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Konten Rekomendasi
CREATE TABLE content_recommendations (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content_url VARCHAR(512),
    content_body TEXT, -- Jika konten disimpan internal
    content_type VARCHAR(50), -- Misal: 'Artikel', 'Video', 'Latihan'
    category VARCHAR(100),
    relevance_criteria JSONB, -- Kriteria relevansi (emosi, skor tes, dll.)
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Profesional (Untuk Fitur Masa Depan)
CREATE TABLE professionals (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    credentials TEXT,
    specialization TEXT,
    contact_info JSONB,
    profile_bio TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Hubungan Pengguna-Profesional (Untuk Fitur Masa Depan)
CREATE TABLE user_professional_connections (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    professional_id INT NOT NULL REFERENCES professionals(id) ON DELETE CASCADE,
    connection_status VARCHAR(50) DEFAULT 'pending', -- 'pending', 'active', 'inactive', 'rejected'
    data_sharing_consent BOOLEAN DEFAULT FALSE,
    consent_details JSONB, -- Rincian data apa saja yang dibagikan
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, professional_id)
);
```
*Catatan: Skema ini adalah draf awal dan mungkin perlu disesuaikan lebih lanjut selama pengembangan.* 

### 4.5 Alur Data Utama

1.  **Input Pengguna/Sensor:** Aplikasi Android mengumpulkan data (entri diary, lokasi, aktivitas, detak jantung) dan menyimpannya di database Room lokal.
2.  **Sinkronisasi:** WorkManager secara berkala (atau saat online) mengirim data dari Room ke Backend API.
3.  **Pemrosesan Backend:** Backend menerima data, memvalidasi, dan menyimpannya ke database PostgreSQL.
4.  **Analisis AI:** Backend (bisa secara terjadwal atau dipicu oleh data baru) mengirim data relevan ke API layanan AI eksternal.
5.  **Penyimpanan Insight:** Hasil analisis dari AI diterima backend dan disimpan di tabel `ai_insights`.
6.  **Penyajian Data:** Aplikasi Android meminta data (entri lama, insight, rekomendasi, hasil tes) dari Backend API untuk ditampilkan ke pengguna di Dasbor, Analisis, dll.

### 4.6 Pertimbangan Keamanan

*   **Komunikasi:** Wajib menggunakan HTTPS (TLS/SSL) untuk semua komunikasi antara klien dan server.
*   **Otentikasi:** Menggunakan JWT untuk mengamankan endpoint API.
*   **Penyimpanan:** Enkripsi data sensitif saat disimpan (at rest), terutama data kesehatan dan catatan pribadi. Gunakan fitur enkripsi database atau enkripsi level aplikasi.
*   **API Keys:** Simpan API key layanan eksternal (seperti OpenAI) secara aman di backend (misal: environment variables, secret manager), jangan pernah disimpan di kode klien.
*   **Izin:** Terapkan mekanisme izin yang ketat di backend untuk memastikan pengguna hanya bisa mengakses datanya sendiri (kecuali ada consent eksplisit untuk berbagi dengan profesional).
*   **Validasi Input:** Lakukan validasi input di sisi klien dan server untuk mencegah injection atau data korup.
*   **Kepatuhan:** Pastikan desain mematuhi regulasi privasi data yang relevan (misal: GDPR, atau UU PDP di Indonesia).

Arsitektur ini memberikan fondasi yang solid untuk MVP dan memungkinkan skalabilitas untuk penambahan fitur dan pengguna di masa mendatang.

---




## Bagian 5: Rencana Pengembangan & Strategi Scaling

Bagian ini menguraikan rencana pengembangan bertahap untuk aplikasi "Diary Depresiku", mencakup fokus untuk tahun pertama (pengembangan MVP) dan strategi scaling untuk tahun kedua, termasuk potensi model bisnis dan pengembangan tim.

### 5.1 Tahun Pertama: Fokus Pengembangan Minimum Viable Product (MVP)

**Tujuan Utama:** Meluncurkan versi awal aplikasi (MVP) yang fungsional dan stabil untuk mendapatkan pengguna awal dan feedback, serta memvalidasi konsep inti.

**Tim:**
*   **Ideal:** Pendiri (Anda) sebagai Product Owner/Manager + 1 Freelance Android Developer (dengan pengalaman backend dasar atau kemauan belajar) ATAU 1 Freelance Full-Stack Developer (Kotlin + Python/Flask).
*   **Minimal:** Pendiri (Anda) yang belajar dan mengerjakan sebagian besar pengembangan, mungkin dengan bantuan freelance untuk bagian spesifik (misal: setup backend awal, integrasi API AI).

**Fokus Fitur MVP:**
*   **Pencatatan Emosi:** Input via form (pilihan emosi, intensitas opsional) dan input teks bebas.
*   **Pelacakan Konteks Dasar:** Pelacakan tipe lokasi (menggunakan izin lokasi kasar/kota atau tipe tempat jika memungkinkan tanpa terlalu menguras baterai/kompleksitas di awal).
*   **Dasbor Sederhana:** Menampilkan entri terbaru dan ringkasan mood dasar (misal: mood rata-rata minggu ini).
*   **Analisis AI Dasar:** Integrasi awal dengan API AI (misal: OpenAI) untuk analisis sentimen sederhana dari catatan teks atau insight dasar berdasarkan korelasi sederhana (jika data cukup).
*   **Tes Psikologi:** Implementasi tes BDI (Beck Depression Inventory) dengan perhitungan skor otomatis dan interpretasi dasar (dengan disclaimer jelas).
*   **Rekomendasi Konten Dasar:** Menampilkan daftar artikel/sumber daya umum yang relevan dengan kesehatan mental (belum dipersonalisasi secara mendalam oleh AI).
*   **Fitur Inti:** Registrasi/Login, Pengaturan Profil Dasar, Manajemen Izin.

**Fokus Teknologi:**
*   **Android:** Kotlin, Jetpack Compose, Room (untuk offline cache), Retrofit/Ktor, WorkManager (untuk sinkronisasi).
*   **Backend:** Python/Flask (atau Django jika lebih familiar), PostgreSQL, JWT untuk otentikasi.
*   **Infrastruktur:** Cloud hosting dasar (misal: Heroku free/hobby tier, AWS EC2 t2.micro, Google Cloud e2-micro) untuk backend dan database.

**Timeline Tentatif (Asumsi 1 Developer Fokus):**
*   **Bulan 1-3:** Setup backend (database, API otentikasi, API entri diary dasar), Setup proyek Android (arsitektur MVVM, navigasi dasar, layar login/register).
*   **Bulan 4-6:** Implementasi fitur pencatatan emosi (form & teks) di Android, Sinkronisasi data ke backend, Layar Dasbor dasar.
*   **Bulan 7-9:** Implementasi fitur tes BDI (frontend & backend logic), Integrasi awal API AI untuk analisis sentimen/insight dasar.
*   **Bulan 10-12:** Implementasi pelacakan konteks dasar (lokasi), Fitur rekomendasi konten statis, Pengujian menyeluruh (internal/beta), Persiapan peluncuran (Google Play Store listing), Peluncuran MVP.

**Anggaran Awal (Perkiraan Kasar):**
*   Biaya Hosting Cloud: Relatif rendah di awal ($10-$50/bulan tergantung traffic dan pilihan layanan).
*   Biaya API AI: Tergantung penggunaan (OpenAI punya free tier, tapi perlu monitor). Anggarkan $20-$100/bulan sebagai awal.
*   Biaya Freelance Developer: Sangat bervariasi (misal: $20-$60+/jam tergantung lokasi dan pengalaman). Perlu anggaran signifikan jika menggunakan freelance secara ekstensif.
*   Biaya Akun Google Play Developer: $25 (satu kali).

### 5.2 Tahun Kedua: Scaling, Peningkatan Fitur, dan Potensi Komersialisasi

**Pemicu:** Traksi pengguna yang positif pada MVP, feedback yang membangun, potensi pendanaan/investasi, atau keputusan untuk masuk ke fase komersial.

**Tujuan Utama:** Meningkatkan nilai aplikasi bagi pengguna, memperluas jangkauan fitur, membangun fondasi untuk keberlanjutan (komersial), dan memperkuat tim.

**Strategi Pengembangan Tim:**
*   **Hire #1: Backend Developer Dedikasi:** Mengambil alih pengembangan dan pemeliharaan backend, optimasi database, dan integrasi API yang lebih kompleks.
*   **Hire #2: Android Developer Dedikasi (atau Lanjutan Freelance):** Fokus pada pengembangan fitur baru di Android, peningkatan UI/UX, dan optimasi performa aplikasi.
*   **Potensi Hire #3 (Paruh Waktu/Freelance Awal): UI/UX Designer:** Membantu menyempurnakan desain, memastikan konsistensi, dan merancang fitur baru dengan fokus pada pengalaman pengguna.
*   **Potensi Hire #4 (Jika AI menjadi kompleks): Data Scientist/ML Engineer:** Mengembangkan model AI internal (jika diperlukan), melakukan analisis data yang lebih mendalam, dan menyempurnakan algoritma rekomendasi.
*   **Peran Pendiri:** Transisi lebih ke arah Manajemen Produk, Strategi Bisnis, Kemitraan (dengan profesional/institusi), dan Fundraising (jika diperlukan).

**Fokus Peningkatan Fitur:**
*   **Pelacakan Konteks Lanjutan:** Integrasi Health Connect/Google Fit yang lebih dalam (langkah, aktivitas spesifik), integrasi data detak jantung dari smartwatch.
*   **Analisis AI Lanjutan:** Model AI yang lebih canggih untuk prediksi mood, identifikasi pemicu yang lebih akurat, analisis pola tidur (jika data tersedia), rekomendasi konten yang sangat dipersonalisasi.
*   **Tes Psikologi Tambahan:** Menambah alat tes terstandarisasi lainnya (misal: GAD-7 untuk kecemasan).
*   **Fitur Koneksi Profesional:** Membangun fitur direktori psikolog, penjadwalan sesi (integrasi kalender), platform komunikasi aman (chat/video - mungkin integrasi pihak ketiga), sistem consent berbagi data yang aman.
*   **Fitur Komunitas (Opsional & Hati-hati):** Forum anonim termoderasi atau grup dukungan sebaya (memerlukan moderasi ketat dan pertimbangan privasi).
*   **Gamifikasi (Opsional):** Elemen gamifikasi untuk mendorong pencatatan rutin atau penyelesaian latihan (misal: streaks, badges).

**Fokus Teknologi & Infrastruktur:**
*   **Backend:** Optimasi query database, caching, pertimbangkan arsitektur microservices jika skala menuntut, implementasi monitoring dan logging yang lebih baik (misal: Sentry, Datadog).
*   **Infrastruktur:** Upgrade ke sumber daya cloud yang lebih besar, gunakan database terkelola (Managed Database) untuk kemudahan scaling dan backup, pertimbangkan Content Delivery Network (CDN) untuk aset statis.
*   **Keamanan:** Audit keamanan rutin, implementasi praktik keamanan terbaik secara berkelanjutan.

**Potensi Model Bisnis:**
*   **Freemium:**
    *   **Gratis:** Fitur inti (pencatatan, analisis dasar, 1-2 tes, rekomendasi umum).
    *   **Premium (Subscription Bulanan/Tahunan):** Analisis AI lanjutan, insight mendalam, semua alat tes, rekomendasi konten premium/kursus mini, fitur koneksi profesional (misal: jumlah koneksi/pesan terbatas di free tier).
*   **Platform Fee untuk Profesional:** Biaya langganan bagi psikolog untuk terdaftar di direktori, menggunakan alat platform untuk manajemen klien (yang berasal dari aplikasi), dan fitur komunikasi aman.
*   **Kemitraan B2B:** Menawarkan versi white-label atau paket khusus untuk perusahaan (program kesehatan karyawan), klinik, atau universitas.
*   **Donasi (Kurang Berkelanjutan):** Memungkinkan pengguna memberikan donasi.
*   **Penting:** Hindari menjual data pengguna individual karena sifatnya yang sangat sensitif. Data agregat dan anonim mungkin bisa digunakan untuk riset (dengan consent yang sangat jelas), tetapi ini area yang kompleks secara etika dan hukum.

**Rencana ini bersifat fleksibel dan harus dievaluasi ulang secara berkala berdasarkan data penggunaan, feedback pengguna, dan kondisi pasar.**

---

## Penutup

Proposal ini menyajikan kerangka kerja awal untuk pengembangan aplikasi "Diary Depresiku". Langkah selanjutnya melibatkan validasi lebih lanjut, perancangan detail UI/UX (mockup/prototipe interaktif), dan memulai pengembangan MVP sesuai rencana. Kami yakin aplikasi ini memiliki potensi besar untuk memberikan dampak positif bagi kesehatan mental pengguna di Indonesia.

