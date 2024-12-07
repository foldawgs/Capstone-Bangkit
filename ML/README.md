# Cara Kerja File
Setiap file dalam proyek ini memiliki fungsi dan peran spesifik yang mendukung keseluruhan sistem. Berikut adalah penjelasan bagaimana semua file bekerja:

## 1. app.py
File utama yang mengimplementasikan Flask untuk menjalankan aplikasi berbasis API.
Fungsi utama:
Route /predict: Mengambil teks input dari pengguna, memprosesnya melalui pipeline (normalisasi teks, tokenisasi, prediksi emosi), lalu memberikan rekomendasi film berdasarkan emosi yang diprediksi.
## 2. model/model.tflite
Model TensorFlow Lite yang dilatih menggunakan arsitektur LSTM untuk klasifikasi emosi.
Input: Tensor dengan shape (1, 45) yang merepresentasikan urutan token dari teks pengguna.
Output: Tensor dengan shape (1, 6) yang menunjukkan probabilitas enam kelas emosi.
## 3. model/tokenizer.pkl
Tokenizer yang telah dilatih untuk mengubah teks menjadi urutan token yang dapat diproses oleh model.
Fungsi:
Mengonversi teks input menjadi angka berbasis indeks kata dalam kosakata model.
Menjamin bahwa teks input sesuai dengan format yang diperlukan oleh model.
## 4. model/fix_dataset.pkl
Dataset film yang telah diproses dan disimpan dalam format pickle.
Berisi informasi berikut:
Judul film (title).
Popularitas film (popularity).
Label emosi (predicted_emotion) yang digunakan untuk mencocokkan film dengan hasil prediksi emosi.
## 5. text_normalize.py
Berisi fungsi normalize() untuk memproses teks pengguna sebelum masuk ke pipeline prediksi.
Proses yang dilakukan:
Menghapus karakter tidak relevan (misalnya tanda baca, angka).
Menghapus stopwords (kata-kata umum seperti "dan", "yang").
Mengubah teks menjadi huruf kecil untuk konsistensi.
## 6. requirements.txt
Berisi daftar pustaka Python yang diperlukan untuk menjalankan aplikasi.
Digunakan untuk memastikan semua dependensi diinstal dengan versi yang kompatibel.

# Alur Kerja Sistem
## Input Teks:
Pengguna memberikan teks input melalui endpoint /predict (misalnya, "I feel so happy today!").

## Normalisasi Teks:
Teks diproses oleh fungsi normalize() di file text_normalize.py untuk menghapus karakter tidak penting, stopwords, dan memastikan format yang sesuai.

## Tokenisasi:
Teks yang telah dinormalisasi diubah menjadi urutan token oleh tokenizer (tokenizer.pkl).

## Prediksi Emosi:
Token yang dihasilkan dari tokenizer diberikan sebagai input ke model TFLite (model.tflite).
Model menghasilkan probabilitas untuk setiap emosi, dan sistem memilih emosi dengan probabilitas tertinggi.

## Pencarian Film:
Berdasarkan emosi yang diprediksi, dataset film (fix_dataset.pkl) difilter untuk mencocokkan predicted_emotion.
Film yang sesuai diurutkan berdasarkan popularitas, dan 15 film teratas direkomendasikan.

## Output Rekomendasi:

API memberikan respons berupa JSON yang berisi:
Prediksi Emosi: Emosi yang diprediksi oleh model.
Rekomendasi Film: Daftar 15 film teratas berdasarkan emosi yang diprediksi.
