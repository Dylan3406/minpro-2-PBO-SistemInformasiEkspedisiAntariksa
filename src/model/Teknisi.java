package model;

public class Teknisi extends Kru {
    private String bidangKeahlian;
    private String sertifikasi;
    public Teknisi(int idKru, String nama, int usia, String bidangKeahlian, String sertifikasi) {
        super(idKru, nama, usia); 
        this.bidangKeahlian = bidangKeahlian;
        this.sertifikasi = sertifikasi;
    }
    public String getBidangKeahlian() {
        return bidangKeahlian;
    }
    public void setBidangKeahlian(String bidangKeahlian) {
        this.bidangKeahlian = bidangKeahlian;
    }
    public String getSertifikasi() {
        return sertifikasi;
    }
    public void setSertifikasi(String sertifikasi) {
        this.sertifikasi = sertifikasi;
    }
    @Override
    public String getPeran() {
        return "Teknisi";
    }
    @Override
    public String getDetailTugas() {
        return "Bidang " + bidangKeahlian + ", Sertifikasi " + sertifikasi;
    }
}