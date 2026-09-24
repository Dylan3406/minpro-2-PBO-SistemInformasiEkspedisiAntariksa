# Minpro 2 PBO Sistem Informasi Ekspedisi Antariksa

## Deskripsi Singkat Program

Program ini adalah aplikasi konsol (CLI) berbasis Java untuk mengelola data operasional sebuah lembaga eksplorasi antariksa, yang terdiri dari tiga entitas utama:

- **Ekspedisi** - misi eksplorasi antariksa (nama, tujuan, durasi, status).
- **Kru** - anggota kru ekspedisi, terbagi menjadi dua peran spesifik: **Astronot** dan **Teknisi**.
- **Pesawat Antariksa** - armada pesawat yang digunakan untuk ekspedisi (nama, jenis, kapasitas, status).

Setiap entitas mendukung operasi CRUD penuh: Tambah, Tampilkan, Ubah, dan Hapus data melalui menu interaktif.

## Penjelasan Alur Program

1. Program dimulai dari `Main.java` yang menampilkan menu utama dengan 3 pilihan modul (Ekspedisi, Kru, Pesawat Antariksa) dan 1 pilihan keluar.
2. Saat program dijalankan, setiap Controller langsung mengisi **dummy data awal** ke dalam `ArrayList` masing-masing, sehingga menu "Tampilkan Data" langsung menampilkan data tanpa perlu input manual terlebih dahulu.
3. Setiap modul memiliki sub-menu (Tambah, Tampilkan, Ubah, Hapus, Kembali) yang memanggil method pada Controller terkait.
4. `Main` dan `View` **tidak pernah** mengakses/menyimpan data secara langsung - semua data disimpan dan diproses di dalam Controller, mengikuti pola MVC.
5. Semua pembacaan input dari keyboard (`Scanner`) beserta validasinya dipusatkan di `View`, sehingga logika validasi tidak tersebar dan mudah digunakan ulang oleh ketiga Controller.
6. Program terus berputar dalam menu (`do-while`) sampai pengguna memilih "Keluar"/"Kembali".

## Struktur Package (MVC)

```
src/mini/project/pkg2/
├── model/          # (Model) Representasi data & aturan bisnis dasar tiap entitas
│   ├── Ekspedisi.java
│   ├── Kru.java              -> superclass (abstract)
│   ├── Astronot.java         -> subclass Kru
│   ├── Teknisi.java          -> subclass Kru
│   └── PesawatAntariksa.java
├── view/           # (View) Seluruh tampilan teks & pembacaan input pengguna beserta validasinya
│   └── View.java
├── controller/     # (Controller) Logika bisnis: CRUD, validasi ID unik, pencarian data, menghubungkan Model <-> View
│   ├── EkspedisiController.java
│   ├── KruController.java
│   └── PesawatController.java
└── main/           # Entry point aplikasi, hanya menampilkan menu & mendelegasikan ke Controller
    └── Main.java
```

- **Model** hanya berisi atribut (private), constructor, getter/setter, dan method tampilan data milik dirinya sendiri. Tidak ada logika menu atau `Scanner` di sini.
- **View** hanya berisi method untuk mencetak teks ke layar dan membaca+memvalidasi input dari `Scanner`. View tidak menyimpan data aplikasi.
- **Controller** menyimpan `ArrayList` data, berisi seluruh logika CRUD dan validasi (misalnya cek ID duplikat), lalu memanggil `View` untuk berinteraksi dengan pengguna dan `Model` untuk membuat/mengubah objek data.
- **Main** hanya menampilkan struktur menu dan meneruskan pilihan pengguna ke method Controller yang sesuai.

## Penerapan Encapsulation dan Inheritance

### Encapsulation
Seluruh atribut pada class `Ekspedisi`, `Kru` (beserta subclass-nya), dan `PesawatAntariksa` dideklarasikan dengan access modifier `private`, dan hanya dapat diakses/diubah melalui getter dan setter publik. Contoh: `idKru`, `nama`, `usia` pada `Kru.java` bersifat `private` dan hanya bisa dibaca lewat `getIdKru()`, `getNama()`, `getUsia()` atau diubah lewat setter-nya.

### Inheritance
Dibuat satu superclass abstrak `Kru` dengan dua subclass:
- `Astronot extends Kru` - menambahkan atribut `spesialisasi` dan `jamTerbang`.
- `Teknisi extends Kru` - menambahkan atribut `bidangKeahlian` dan `sertifikasi`.

Kedua subclass mewarisi atribut umum (`idKru`, `nama`, `usia`) beserta getter/setter dari `Kru`, dan memanfaatkan constructor superclass melalui `super(...)`. `KruController` menyimpan data dalam satu `ArrayList<Kru>` yang berisi campuran objek `Astronot` dan `Teknisi`, menunjukkan hubungan is-a antara subclass dan superclass-nya.

