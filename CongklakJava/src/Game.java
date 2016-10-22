/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Game {

    private int giliran;
    private Papan p;

    public Game() {
        giliran = 0; //giliran 0 adalah Player 1, giliran 1 adalah Player 2
        p = new Papan();
    }

    public void gerak(int idxLubang) {
        int tangan = p.getLubang()[idxLubang].getIsi();
        p.getLubang()[idxLubang].setIsi(0);
        boolean masukRumah = false;
        int idx = idxLubang;
        do {
            idx = (idx + 1) % 14; //maju ke lubang selanjutnya
            if (p.getLubang()[idx].isBisaDiisi()) {
                if (giliran == 0 && idx == p.getIsiAwal1() && tangan == 1) {
                    //Player 1 berakhir di rumah
                    masukRumah = true;
                    p.setRumah0(p.getRumah0() + 1);
                    break;
                }
                if (giliran == 1 && idx == p.getIsiAwal0() && tangan == 1) {
                    //Player 2 berakhir di rumah
                    masukRumah = true;
                    p.setRumah1(p.getRumah1() + 1);
                    break;
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
                    if (idx >= p.getIsiAwal1()) {
                        //lubang terakhir di daerah lawan
                        //ganti giliran
                    } else {
                        //lubang terakhir di daerah sendiri
                        //ambil biji di lubag terakhir dan seberangnya, simpan di rumah
                        p.setRumah0(p.getRumah0()+p.getLubang()[idx].getIsi()+p.getLubang()[13-idx].getIsi());
                    }
                }
            }
            giliran = (giliran + 1) % 2;
        }
    }

    public boolean cekAkhirGame() {
        if (p.getRumah0() + p.getRumah1() == 98) {
            return true;
        }
        return false;
    }
}