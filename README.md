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
<img width="288" height="174" alt="image" src="https://github.com/user-attachments/assets/bae9bc31-8e90-4b9c-bf6b-4784518c8f2c" /> <br>
Ini adalah Tampilan awal ketika baru memulai program ekspedisi antariksa <br>

### Menu Kelas Ekspedisi
<img width="286" height="176" alt="image" src="https://github.com/user-attachments/assets/c874b762-9855-47bc-ac48-84dc5eecfb50" /> <br>
Ini Adalah Tampilan menu CRUD di kelas Ekspedisi <br>

### Create Ekspedisi
<img width="357" height="273" alt="image" src="https://github.com/user-attachments/assets/a7966c2e-c74e-4bcc-9238-efd09599d82b" /> <br>
Ini adalah hal hal yang perlu di Input di dalam mode Create Ekspedisi, setelah selesai program akan looping kembali ke menu kelas ekspedisi <br>

### Read Ekspedisi
<img width="298" height="243" alt="image" src="https://github.com/user-attachments/assets/d41573ad-aa1b-41de-89a4-14d60ef7c43e" /> <br>
Ini adalah Output dari Read Ekspedisi, ID Ekspedisi 1 adalah data dummy yang harus dibuat sesuai dengan ketentuan mini project 2. dan ID Ekspedisi 2 adalah data dari yang kita input sebelumnya, untuk status dari data ekspedisi yang kita tambahkan itu masih berstatus direncanakan yang berarti ekspedisi ini belum dilaksanakan <br>

### Update Ekspedisi
<img width="314" height="210" alt="image" src="https://github.com/user-attachments/assets/42a89734-a4ed-44fb-a0fc-5ee360eeb5fe" /> <br>
Ini adalah Output dari Update Ekspedisi, disini kita hanya mengubah status dari ekspedisinya saja <br>

<img width="297" height="247" alt="image" src="https://github.com/user-attachments/assets/9714d6f4-977f-4062-ad7d-67042c331cb4" /> <br>
Status yang sudah kita update sudah berhasil dirubah pada saat kita tampilkan datanya <br>

### Delete Ekspedisi
<img width="327" height="87" alt="image" src="https://github.com/user-attachments/assets/683ccbea-7ac3-4729-b355-d718ef5f058a" /> <br>
Di bagian ini user akan diminta untuk memasukkan ID Ekspedisi yang ada di data sebelumnya, saya menggunakan ID 2 yang baru kita bikin tadi datanya sebagai contoh <br>

<img width="294" height="147" alt="image" src="https://github.com/user-attachments/assets/b8b3755f-a6de-4bd5-863f-c76921077c62" /> <br>
Dapat kita lihat data yang kita hapus sudah hilang pada saat kita menampilkan datanya <br>

### Menu Kelas Kru
<img width="301" height="174" alt="image" src="https://github.com/user-attachments/assets/4a91ec77-7ba0-4942-9d7b-a0166144523a" /> <br>
Ini adalah tampilan menu CRUD di kelas Kru <br>

### Create Kru
#### Astronot
<img width="331" height="269" alt="image" src="https://github.com/user-attachments/assets/601c7b60-00fa-4e7d-a85d-9c65bdd7b049" /> <br>
Ini adalah hal hal yang perlu di Input di dalam mode Create Kru, disini user dapat mengelompokkan Kru ini spesifiknya bekerja sebagai apa, sebagai contoh disini saya membuat kru astronot <br>

#### Teknisi
<img width="284" height="226" alt="image" src="https://github.com/user-attachments/assets/37a3fdb9-fcb9-40a2-aab3-8bbe4f0e49c3" /> <br>
Ini adalah hal hal yang perlu di input user jika ingin menambahkan data kru di bagian teknisi <br>

