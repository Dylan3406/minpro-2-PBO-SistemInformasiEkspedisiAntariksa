# Minpro-2-PBO-EkspedisiAntariksa

## Deskripsi Singkat Program

Program ini adalah **Sistem Pengelolaan Ekspedisi Antariksa** berbasis console (CLI) yang dikembangkan menggunakan Java dengan pendekatan Object-Oriented Programming (OOP). Program ini merupakan lanjutan dari Mini Project 1, yang sebelumnya mengelola tiga entitas (Ekspedisi, Kru, Pesawat Antariksa). Berdasarkan masukan asisten laboratorium bahwa cakupan sebelumnya terlalu kompleks, pada Mini Project 2 ini pengembangan **difokuskan pada satu entitas utama, yaitu Ekspedisi**, namun dikembangkan lebih dalam dengan menerapkan inheritance, encapsulation, validasi input, serta struktur MVC yang lebih rapi.

Program memungkinkan pengguna untuk mengelola dua jenis ekspedisi antariksa:
1. **Ekspedisi Penelitian** — misi ilmiah dengan atribut bidang penelitian & jumlah ilmuwan.
2. **Ekspedisi Komersial** — misi pengiriman kargo dengan atribut klien & kapasitas kargo.

Fitur yang tersedia: tambah data (per jenis), tampilkan seluruh data, cari data (berdasarkan ID atau nama), ubah status, dan hapus data.

## Struktur Package (MVC)

```
src/
├── app/
│   └── Main.java                  -> Entry point, hanya memanggil View
├── model/
│   ├── Ekspedisi.java             -> Superclass abstrak
│   ├── EkspedisiPenelitian.java   -> Subclass 1
│   └── EkspedisiKomersial.java    -> Subclass 2
├── controller/
│   └── EkspedisiController.java   -> Seluruh logic CRUD & pengelolaan ArrayList
└── view/
    └── EkspedisiView.java         -> Seluruh interaksi input/output dengan pengguna
```

- **model** — berisi kelas data (entity) beserta aturan/validasi datanya sendiri (getter, setter, hitung biaya).
- **controller** — berisi seluruh logic bisnis: tambah, cari, ubah, hapus data. Tidak ada satu pun `System.out`/`Scanner` di sini.
- **view** — berisi seluruh tampilan menu, `Scanner`, dan validasi format input dari pengguna. View memanggil controller untuk memproses data, tidak pernah mengubah data secara langsung.
- **app.Main** — hanya title/entry point, cukup 5 baris, tidak ada logic apa pun di dalamnya (ini adalah perbaikan langsung dari koreksi Mini Project 1 yang meminta logic CRUD dipisah dari entry point).

## Alur Program

1. `Main` menjalankan `EkspedisiView.tampilkanMenuUtama()`.
2. `EkspedisiView` membuat instance `EkspedisiController`, yang otomatis mengisi 2 data dummy (1 Ekspedisi Penelitian, 1 Ekspedisi Komersial) ke dalam `ArrayList<Ekspedisi>`.
3. Pengguna memilih menu (1-7). Setiap pilihan menu di `View` akan:
   - Meminta & memvalidasi input dari pengguna (misalnya angka harus > 0, string tidak boleh kosong, ID tidak boleh duplikat).
   - Memanggil method yang sesuai di `EkspedisiController` untuk memproses data (tambah/cari/ubah/hapus).
   - Menampilkan hasil ke pengguna, memanfaatkan method `tampilkanData()` milik object `Ekspedisi` (bisa `EkspedisiPenelitian` atau `EkspedisiKomersial`) — di sinilah polymorphism runtime terjadi.
4. Program terus berulang sampai pengguna memilih menu **7. Keluar**.

## Penerapan Encapsulation

- Seluruh field pada `Ekspedisi`, `EkspedisiPenelitian`, dan `EkspedisiKomersial` bersifat `private`/`protected` (tidak bisa diakses langsung dari luar class).
- Setiap field diakses lewat **getter** dan diubah lewat **setter**.
- Setter tidak sekadar assignment, tapi **melakukan validasi** sebelum mengubah nilai, contoh:
  ```java
  public void setDurasi(int durasi) {
      if (durasi > 0) {
          this.durasi = durasi;
      }
  }
  ```
- Berbeda dari Mini Project 1, kali ini getter/setter **benar-benar dipakai** di alur program — misalnya `tampilkanData()` pada superclass memanggil `getIdEkspedisi()`, `getNamaEkspedisi()`, dst. (bukan mengakses field secara langsung), dan `EkspedisiController` menggunakan `getIdEkspedisi()` saat proses pencarian/penghapusan data.

