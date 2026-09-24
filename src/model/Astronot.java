package model;

public class Astronot extends Kru {
    private String spesialisasi;
    private int jamTerbang;
    public Astronot(int idKru, String nama, int usia, String spesialisasi, int jamTerbang) {
        super(idKru, nama, usia); 
        this.spesialisasi = spesialisasi;
        this.jamTerbang = jamTerbang;
    }
    public String getSpesialisasi() {
        return spesialisasi;
    }
    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
    public int getJamTerbang() {
        return jamTerbang;
    }
    public void setJamTerbang(int jamTerbang) {
        this.jamTerbang = jamTerbang;
    }
    @Override
    public String getPeran() {
        return "Astronot";
    }
    @Override
    public String getDetailTugas() {
        return "Spesialisasi " + spesialisasi + ", Jam Terbang " + jamTerbang + " jam";
    }
}