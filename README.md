# Diary Depresiku

Diary Depresiku adalah prototipe aplikasi pencatatan suasana hati (mood diary) berbasis Android dengan dukungan backend Flask. Proyek ini bertujuan membantu pengguna melacak emosi sehari-hari, menganalisis pola emosi, dan menyediakan insight atau rekomendasi terkait kesehatan mental.

## Fitur Utama

- **Pencatatan Harian**: Pengguna dapat mencatat emosi, intensitas, serta catatan teks tiap hari.
- **Pelacakan Konteks**: Rencana integrasi data lokasi, aktivitas fisik, dan sensor lain untuk memperkaya analisis.
- **Dasbor & Analisis AI**: Ringkasan mood dan insight berdasarkan data yang terkumpul.
- **Tes Psikologis**: Menyediakan tes standar (misalnya BDI) untuk penilaian mandiri.
- **Rekomendasi Konten**: Artikel atau sumber daya yang relevan dengan kondisi pengguna.
- **Koneksi Profesional**: Penghubung ke psikolog profesional (fitur lanjutan).

## Struktur Proyek

Repositori berisi beberapa komponen:

- **Dokumentasi** (`*.md`)
  - Analisis kebutuhan, rancangan fitur, rencana pengembangan, dan konsep desain UI/UX.
  - Skema arsitektur teknis dan struktur proyek Android/Backend.
- **Backend Flask** (`backend_prototype/`)
  - Folder `app/` memuat `models.py` serta blueprint di `routes/`.
  - Konfigurasi berada di `config/settings.py` dan server dijalankan via `run.py`.
- **Prototype Android (Kotlin)** (`android_client_prototype/`)
  - Mengikuti pola MVVM dengan Jetpack Compose.
  - Kode sumber berada di `app/src/main/java/com/example/diarydepresiku`.

## Menjalankan Backend

1. Masuk ke direktori `backend_prototype`.
2. Buat dan aktifkan virtual environment.
3. Install dependensi: `pip install -r requirements.txt`.
4. Atur variabel lingkungan (`SECRET_KEY`, `JWT_SECRET_KEY`, `DATABASE_URL`).
5. Jalankan server dengan `python run.py`.

## Status Pengembangan

Repositori ini masih bersifat prototipe awal. Commit terbaru menambahkan berbagai dokumen perencanaan serta kode contoh untuk backend Flask dan klien Android. Ke depan, fungsionalitas akan diperluas serta dilakukan integrasi lebih lengkap dengan layanan AI dan penyimpanan data.

Lisensi proyek menggunakan MIT License.
