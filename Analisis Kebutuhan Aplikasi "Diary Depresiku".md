# Analisis Kebutuhan Aplikasi "Diary Depresiku"

Dokumen ini merangkum kebutuhan fungsional dan non-fungsional untuk aplikasi Android "Diary Depresiku" berdasarkan permintaan pengguna.

## 1. Tujuan Aplikasi

*   Menciptakan sebuah platform digital (aplikasi Android) yang berfungsi sebagai buku harian psikologis pribadi.
*   Membantu pengguna melacak dan memahami pola emosi mereka dari waktu ke waktu.
*   Mengintegrasikan data kontekstual (lokasi, aktivitas fisik, data kesehatan) untuk analisis emosi yang lebih kaya.
*   Memanfaatkan kecerdasan buatan (AI) untuk memberikan analisis prediktif/deskriptif mengenai kondisi emosional pengguna.
*   Menyediakan konten (artikel, informasi, hiburan) yang relevan dengan kondisi emosional pengguna berdasarkan analisis AI.
*   Menawarkan alat tes psikologi terstandarisasi (contoh: BDI) untuk penilaian mandiri kondisi psikologis.
*   Menyimpan riwayat hasil tes sebagai gambaran kondisi psikologis pengguna.
*   Memfasilitasi koneksi antara pengguna (terutama yang terindikasi membutuhkan bantuan lebih lanjut) dengan psikolog profesional untuk konsultasi atau pendampingan.
*   Menjadi asisten AI sekaligus jembatan ke bantuan profesional.

## 2. Pengguna Target

*   Individu yang ingin memantau kesehatan mental dan emosional mereka.
*   Individu yang mengalami fluktuasi emosi atau gejala depresi ringan hingga sedang.
*   Individu yang tertarik menggunakan teknologi untuk self-help dan pemahaman diri.
*   Individu yang mungkin memerlukan dukungan psikologis profesional namun belum mengambil langkah.

## 3. Kebutuhan Fungsional

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

## 4. Kebutuhan Non-Fungsional

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

## 5. Rencana Pengembangan Awal

*   **Tahun 1:** Fokus pada pengembangan Minimum Viable Product (MVP) oleh pengembang tunggal atau freelance. Mencakup fitur inti: pencatatan emosi, pelacakan dasar (lokasi/aktivitas jika memungkinkan di awal), analisis AI sederhana, rekomendasi konten dasar, dan tes BDI.
*   **Tahun 2:** Jika menunjukkan potensi komersial:
    *   Scaling: Peningkatan fitur (integrasi smartwatch, alat tes tambahan, fitur profesional yang lebih lengkap).
    *   Pembentukan tim full-time.
    *   Pengembangan backend yang lebih robust.
    *   Potensi komersialisasi (model bisnis).

## 6. Pertimbangan Lain

*   **Etika AI:** Penggunaan AI harus etis, transparan mengenai kemampuannya, dan tidak menggantikan diagnosis profesional.
*   **Kemitraan:** Perlu strategi untuk menjalin kerjasama dengan psikolog profesional.
*   **Regulasi Kesehatan:** Memastikan kepatuhan terhadap regulasi terkait aplikasi kesehatan mental jika ada.
