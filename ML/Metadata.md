# Metadata Dataset Rekomendasi Film

Dataset ini berisi informasi tentang film beserta emosi yang diprediksi. Film-film ini dikelompokkan berdasarkan genre, kata kunci, dan metadata lainnya yang dapat berguna untuk rekomendasi dan analisis.

## Kolom-Kolom

- **id**: Identifikasi unik untuk setiap film.
- **title**: Judul film.
- **release_date**: Tanggal rilis film dalam format `YYYY-MM-DD`.
- **vote_average**: Rata-rata rating film (berdasarkan ulasan pengguna).
- **vote_count**: Jumlah suara yang diterima oleh film.
- **runtime**: Durasi film dalam menit.
- **adult**: Menunjukkan apakah film ini untuk orang dewasa (`True` atau `False`).
- **popularity**: Skor popularitas film, yang bisa mencerminkan jumlah penonton, minat, atau performa box office.
- **Genres**: Daftar genre yang terkait dengan film (misalnya, Aksi, Drama, Fiksi Ilmiah).
- **Keywords**: Daftar kata kunci relevan untuk film, dipisahkan dengan koma (misalnya, penyelamatan, misi, mimpi).
- **tags**: Deskripsi singkat atau ringkasan plot film.
- **predicted_emotion**: Emosi yang diprediksi terkait dengan film, berdasarkan sentimen emosional (0 = sadness, 1 = joy, 2 = love, 3 = anger, 4 = fear, 5 = surprise).

## Example Data

| id     | title            | release_date | vote_average | vote_count | runtime | adult | popularity | Genres                               | Keywords                                           | tags                                           | predicted_emotion |
|--------|------------------|--------------|--------------|------------|---------|-------|------------|--------------------------------------|--------------------------------------------------|------------------------------------------------|------------------|
| 27205  | Inception        | 2010-07-15   | 8.364        | 34495      | 148     | False | 83.952     | Action, Science Fiction, Adventure   | rescue, mission, dream, airplane, paris, france  | Cobb skilled thief commits corporate espionage... | 3                |
| 157336 | Interstellar     | 2014-11-05   | 8.417        | 32571      | 169     | False | 140.241    | Adventure, Drama, Science Fiction    | rescue, future, spacecraft, race against time... | Adventures group explorers make use newly disc... | 1                |
| 155    | The Dark Knight  | 2008-07-16   | 8.512        | 30619      | 152     | False | 130.643    | Drama, Action, Crime, Thriller       | joker, sadism, chaos, secret identity, crime f... | Batman raises stakes war crime help Lt. Jim Gordon... | 4              |
| 19995  | Avatar           | 2009-12-15   | 7.573        | 29815      | 162     | False | 79.932     | Action, Adventure, Fantasy, Science Fiction | future, society, culture clash, space travel...   | 22nd-century paraplegic marine dispatched moon... | 1                |
| 24428  | The Avengers     | 2012-04-25   | 7.710        | 29166      | 143     | False | 98.082     | Science Fiction, Action, Adventure   | New York City, superhero, shield, based on comics | Unexpected enemy emerges threatens global safety... | 1               |

## Emotion Mapping

- **0**: Sadness
- **1**: Joy
- **2**: Love
- **3**: Anger
- **4**: Fear
- **5**: Surprise

## Use Cases

Dataset ini dapat digunakan untuk berbagai keperluan, antara lain:

- **Rekomendasi Film Berdasarkan Emosi**: Merekomendasikan film berdasarkan sentimen emosional yang diprediksi dari kolom `predicted_emotion`.
- **Penyaringan Berdasarkan Genre**: Menyaring film berdasarkan genre-nya untuk rekomendasi yang lebih terpersonalisasi (misalnya, hanya merekomendasikan film aksi atau drama).
- **Analisis Film**: Menganalisis film berdasarkan kata kunci dan sentimen emosional yang terkait.
- **Analisis Popularitas**: Menyaring atau mengurutkan film berdasarkan skor popularitas.

---

### Catatan

- Kolom `predicted_emotion` berdasarkan pada model pembelajaran mesin yang memprediksi nada emosional film. Kolom ini yang digunakan untuk pencocokan dengan emosi pengguna nantinya
- Kolom `tag` adalah gabungan dari kolom `Genres`,`Keywords`,`tagline`, digunakan untuk klasifikasi emosi menggunakan model yang sudah ada
