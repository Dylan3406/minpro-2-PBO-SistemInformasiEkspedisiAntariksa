package model;

public class Ekspedisi {
    private int idEkspedisi;
    private String namaEkspedisi;
    private String tujuan;
    private int durasi;
    private String status;
    public Ekspedisi(int idEkspedisi, String namaEkspedisi,
    String tujuan, int durasi, String status) {
        this.idEkspedisi = idEkspedisi;
        this.namaEkspedisi = namaEkspedisi;
        this.tujuan = tujuan;
        this.durasi = durasi;
        this.status = status;
    }
    public int getIdEkspedisi() {
        return idEkspedisi;
    }
    public void setIdEkspedisi(int idEkspedisi) {
        this.idEkspedisi = idEkspedisi;
    }
    public String getNamaEkspedisi() {
        return namaEkspedisi;
    }
    public void setNamaEkspedisi(String namaEkspedisi) {
        this.namaEkspedisi = namaEkspedisi;
    }
    public String getTujuan() {
        return tujuan;
    }
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
    public int getDurasi() {
        return durasi;
    }
    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void tampilkanData() {
        System.out.println("ID Ekspedisi : " + idEkspedisi);
        System.out.println("Nama         : " + namaEkspedisi);
        System.out.println("Tujuan       : " + tujuan);
        System.out.println("Durasi       : " + durasi + " hari");
        System.out.println("Status       : " + status);
    }
}