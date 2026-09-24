package controller;

import java.util.ArrayList;
import model.PesawatAntariksa;
import view.View;

public class PesawatController {
    private final ArrayList<PesawatAntariksa> daftarPesawat = new ArrayList<>();
    private final View view;
    public PesawatController(View view) {
        this.view = view;
        muatDataAwal();
    }
    private void muatDataAwal() {
        daftarPesawat.add(new PesawatAntariksa(1, "Taufan BAEK", "Roket Orbital", 6, "Siap"));
    }
    public void tambahData() {
        view.cetak("\n--- TAMBAH DATA PESAWAT ---");

        int id = view.inputIntPositif("ID Pesawat: ");
        if (cariPesawat(id) != null) {
            view.cetak("ID Pesawat sudah digunakan!");
            return;
        }
        String nama = view.inputString("Nama Pesawat: ");
        String jenis = view.inputString("Jenis: ");
        int kapasitas = view.inputIntPositif("Kapasitas (orang): ");
        String status = pilihStatus();
        daftarPesawat.add(new PesawatAntariksa(id, nama, jenis, kapasitas, status));
        view.cetak("\nData pesawat berhasil ditambahkan!");
    }
    public void tampilkanData() {
        view.cetak("\n--- DATA PESAWAT ANTARIKSA ---");
        if (daftarPesawat.isEmpty()) {
            view.cetak("Belum ada data pesawat.");
            return;
        }
        for (PesawatAntariksa p : daftarPesawat) {
            view.tampilkanGaris();
            p.tampilkanData();
        }
        view.tampilkanGaris();
    }
    public void ubahData() {
        view.cetak("\n--- UBAH DATA PESAWAT ---");
        if (daftarPesawat.isEmpty()) {
            view.cetak("Belum ada data pesawat.");
            return;
        }
        int id = view.inputIntPositif("Masukkan ID pesawat yang ingin diubah: ");
        PesawatAntariksa p = cariPesawat(id);
        if (p == null) {
            view.cetak("\nID pesawat tidak ditemukan.");
            return;
        }
        p.setNamaPesawat(view.inputString("Nama baru: "));
        p.setJenis(view.inputString("Jenis baru: "));
        p.setKapasitas(view.inputIntPositif("Kapasitas baru: "));
        p.setStatus(pilihStatus());
        view.cetak("\nData berhasil diubah!");
    }
    public void hapusData() {
        view.cetak("\n--- HAPUS DATA PESAWAT ---");
        if (daftarPesawat.isEmpty()) {
            view.cetak("Belum ada data pesawat.");
            return;
        }
        int id = view.inputIntPositif("Masukkan ID pesawat yang ingin dihapus: ");
        PesawatAntariksa p = cariPesawat(id);
        if (p == null) {
            view.cetak("\nID pesawat tidak ditemukan.");
            return;
        }
        daftarPesawat.remove(p);
        view.cetak("\nData berhasil dihapus!");
    }
    public PesawatAntariksa cariPesawat(int id) {
        for (PesawatAntariksa p : daftarPesawat) {
            if (p.getIdPesawat() == id) {
                return p;
            }
        }
        return null;
    }
    public PesawatAntariksa cariPesawat(String nama) {
        for (PesawatAntariksa p : daftarPesawat) {
            if (p.getNamaPesawat().equalsIgnoreCase(nama)) {
                return p;
            }
        }
        return null;
    }
    private String pilihStatus() {
        String[] opsi = {"Siap", "Digunakan", "Perawatan", "Tidak Aktif"};
        while (true) {
            view.cetak("\nStatus Pesawat:");
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