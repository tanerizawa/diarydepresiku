# Prototipe Arsitektur Teknis & Skema Database "Diary Depresiku"

Dokumen ini menguraikan usulan arsitektur teknis dan skema database awal untuk aplikasi "Diary Depresiku", dirancang untuk mendukung fitur yang dibutuhkan serta skalabilitas di masa depan.

## 1. Gambaran Umum Arsitektur

Aplikasi akan mengadopsi model **Client-Server**:

*   **Client:** Aplikasi Android native yang berjalan di perangkat pengguna. Bertanggung jawab untuk antarmuka pengguna, pengumpulan data awal (input pengguna, sensor), penyimpanan lokal (offline caching), dan komunikasi dengan backend.
*   **Server (Backend):** Layanan pusat yang berjalan di cloud. Bertanggung jawab untuk otentikasi pengguna, pemrosesan data, penyimpanan data persisten di database, integrasi dengan layanan AI eksternal, logika bisnis inti, dan penyediaan API untuk aplikasi klien.

![Diagram Arsitektur Umum](placeholder_diagram_umum.png) *<-- Catatan: Ini adalah placeholder, diagram visual akan lebih baik dalam proposal final.*

## 2. Arsitektur Klien (Aplikasi Android)

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

## 3. Arsitektur Server (Backend)

*   **Bahasa/Framework:** **Python** dengan **Flask** atau **Django**. Python dipilih karena ekosistem library AI/ML yang matang. Flask lebih ringan untuk memulai, Django lebih *batteries-included*.
*   **Database:** **PostgreSQL**. Database relasional yang kuat, mendukung tipe data JSONB untuk fleksibilitas, memiliki ekstensi geospasial (PostGIS) jika diperlukan di masa depan, dan handal untuk data terstruktur.
*   **Integrasi AI:** Melalui pemanggilan API ke layanan eksternal (misal: OpenAI API). Perlu modul khusus di backend untuk mengelola request, response, dan API keys secara aman.
*   **Otentikasi & Otorisasi:** **JWT (JSON Web Tokens)**. Standar industri untuk mengamankan API antara klien dan server.
*   **Deployment:** Platform Cloud (AWS, Google Cloud, atau Azure).
    *   **Compute:** EC2 (AWS), Compute Engine (GCP), atau Virtual Machines (Azure) untuk menjalankan aplikasi Flask/Django. Opsi lain: App Engine (GCP), Elastic Beanstalk (AWS) untuk PaaS, atau Serverless Functions (Lambda/Cloud Functions) untuk endpoint API tertentu.
    *   **Database:** RDS for PostgreSQL (AWS), Cloud SQL for PostgreSQL (GCP), Azure Database for PostgreSQL.
    *   **Penyimpanan Objek:** S3 (AWS) atau Cloud Storage (GCP) untuk file statis atau backup.
*   **API:** RESTful API atau GraphQL API untuk komunikasi antara aplikasi Android dan backend.

## 4. Skema Database Awal (PostgreSQL - Konseptual)

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

## 5. Alur Data Utama

1.  **Input Pengguna/Sensor:** Aplikasi Android mengumpulkan data (entri diary, lokasi, aktivitas, detak jantung) dan menyimpannya di database Room lokal.
2.  **Sinkronisasi:** WorkManager secara berkala (atau saat online) mengirim data dari Room ke Backend API.
3.  **Pemrosesan Backend:** Backend menerima data, memvalidasi, dan menyimpannya ke database PostgreSQL.
4.  **Analisis AI:** Backend (bisa secara terjadwal atau dipicu oleh data baru) mengirim data relevan ke API layanan AI eksternal.
5.  **Penyimpanan Insight:** Hasil analisis dari AI diterima backend dan disimpan di tabel `ai_insights`.
6.  **Penyajian Data:** Aplikasi Android meminta data (entri lama, insight, rekomendasi, hasil tes) dari Backend API untuk ditampilkan ke pengguna di Dasbor, Analisis, dll.

## 6. Pertimbangan Keamanan

*   **Komunikasi:** Wajib menggunakan HTTPS (TLS/SSL) untuk semua komunikasi antara klien dan server.
*   **Otentikasi:** Menggunakan JWT untuk mengamankan endpoint API.
*   **Penyimpanan:** Enkripsi data sensitif saat disimpan (at rest), terutama data kesehatan dan catatan pribadi. Gunakan fitur enkripsi database atau enkripsi level aplikasi.
*   **API Keys:** Simpan API key layanan eksternal (seperti OpenAI) secara aman di backend (misal: environment variables, secret manager), jangan pernah disimpan di kode klien.
*   **Izin:** Terapkan mekanisme izin yang ketat di backend untuk memastikan pengguna hanya bisa mengakses datanya sendiri (kecuali ada consent eksplisit untuk berbagi dengan profesional).
*   **Validasi Input:** Lakukan validasi input di sisi klien dan server untuk mencegah injection atau data korup.
*   **Kepatuhan:** Pastikan desain mematuhi regulasi privasi data yang relevan (misal: GDPR, atau UU PDP di Indonesia).

Arsitektur ini memberikan fondasi yang solid untuk MVP dan memungkinkan skalabilitas untuk penambahan fitur dan pengguna di masa mendatang.