## Penerapan Inheritance

- `Ekspedisi` adalah **superclass abstrak** yang menyimpan atribut umum (`idEkspedisi`, `namaEkspedisi`, `tujuan`, `durasi`, `status`).
- `EkspedisiPenelitian` dan `EkspedisiKomersial` adalah **2 subclass** yang meng-extend `Ekspedisi`, masing-masing menambahkan atribut spesifik:
  - `EkspedisiPenelitian` → `bidangPenelitian`, `jumlahIlmuwan`.
  - `EkspedisiKomersial` → `namaKlien`, `kapasitasKargo`.
- Kedua subclass memanggil constructor superclass melalui `super(...)`.
- Karena `Ekspedisi` bersifat `abstract`, class ini tidak bisa di-instantiate langsung — memaksa setiap ekspedisi yang dibuat harus jelas jenisnya (Penelitian/Komersial), sekaligus memastikan inheritance benar-benar dipakai secara fungsional (bukan formalitas).

## Penerapan Nilai Tambah

### a. Struktur MVC
Dijelaskan lengkap pada bagian "Struktur Package (MVC)" di atas — logic CRUD (`controller`), tampilan/input (`view`), dan data (`model`) dipisah ke package masing-masing, dengan `Main` hanya sebagai entry point.

### b. Polymorphism

**Overriding:**
- `hitungEstimasiBiaya()` — method abstrak di `Ekspedisi`, diimplementasikan berbeda di tiap subclass (rumus biaya penelitian berbeda dengan rumus biaya komersial).
- `tampilkanData()` — di-override oleh `EkspedisiPenelitian` dan `EkspedisiKomersial` untuk menambahkan baris output atribut khas masing-masing, setelah tetap memanggil `super.tampilkanData()` untuk atribut umum.
- Efeknya terlihat pada menu "Tampilkan Semua Ekspedisi": program melakukan loop `for (Ekspedisi e : daftarEkspedisi) { e.tampilkanData(); }` — method yang dipanggil otomatis menyesuaikan jenis object aslinya saat runtime.

**Overloading:**
- `tampilkanData()` (tanpa parameter) vs `tampilkanData(boolean ringkas)` pada class `Ekspedisi` — dipakai saat fitur "Cari Ekspedisi berdasarkan Nama" menampilkan hasil dalam format ringkas satu baris.
- `EkspedisiController.tambah(EkspedisiPenelitian e)` vs `tambah(EkspedisiKomersial e)` — dua method dengan nama sama, parameter beda.
- `EkspedisiController.cari(int id)` vs `cari(String kataKunci)` — pencarian berdasarkan ID (exact match, satu hasil) vs berdasarkan nama (mengandung kata kunci, bisa banyak hasil).

## Validasi Input yang Diterapkan

- Angka wajib berupa angka valid (`try-catch NumberFormatException`).
- Angka (durasi, jumlah ilmuwan, kapasitas kargo, ID) wajib bernilai positif (> 0).
- String (nama, tujuan, bidang, klien) tidak boleh kosong/hanya spasi.
- ID ekspedisi baru wajib unik (dicek lewat `controller.idSudahAda(id)`), mencegah duplikasi data.
- Pilihan menu & pilihan status divalidasi terhadap rentang pilihan yang tersedia, akan looping meminta input ulang jika tidak valid.

## Dummy Data Awal

Saat program pertama kali dijalankan, `EkspedisiController` otomatis mengisi 2 data awal ke `ArrayList`, sehingga menu "Tampilkan Semua Ekspedisi" langsung menampilkan data tanpa perlu input manual terlebih dahulu:

| ID | Nama            | Jenis      | Tujuan             | Durasi | Status       |
|----|-----------------|------------|--------------------|--------|--------------|
| 1  | Artemis Survey  | Penelitian | Bulan              | 30 hari| Berlangsung  |
| 2  | Starlink Cargo  | Komersial  | Orbit Rendah Bumi  | 10 hari| Direncanakan |

## Screenshot Output

> *(Tambahkan screenshot hasil run program di sini sebelum submit, contoh: menu utama, tampilan data, hasil tambah data, dsb. — ini sesuai masukan asisten lab agar dokumentasi punya gambaran visual program.)*

```
[Letakkan screenshot menu utama di sini]
[Letakkan screenshot fitur tampilkan data di sini]
[Letakkan screenshot fitur tambah data di sini]
```
