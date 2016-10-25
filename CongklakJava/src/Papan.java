/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Kelas yang merepresentasikan papan permainan congklak
 * @author user
 */
public class Papan {
    
    //array yang berisi lubang-lubang dalam papan congklak
    private Lubang[] lubang;
    
    //menampung banyak biji pada masing-masing rumah
    private int rumah0,rumah1;

    /**
     * Constructor kelas Papan.
     * Pada awal permainan, semua biji diletakkan pada rumah masing-masing pemain secara merata sebelum dibagikan ke lubang di daerah masing-masing.
     */
    public Papan() {
        rumah0=rumah1=49;
        lubang=new Lubang[14];
        for(int i=0;i<lubang.length;i++){
            lubang[i]=new Lubang();
        }
    }
    
    /**
     * Constructor berparameter kelas Papan.
     * @param lubang Array lubang yang baru
     * @param rumah0 Banyak biji di rumah Player 1 yang baru
     * @param rumah1 Banyak biji di rumah Player 2 yang baru
     */
    public Papan(Lubang[] lubang,int rumah0,int rumah1){
        this.lubang=new Lubang[14];
        setState(lubang,rumah0,rumah1);
    }
    
    /**
     * Getter untuk atribut privat lubang.
     * @return Array berisi objek-objek lubang.
     */
    public Lubang[] getLubang() {
        return lubang;
    }

    /**
     * Getter untuk atribut privat rumah0.
     * @return Banyak biji yang ada di dalam rumah Player 1.
     */
    public int getRumah0() {
        return rumah0;
    }

    /**
     * Setter untuk atribut privat rumah0.
     * @param rumah0 Banyak biji dalam rumah Player 1 yang baru.
     */
    public void setRumah0(int rumah0) {
        this.rumah0 = rumah0;
    }

    /**
     * Getter untuk atribut privat rumah1.
     * @return Banyak biji yang ada di dalam rumah Player 2.
     */
    public int getRumah1() {
        return rumah1;
    }

    /**
     * Setter untuk atribut privat rumah1
     * @param rumah1 Banyak biji dalam rumah Player 2 yang baru.
     */
    public void setRumah1(int rumah1) {
        this.rumah1 = rumah1;
    }
    
    /**
     * Method untuk menyiapkan papan permainan dan dipanggil sebelum permainan dimulai.
     * Biji yang ada di rumah akan dibagikan ke lubang daerah masing-masing sebanyak 7 biji tiap lubang.
     * Jika ada sisa biji setelah mengisi semua lubang, kembalikan sisa biji ke rumah.
     * Jika sisa biji kurang dari 7 dan tidak semua lubang terisi, kembalikan sisa biji ke rumah dan tandai lubang-lubang yang kosong.
     */
    public void siapkanPapan(){
        //kosongkan semua lubang dan set agar semua lubang dapat diisi
        for(int i=0;i<14;i++){
            lubang[i].setIsi(0);
            lubang[i].setBisaDiisi(true);
        }
        
        //isi lubang daerah Player 1
        int i=0;
        while(i<7&&rumah0>=7){
            lubang[i].setIsi(7);
            i++;
            rumah0-=7;
        }
        
        //tandai lubang-lubang yang kosong
        while(i<7){
            lubang[i].setBisaDiisi(false);
            i++;
        }
        
        //isi lubang daerah Player 2
        while(i<14&&rumah1>=7){
            lubang[i].setIsi(7);
            i++;
            rumah1-=7;
        }
        
        //tandai lubang-lubang yang kosong
        while(i<14){
            lubang[i].setBisaDiisi(false);
            i++;
        }
    }
    
    private void setState(Lubang[] lubang,int rumah0,int rumah1){
        for(int i=0;i<lubang.length;i++){
            this.lubang[i]=lubang[i];
        }
        this.rumah0=rumah0;
        this.rumah1=rumah1;
    }
    
    /**
     * Method untuk mencetak isi papan ke layar.
     * Setiap angka yang ditampilkan berupa angka 2 digit.
     */
    public void printPapan(){
        //baris pertama, lubang Player 2
        System.out.print("    ");
        for(int i=7;i<14;i++){
            System.out.printf(" |%02d| ",lubang[i].getIsi());
        }
        System.out.println("");
        
        //baris kedua, rumah Player 1 dan rumah Player 2
        System.out.printf("|%02d|",rumah0);
        for(int i=0;i<7;i++){
            System.out.print("      ");
        }
        System.out.printf("|%02d|",rumah1);
        System.out.println("");
        
        //baris ketiga, lubang Player 1
        System.out.print("    ");
        for(int i=6;i>=0;i--){
            System.out.printf(" |%02d| ",lubang[i].getIsi());
        }
        System.out.println("");
    }
}
