package controller;

import java.util.ArrayList;
import model.Ekspedisi;
import view.View;

public class EkspedisiController {
    private final ArrayList<Ekspedisi> daftarEkspedisi = new ArrayList<>();
    private final View view;
    public EkspedisiController(View view) {
        this.view = view;
        muatDataAwal();
    }
    private void muatDataAwal() {
        daftarEkspedisi.add(new Ekspedisi(1, "Galang Dana Bem KM UNMUL", "Mars", 14, "Berlangsung"));
    }
    public void tambahData() {
        view.cetak("\n--- TAMBAH DATA EKSPEDISI ---");

        int id = view.inputIntPositif("ID Ekspedisi: ");
        if (cariEkspedisi(id) != null) {
            view.cetak("ID Ekspedisi sudah digunakan!");
            return;
        }
        String nama = view.inputString("Nama Ekspedisi: ");
        String tujuan = view.inputString("Tujuan: ");
        int durasi = view.inputIntPositif("Durasi (hari): ");
        String status = pilihStatus();
        daftarEkspedisi.add(new Ekspedisi(id, nama, tujuan, durasi, status));
        view.cetak("\nData ekspedisi berhasil ditambahkan!");
    }
    public void tampilkanData() {
        view.cetak("\n--- DATA EKSPEDISI ---");
        if (daftarEkspedisi.isEmpty()) {
            view.cetak("Belum ada data ekspedisi.");
            return;
        }
        for (Ekspedisi e : daftarEkspedisi) {
            view.tampilkanGaris();
            e.tampilkanData();
        }
        view.tampilkanGaris();
    }
    public void ubahStatus() {
        view.cetak("\n--- UBAH STATUS EKSPEDISI ---");
        if (daftarEkspedisi.isEmpty()) {
            view.cetak("Belum ada data ekspedisi.");
            return;
        }
        int id = view.inputIntPositif("Masukkan ID ekspedisi yang ingin diubah: ");
        Ekspedisi e = cariEkspedisi(id);
        if (e == null) {
            view.cetak("\nID ekspedisi tidak ditemukan.");
            return;
        }
        e.setStatus(pilihStatus());
        view.cetak("\nData berhasil diubah!");
    }
    public void hapusData() {
        view.cetak("\n--- HAPUS DATA EKSPEDISI ---");
        if (daftarEkspedisi.isEmpty()) {
            view.cetak("Belum ada data ekspedisi.");
            return;
        }
        int id = view.inputIntPositif("Masukkan ID ekspedisi yang ingin dihapus: ");
        Ekspedisi e = cariEkspedisi(id);
        if (e == null) {
            view.cetak("\nID ekspedisi tidak ditemukan.");
            return;
        }
        daftarEkspedisi.remove(e);
        view.cetak("\nData berhasil dihapus!");
    }
    public Ekspedisi cariEkspedisi(int id) {
        for (Ekspedisi e : daftarEkspedisi) {
            if (e.getIdEkspedisi() == id) {
                return e;
            }
        }
        return null;
    }
    public Ekspedisi cariEkspedisi(String nama) {
        for (Ekspedisi e : daftarEkspedisi) {
            if (e.getNamaEkspedisi().equalsIgnoreCase(nama)) {
                return e;
            }
        }
        return null;
    }
    private String pilihStatus() {
        String[] opsi = {"Direncanakan", "Persiapan", "Berlangsung", "Selesai", "Dibatalkan"};

        while (true) {
            view.cetak("\nStatus Ekspedisi:");
            for (int i = 0; i < opsi.length; i++) {
                view.cetak((i + 1) + ". " + opsi[i]);
            }
            int pilihan = view.inputInt("Pilih status: ");
            if (pilihan >= 1 && pilihan <= opsi.length) {
                return opsi[pilihan - 1];
            }
            view.cetak("Pilihan status tidak tersedia!");
        }
    }
}