### Read Kru
<img width="579" height="456" alt="image" src="https://github.com/user-attachments/assets/384aeef5-3faa-444c-b3dc-1ba6c3aa0985" /> <br>
Ini adalah Output dari Read Kru, ID Kru 1 dan 2 itu adalah data dummy yang dibuat sesuai ketentuan mini project 2, sedangkan ID 3 dan 4 itu adalah data yang baru dibuat dengan masing masing kru yang berbeda spesifikasinya <br>

### Update Kru
<img width="323" height="189" alt="image" src="https://github.com/user-attachments/assets/6cb431c3-66f8-408d-b11c-f5056593a712" /> <br>
Ini adalah Output dari Update Kru, berbeda dengan update ekspedisi, disini kita bisa mengubah semua data yang ada di ID Kru tersebut. Tetapi disini saya hanya mengubah Bidangnya dan Sertifikasinya saja <br>

<img width="582" height="446" alt="image" src="https://github.com/user-attachments/assets/495276d7-84cc-45b8-80f1-959ac08009b3" /> <br>
Hasil dari data yang sudah diubah sebelumnya bisa dilihat pada mode Read Kru <br>

### Delete Kru
<img width="292" height="86" alt="image" src="https://github.com/user-attachments/assets/79fe2648-56c2-4764-8861-9798d1404b04" /> <br>
Di bagian ini User akan diminta untuk menginput ID Kru yang ingin kita hapus, alur Delete ini sama dengan alur sebelumnya di Delete Ekspedisi <br>

<img width="589" height="346" alt="image" src="https://github.com/user-attachments/assets/a30d206e-bcc4-41ee-ac21-87bece457e7e" /> <br>
Dapat kita lihat ID Kru yang tadi kita input sudah berhasil terhapus dan bisa dilihat pada mode Read Kru 

### Menu Kelas Pesawat Antariksa
<img width="285" height="175" alt="image" src="https://github.com/user-attachments/assets/320550bc-ec50-40e5-97e6-84811e020d1c" /> <br>
Ini adalah tampilan menu CRUD di kelas Pesawat Antariksa

### Create Pesawat
<img width="268" height="250" alt="image" src="https://github.com/user-attachments/assets/76fc0152-a85b-4cc4-a2d0-3f9fc63a02ec" /> <br>
Ini adalah hal hal yang perlu di input dalam mode Create Pesawat, disini user diminta menginput nama, jenis, kapasitas, dan status dari pesawatnya <br>

### Read Pesawat
<img width="283" height="247" alt="image" src="https://github.com/user-attachments/assets/d3522a8d-b616-489e-b134-3befdb78da8d" /> <br>
Ini adalah output dari Read Pesawat, ID 1 adalah data dummy sesuai dengan instruksi mini project 2, sedangkan ID 2 adalah data yang baru ditambahkan pada proses sebelumnya <br>

### Update Pesawat 
<img width="304" height="245" alt="image" src="https://github.com/user-attachments/assets/998e103e-f395-41e3-8caa-12907ce1f6f4" /> <br>
Ini adalah Output dari Update pesawat, sama seperti update Kru, disini kita bisa mengubah semua data yang ada di ID Pesawat tersebut. Tetapi disini saya hanya mengubah Statusnya saja <br>

<img width="288" height="246" alt="image" src="https://github.com/user-attachments/assets/e37cf45c-bcc0-4de9-941c-3c3d46478d38" /> <br>
Dapat dilihat hasil dari status yang sudah diubah sebelumnya pada mode Read Pesawat <br>

### Delete Pesawat
<img width="311" height="80" alt="image" src="https://github.com/user-attachments/assets/bf18569e-337d-4df2-8af4-fc667a6a3425" /> <br>
Di bagian ini User akan diminta menginput ID Pesawat yang ingin kita hapus, alur Delete ini sama dengan kedua alur sebelumnya <br>

<img width="306" height="151" alt="image" src="https://github.com/user-attachments/assets/4a3d4bf2-cae3-4031-bd54-5d7f69b694ba" /> <br>
Dapat kita lihat ID Pesawat yang tadi kita input sudah berhasil terhapus dan bisa dilihat pada mode Read Pesawat <br>
