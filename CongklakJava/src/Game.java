
import java.util.Random;
import java.util.Stack;

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

    //atribut untuk menyimpan nomor giliran
    private int giliran;
    
    //atribut yang menyimpan papan permainan saat ini
    private Papan p;

    /**
     * Constructor kelas Game.
     * Giliran pertama selalu diawali oleh Player 1.
     */
    public Game() {
        giliran = 0; //giliran 0 adalah Player 1, giliran 1 adalah Player 2
        p = new Papan();
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
                        //Player 1 lewat rumah
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
                        //Player 2 lewat rumah
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
        if(!masihAdaJalanValid(giliran)){
            //tidak ada lagi jalan valid, harus ganti giliran
            giliran=(giliran+1)%2;
        }
    }

    /**
     * Method untuk memeriksa apakah permainan sudah berakhir atau belum.
     * @return True jika permainan sudah berakhir dan ada pemenang, false jika belum.
     */
    public boolean cekAkhirGame() {
        //permainan pasti berakhir ketika semua biji berada di dalam rumah
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
    
    //memeriksa apakah ada jalan yang valid untuk pemain yang akan memulai gilirannya
    //jika tidak ada yang valid, maka giliran akan dikembalikan ke pemain yang lain
    private boolean masihAdaJalanValid(int giliran){
        if(giliran==0){
            //giliran Player 1
            for(int i=0;i<7;i++){
                if(p.getLubang()[i].getIsi()!=0){
                    return true;
                }
            }
        }
        else if(giliran==1){
            //giliran Player 2
            for(int i=7;i<14;i++){
                if(p.getLubang()[i].getIsi()!=0){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Method untuk menyiapkan papan sebelum permainan dimulai.
     */
    public void siapkanPapan(){
        p.siapkanPapan();
    }
        
    /**
     * Method untuk memilih jalan terbaik dari semua kemungkinan lubang yang valid.
     * @return Indeks lubang yang mungkin terbaik untuk dipilih.
     */
    public int cariJalanTerbaik(){
        Papan statePapan=p;
        Stack<Papan> states=new Stack<Papan>();
        int alpha=Integer.MIN_VALUE; //batas bawah
        int beta=Integer.MAX_VALUE; //batas atas
        int bestMove=-1; //belum ada best move pada awalnya
        int pilihanTemp=7;
        int d=0; //kedalaman minmax tree
        
        for(int i=7;i<14&&cekInput(i);i++){
            int lubangTerpilih=i;
            states.push(statePapan);
            
            //simpan state papan awal
            Lubang[] lubangAwal=new Lubang[14];
            int rumahAwal0=p.getRumah0();
            int rumahAwal1=p.getRumah1();
            for(int j=0;j<lubangAwal.length;j++){
                lubangAwal[j]=new Lubang();
                lubangAwal[j].setIsi(p.getLubang()[j].getIsi());
                lubangAwal[j].setBisaDiisi(p.getLubang()[j].isBisaDiisi());
            }
            
            //simulasi gerak
            this.gerak(lubangTerpilih);
            //nilai-nilai di papan p sudah berubah karena dipanggil di dalam method gerak()
            if(giliran==0){
                //giliran Player 1 atau giliran lawan (minimum)
                cariMin(alpha,beta,d+1);
            }
            else{
                //giliran Player 2 atau pemain AI (maksimum)
                cariMax(alpha,beta,d+1);
            }
            if(pilihanTemp>alpha){
                alpha=pilihanTemp;
                bestMove=lubangTerpilih;
            }
            if(alpha>=beta){
                //pruning, batas bawah lebih besar dari batas atas
                break;
            }
            //kembalikan papan ke state awal
            p=new Papan(lubangAwal,rumahAwal0,rumahAwal1);
        }
        
        if(bestMove==-1){
            //tidak ada jalan terbaik, pilih lubang valid secara acak
            Random r=new Random();
            while(!cekInput(bestMove)){                
                bestMove=7+r.nextInt(7);
            }
        }
        return bestMove;
    }
    
    /**
     * Method untuk memaksimalkan nilai yang didapat untuk Player 2 dalam kasus ini, yaitu komputer.
     * @param alpha Batas bawah
     * @param beta Batas atas
     * @param d Kedalaman dalam pohon
     * @return Nilai maksimum yang bida didapat dari sisi Player 2.
     */
    public int cariMax(int alpha, int beta,int d){
        Stack<Papan> states=new Stack<Papan>();
        if(cekAkhirGame()||d>0){
            if(giliran==1){
                return p.getRumah1();
            }
            else{
                return p.getRumah0();
            }
        }
        int pilihanTemp=7;
        for(int i=8;i<14&&cekInput(i);i++){
            int lubangTerpilih=i;
            states.push(p);
            
            //simpan state papan awal
            Lubang[] lubangAwal=new Lubang[14];
            int rumahAwal0=p.getRumah0();
            int rumahAwal1=p.getRumah1();
            for(int j=0;j<lubangAwal.length;j++){
                lubangAwal[j]=new Lubang();
                lubangAwal[j].setIsi(p.getLubang()[j].getIsi());
                lubangAwal[j].setBisaDiisi(p.getLubang()[j].isBisaDiisi());
            }
            
            //simulasi gerak
            this.gerak(lubangTerpilih);
            //nilai-nilai di papan p sudah berubah karena dipanggil di dalam method gerak()
            if(giliran==0){
                //giliran Player 1 atau giliran lawan (minimum)
                cariMin(alpha,beta,d+1);
            }
            else{
                //giliran Player 2 atau pemain AI (maksimum)
                cariMax(alpha,beta,d+1);
            }
            if(pilihanTemp>alpha){
                alpha=pilihanTemp;
            }
            if(alpha>=beta){
                //pruning, batas bawah lebih besar dari batas atas
                break;
            }
            //kembalikan papan ke state awal
            p=new Papan(lubangAwal,rumahAwal0,rumahAwal1);
        }
        return alpha;
    }
    
    /**
     * Method untuk meminimumkan nilai yang didapat untuk Player 2 dalam kasus ini, yaitu komputer.
     * @param alpha Batas bawah
     * @param beta Batas atas
     * @param d Kedalaman dalam pohon
     * @return Nilai minimum yang bisa didapat dari sisi Player 2.
     */
    public int cariMin(int alpha, int beta,int d){
        Stack<Papan> states=new Stack<Papan>();
        if(cekAkhirGame()||d>0){
            if(giliran==1){
                p.getRumah1();
            }
            else{
                p.getRumah0();
            }
        }
        int pilihanTemp=7;
        for(int i=8;i<14&&cekInput(i);i++){
            int lubangTerpilih=i;
            states.push(p);
            
            //simpan state papan awal
            Lubang[] lubangAwal=new Lubang[14];
            int rumahAwal0=p.getRumah0();
            int rumahAwal1=p.getRumah1();
            for(int j=0;j<lubangAwal.length;j++){
                lubangAwal[j]=new Lubang();
                lubangAwal[j].setIsi(p.getLubang()[j].getIsi());
                lubangAwal[j].setBisaDiisi(p.getLubang()[j].isBisaDiisi());
            }
            
            //simulasi gerak
            this.gerak(lubangTerpilih);
            //nilai-nilai di papan p sudah berubah karena dipanggil di dalam method gerak()
            if(giliran==0){
                //giliran Player 1 atau giliran lawan (minimum)
                cariMin(alpha,beta,d+1);
            }
            else{
                //giliran Player 2 atau pemain AI (maksimum)
                cariMax(alpha,beta,d+1);
            }
            if(pilihanTemp<beta){
                beta=pilihanTemp;
            }
            if(alpha>=beta){
                //pruning, batas bawah lebih besar dari batas atas
                break;
            }
            //kembalikan papan ke state awal
            p=new Papan(lubangAwal,rumahAwal0,rumahAwal1);
        }
        return beta;
    }
}
