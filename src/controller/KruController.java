package controller;

import java.util.ArrayList;
import model.Astronot;
import model.Kru;
import model.Teknisi;
import view.View;

public class KruController {
    private final ArrayList<Kru> daftarKru = new ArrayList<>();
    private final View view;
    public KruController(View view) {
        this.view = view;
        muatDataAwal();
    }
    private void muatDataAwal() {
        daftarKru.add(new Astronot(1, "Dylan Al Furqon", 21, "Pengendali Multiverse", 320));
        daftarKru.add(new Teknisi(2, "Tony Stark", 45, "Genius, Billionaire, Playboy", "ISO 9001"));
    }
    public void tambahData() {
        view.cetak("\n--- TAMBAH DATA KRU ---");
        int id = view.inputIntPositif("ID Kru: ");
        if (cariKru(id) != null) {
            view.cetak("ID Kru sudah digunakan!");
            return;
        }
        String nama = view.inputString("Nama: ");
        int usia = view.inputIntPositif("Usia: ");
        view.cetak("\nJenis Kru:");
        view.cetak("1. Astronot");
        view.cetak("2. Teknisi");
        int jenis = view.inputInt("Pilih jenis kru: ");
        switch (jenis) {
            case 1 -> {
                String spesialisasi = view.inputString("Spesialisasi: ");
                int jamTerbang = view.inputIntNonNegatif("Jam Terbang: ");
                daftarKru.add(new Astronot(id, nama, usia, spesialisasi, jamTerbang));
            }
            case 2 -> {
                String bidang = view.inputString("Bidang Keahlian: ");
                String sertifikasi = view.inputString("Sertifikasi: ");
                daftarKru.add(new Teknisi(id, nama, usia, bidang, sertifikasi));
            }
            default -> {
                view.cetak("\nJenis kru tidak dikenali, data dibatalkan.");
                return;
            }
        }
        view.cetak("\nData kru berhasil ditambahkan!");
    }
    public void tampilkanData() {
        view.cetak("\n--- DATA KRU ---");

        if (daftarKru.isEmpty()) {
            view.cetak("Belum ada data kru.");
            return;
        }
        for (Kru k : daftarKru) {
            view.tampilkanGaris();
            k.tampilkanData(); // polymorphism: method overriding pada tiap subclass
        }
        view.tampilkanGaris();
    }
    public void ubahData() {
        view.cetak("\n--- UBAH DATA KRU ---");

        if (daftarKru.isEmpty()) {
            view.cetak("Belum ada data kru.");
            return;
        }
        int id = view.inputIntPositif("Masukkan ID kru yang ingin diubah: ");
        Kru k = cariKru(id);

        if (k == null) {
            view.cetak("\nID kru tidak ditemukan.");
            return;
        }
        k.setNama(view.inputString("Nama baru: "));
        k.setUsia(view.inputIntPositif("Usia baru: "));
        switch (k) {
            case Astronot a -> {
                a.setSpesialisasi(view.inputString("Spesialisasi baru: "));
                a.setJamTerbang(view.inputIntNonNegatif("Jam Terbang baru: "));
            }
            case Teknisi t -> {
                t.setBidangKeahlian(view.inputString("Bidang Keahlian baru: "));
                t.setSertifikasi(view.inputString("Sertifikasi baru: "));
            }
            default -> {
            }
        }
        view.cetak("\nData berhasil diubah!");
    }
    public void hapusData() {
        view.cetak("\n--- HAPUS DATA KRU ---");

        if (daftarKru.isEmpty()) {
            view.cetak("Belum ada data kru.");
            return;
        }
        int id = view.inputIntPositif("Masukkan ID kru yang ingin dihapus: ");
        Kru k = cariKru(id);

        if (k == null) {
            view.cetak("\nID kru tidak ditemukan.");
            return;
        }
        daftarKru.remove(k);
        view.cetak("\nData berhasil dihapus!");
    }
    public Kru cariKru(int id) {
        for (Kru k : daftarKru) {
            if (k.getIdKru() == id) {
                return k;
            }
        }
        return null;
    }
    public Kru cariKru(String nama) {
        for (Kru k : daftarKru) {
            if (k.getNama().equalsIgnoreCase(nama)) {
                return k;
            }
        }
        return null;
    }
}