## Penerapan Nilai Tambah

### a. Struktur MVC
Dijelaskan lengkap pada bagian 3 di atas. Pemisahan `model`, `view`, `controller`, dan `main` membuat setiap bagian punya tanggung jawab tunggal (single responsibility) dan lebih mudah dikembangkan/diuji secara terpisah.

### b. Polymorphism
Diterapkan dalam dua bentuk:

1. **Method Overriding** - class `Kru` mendeklarasikan method abstrak `getPeran()` dan `getDetailTugas()`. Method ini diimplementasikan secara berbeda oleh `Astronot` dan `Teknisi`. Saat `tampilkanData()` (didefinisikan sekali di `Kru`) dipanggil untuk objek apa pun di dalam `ArrayList<Kru>`, hasil `getPeran()` dan `getDetailTugas()` otomatis menyesuaikan tipe objek aslinya (lihat `KruController.tampilkanData()`).
2. **Method Overloading** - setiap Controller (`EkspedisiController`, `KruController`, `PesawatController`) memiliki dua method pencarian dengan nama sama namun parameter berbeda, misalnya:
   - `cariKru(int id)` - mencari berdasarkan ID.
   - `cariKru(String nama)` - mencari berdasarkan Nama.

   Java memilih method yang sesuai berdasarkan tipe parameter yang diberikan saat pemanggilan.

## Validasi Input yang Diterapkan

- Input angka wajib berupa angka valid (`inputInt`), jika tidak akan diminta ulang.
- Input angka tertentu wajib lebih dari 0, misalnya ID, durasi, kapasitas, usia (`inputIntPositif`).
- Input angka tertentu tidak boleh negatif, misalnya jam terbang (`inputIntNonNegatif`).
- Input teks wajib diisi, tidak boleh kosong (`inputString`).
- ID pada setiap entitas divalidasi agar tidak duplikat sebelum data baru ditambahkan.
- Pilihan menu dan pilihan status divalidasi terhadap rentang pilihan yang tersedia.

## Dummy Data Awal

Setiap Controller mengisi dummy data awal saat program dijalankan:
- **Ekspedisi**: "Artemis Kaltim I" (tujuan Bulan, status Berlangsung).
- **Kru**: 1 data Astronot ("Dylan Al Furqon") dan 1 data Teknisi ("Tony Stark").
- **Pesawat Antariksa**: "Garuda Nusantara" (jenis Roket Orbital, status Siap).

## Dokumentasi Output
### Menu Awal
<img width="288" height="174" alt="image" src="https://github.com/user-attachments/assets/bae9bc31-8e90-4b9c-bf6b-4784518c8f2c" />
Ini adalah Tampilan awal ketika baru memulai program ekspedisi antariksa <br>

### Menu Kelas Ekspedisi
<img width="286" height="176" alt="image" src="https://github.com/user-attachments/assets/c874b762-9855-47bc-ac48-84dc5eecfb50" />
Ini Adalah Tampilan menu CRUD di kelas Ekspedisi <br>

### Create Ekspedisi
<img width="357" height="273" alt="image" src="https://github.com/user-attachments/assets/a7966c2e-c74e-4bcc-9238-efd09599d82b" />
Ini adalah hal hal yang perlu di Input di dalam mode Create Ekspedisi, setelah selesai program akan looping kembali ke menu kelas ekspedisi <br>

### Read Ekspedisi
<img width="298" height="243" alt="image" src="https://github.com/user-attachments/assets/d41573ad-aa1b-41de-89a4-14d60ef7c43e" />
Ini adalah Output dari Read Ekspedisi, ID Ekspedisi 1 adalah data dummy yang harus dibuat sesuai dengan ketentuan mini project 2. dan ID Ekspedisi 2 adalah data dari yang kita input sebelumnya, untuk status dari data ekspedisi yang kita tambahkan itu masih berstatus direncanakan yang berarti ekspedisi ini belum dilaksanakan <br>

### Update Ekspedisi
<img width="314" height="210" alt="image" src="https://github.com/user-attachments/assets/42a89734-a4ed-44fb-a0fc-5ee360eeb5fe" />
Ini adalah Output dari Update Ekspedisi, disini kita hanya mengubah status dari ekspedisinya saja <br>

<img width="297" height="247" alt="image" src="https://github.com/user-attachments/assets/9714d6f4-977f-4062-ad7d-67042c331cb4" />
Status yang sudah kita update sudah berhasil dirubah pada saat kita tampilkan datanya <br>



