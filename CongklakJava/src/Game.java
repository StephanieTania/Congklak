/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Kelas yang merepresentasikan jalannya permainan congklak
 * @author user
 */
public class Game {

    private int giliran;
    private Papan p;

    /**
     * Constructor kelas Game.
     * Giliran pertama selalu diawali oleh Player 1.
     * Papan permainan disiapkan di dalam constructor ini.
     */
    public Game() {
        giliran = 0; //giliran 0 adalah Player 1, giliran 1 adalah Player 2
        p = new Papan();
        p.siapkanPapan();
    }

    /**
     * Method untuk menjalankan permainan sebanyak 1 kali.
     * @param idxLubang Indeks lubang yang dipilih untuk memulai.
     */
    public void gerak(int idxLubang) {
        int tangan = p.getLubang()[idxLubang].getIsi();
        p.getLubang()[idxLubang].setIsi(0);
        boolean masukRumah = false;
        int idx = idxLubang;
        do {
            idx = (idx + 1) % 14; //maju ke lubang selanjutnya
            if (p.getLubang()[idx].isBisaDiisi()) {
                if (giliran == 0 && idx == 7) {
                    //Player 1 masuk rumah
                    if (tangan == 1) {
                        //Player 1 berakhir di rumah
                        masukRumah = true;
                        p.setRumah0(p.getRumah0() + 1);
                        break;
                    } else {
                        tangan -= 1;
                        p.setRumah0(p.getRumah0() + 1);
                    }
                } else if (giliran == 1 && idx == 0) {
                    //Player 2 masuk rumah
                    if (tangan == 1) {
                        //Player 2 berakhir di rumah
                        masukRumah = true;
                        p.setRumah1(p.getRumah1() + 1);
                        break;
                    }
                    else{
                        tangan-=1;
                        p.setRumah1(p.getRumah1() + 1);
                    }
                }
                tangan -= 1; //ambil biji dari tangan
                p.getLubang()[idx].setIsi(p.getLubang()[idx].getIsi() + 1); //masukkan biji dari tangan ke lubang
            }
        } while (tangan > 0);
        if (!masukRumah) {
            if (p.getLubang()[idx].getIsi() == 1) {
                //akhir di lubang yang awalnya kosong
                if (giliran == 0) {
                    //giliran Player 1
                    if (idx >= 7) {
                        //lubang terakhir di daerah lawan
                        //ganti giliran
                        giliran = (giliran + 1) % 2;
                    } else {
                        //lubang terakhir di daerah sendiri
                        //ambil biji di lubang terakhir dan seberangnya, simpan di rumah
                        int biji=1;
                        p.getLubang()[idx].setIsi(0);
                        
                        biji+=p.getLubang()[13-idx].getIsi();
                        p.getLubang()[13-idx].setIsi(0);
                        
                        p.setRumah0(p.getRumah0()+biji);
                        giliran = (giliran + 1) % 2;                        
                    }
                }
                else if(giliran==1){
                    //giliran Player 2
                    if(idx<7){
                        //lubang terakhir di daerah lawan
                        //ganti giliran
                        giliran=(giliran+1)%2;
                    }
                    else{
                        //lubang terakhir di daerah sendiri
                        //lubang terakhir di daerah sendiri
                        //ambil biji di lubang terakhir dan seberangnya, simpan di rumah
                        int biji=1;
                        p.getLubang()[idx].setIsi(0);
                        
                        biji+=p.getLubang()[13-idx].getIsi();
                        p.getLubang()[13-idx].setIsi(0);
                        
                        p.setRumah1(p.getRumah1()+biji);
                        giliran = (giliran + 1) % 2;
                    }
                }
            } else {
                gerak(idx);
            }
        }
    }

    /**
     * Method untuk memeriksa apakah permainan sudah berakhir atau belum.
     * @return True jika permainan sudah berakhir dan ada pemenang, false jika belum.
     */
    public boolean cekAkhirGame() {
        if (p.getRumah0() + p.getRumah1() == 98) {
            return true;
        }
        return false;
    }

    /**
     * Getter untuk atribut privat giliran
     * @return Giliran pemain yang sedang berjalan.
     */
    public int getGiliran() {
        return giliran;
    }

    /**
     * Method untuk mencetak papan ke layar.
     */
    public void printPapan() {
        p.printPapan();
    }
    
    /**
     * Method validasi untuk memeriksa apakah indeks yang dipilih valid atau tidak.
     * @param idx Indeks lubang yang dipilih untuk diperiksa.
     * @return True jika indeks tersebut valid, false jika indeks tersebut tidak valid.
     */
    public boolean cekInput(int idx){
        if(idx<0||idx>=14){
            //indeks tidak valid
            return false;
        }
        else{
            if(!p.getLubang()[idx].isBisaDiisi()){
                //tidak bisa ambil dari lubang 'terbakar'
                return false;
            }
            else if(p.getLubang()[idx].getIsi()==0){
                //tidak bisa ambil dari lubang kosong
                return false;
            }
            else{
                //tidak bisa ambil dari lubang daerah lawan
                if(giliran==0&&idx>=7){
                    return false;
                }
                else if(giliran==1&&idx<7){
                    return false;
                }
            }
        }
        return true;
    }    
    
    /**
     * Method untuk mengembalikan pemain yang menang.
     * @return Angka 0 jika pemenang adalah Player 1, angka 1 jika pemenang adalah Player 2, atau angka -1 jika permainan berakhir seri.
     */
    public int getPemenang(){
        if(p.getRumah0()>p.getRumah1()){
            return 0;
        }
        else if(p.getRumah0()<p.getRumah1()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
