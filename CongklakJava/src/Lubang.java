/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Kelas yang merepresentasikan sebuah lubang dalam permainan congklak
 * @author user
 */
public class Lubang {
    
    //banyak biji dalam lubang
    private int isi;
    
    //menentukan apakah lubang 'terbakar' atau tidak
    private boolean bisaDiisi;

    /**
     * Constructor kelas Lubang.
     * Atribut isi pada awalnya adalah 0 dan bisaDiisi adalah true.
     */
    public Lubang() {
        this.isi = 0;
        bisaDiisi=true;
    }

    /**
     * Getter untuk atribut privat isi.
     * @return Nilai atribut isi.
     */
    public int getIsi() {
        return isi;
    }

    /**
     * Setter untuk atribut privat isi.
     * @param isi Nilai atribut isi yang baru.
     */
    public void setIsi(int isi) {
        this.isi = isi;
    }

    /**
     * Getter untuk atribut privat bisaDiisi.
     * @return Nilai atribut bisaDiisi, true jika bisa diisi dan false jika tidak bisa diisi atau berupa lubang 'terbakar'.
     */
    public boolean isBisaDiisi() {
        return bisaDiisi;
    }

    /**
     * Setter untuk atribut privat bisaDiisi.
     * @param bisaDiisi Nilai atribut bisaDiisi yang baru.
     */
    public void setBisaDiisi(boolean bisaDiisi) {
        this.bisaDiisi = bisaDiisi;
    }
    
}
