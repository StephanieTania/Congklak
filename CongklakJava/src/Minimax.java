/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Kelas yang merepresentasikan algoritma Minimax.
 * @author user
 */
public class Minimax {

    private int[] isiLubang;
    private boolean[] bisaDiisi;
    private int cobaRumah0, cobaRumah1;
    private int giliran;

    /**
     * Constructor kelas Minimax.
     * Atribut-atribut penampung nilai sementara disiapkan.
     */
    public Minimax() {
        isiLubang = new int[14];
        bisaDiisi = new boolean[14];
        giliran=1;
    }

    //mengganti kondisi atribut-atribut penampung agar sama dengan keadaan yang akan diperiksa.
    private void setState(Papan p) {
        Lubang[] lubang=p.getLubang();
        for (int i = 0; i < lubang.length; i++) {
            isiLubang[i] = lubang[i].getIsi();
            if (lubang[i].isBisaDiisi()) {
                bisaDiisi[i] = true;
            }
        }
        cobaRumah0 = p.getRumah0();
        cobaRumah1 = p.getRumah1();
    }

    //simulasi permainan dalam 1 kali jalan
    private void cobaGerak(int idxLubang) {
        int tangan = isiLubang[idxLubang];
        isiLubang[idxLubang]=0;
        boolean masukRumah = false;
        int idx = idxLubang;
        do {
            idx = (idx + 1) % 14; //maju ke lubang selanjutnya
            if (bisaDiisi[idx]) {
                if (giliran == 0 && idx == 7) {
                    //Player 1 masuk rumah
                    if (tangan == 1) {
                        //Player 1 berakhir di rumah
                        masukRumah = true;
                        cobaRumah0++;
                        break;
                    } else {
                        tangan -= 1;
                        cobaRumah0++;
                    }
                } else if (giliran == 1 && idx == 0) {
                    //Player 2 masuk rumah
                    if (tangan == 1) {
                        //Player 2 berakhir di rumah
                        masukRumah = true;
                        cobaRumah1++;
                        break;
                    } else {
                        tangan -= 1;
                        cobaRumah1++;
                    }
                }
                tangan -= 1; //ambil biji dari tangan
                isiLubang[idx]++; //masukkan biji dari tangan ke lubang
            }
        } while (tangan > 0);
        if (!masukRumah) {
            if (isiLubang[idx]==1) {
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
                        int biji = 1;
                        isiLubang[idx]=0;

                        biji += isiLubang[13-idx];
                        isiLubang[13-idx]=0;

                        cobaRumah0+=biji;
                        giliran = (giliran + 1) % 2;
                    }
                } else if (giliran == 1) {
                    //giliran Player 2
                    if (idx < 7) {
                        //lubang terakhir di daerah lawan
                        //ganti giliran
                        giliran = (giliran + 1) % 2;
                    } else {
                        //lubang terakhir di daerah sendiri
                        //lubang terakhir di daerah sendiri
                        //ambil biji di lubang terakhir dan seberangnya, simpan di rumah
                        int biji = 1;
                        isiLubang[idx]=0;

                        biji += isiLubang[13-idx];
                        isiLubang[13-idx]=0;

                        cobaRumah1+=biji;
                        giliran = (giliran + 1) % 2;
                    }
                }
            } else {
                cobaGerak(idx);
            }
        }
    }
    
    /**
     * Method untuk mencari indeks lubang yang menghasilkan solusi terbaik.
     */
    public void cariSolusiTerbaik(){
        
    }
}
