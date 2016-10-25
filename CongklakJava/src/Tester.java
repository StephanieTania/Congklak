
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Kelas utama untuk menjalankan game.
 * 
* @author user
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game g = new Game();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Setiap pemain akan bergiliran memasukkan angka indeks lubang yang akan diambil bijinya.");
        System.out.println("2. Pemain tidak dapat mengambil dari lubang yang kosong atau dari lubang daerah lawan.");
        System.out.println("3. Daerah Player 1 adalah bagian bawah dan daerah Player 2 adalah bagian atas.");
        System.out.println("4. Indeks lubang adalah 0 sampai 13 dengan 0 sampai 6 adalah lubang daerah Player 1 dan 7 sampai 13 adalah lubang daerah Player 2.");
        System.out.println("5. Demikian nomor-nomor lubang pada papan:");
        System.out.println("         |07| |08| |09| |10| |11| |12| |13|");
        System.out.println("|RumahP1|                                  |RumahP2|");
        System.out.println("         |06| |05| |04| |03| |02| |01| |00|");
        System.out.println("");
        boolean lanjut = true;
        while (lanjut) {
            System.out.print("Masukan mode permainan: (0 untuk lawan pemain lain dan 1 untuk lawan komputer) ");
            int mode = sc.nextInt();
            while (mode < 0 || mode > 1) {
                System.out.println("Mode permainan tidak valid. Silakan dicoba lagi.");
                System.out.print("Masukan mode permainan: (0 untuk lawan pemain lain dan 1 untuk lawan komputer) ");
                mode = sc.nextInt();
            }
            g.printPapan();        
            if (mode == 0) {
                lawanManusia(sc, g);
            } else {
                lawanKomputer(sc, g);
            }
            int pemenang=g.getPemenang();
            switch(pemenang){
                case 0:
                    System.out.println("Player 1 adalah pemenangnya!");
                case 1:
                    System.out.println("Player 2 adalah pemenangnya!");
                default:
                    System.out.println("Permainan seri! Player 1 dan Player 2 mempunyai skor akhir sama!");
            }
            System.out.print("Apakah Anda ingin melanjutkan permainan? (Y/N) ");
            char input = sc.next().charAt(0);
            if (input == 'Y'||input=='y') {
                lanjut = true;
            } else {
                lanjut = false;
            }
        }
    }

    private static void lawanManusia(Scanner sc, Game g) {
        while (!g.cekAkhirGame()) {
            if (g.getGiliran() == 0) {
                System.out.print("Giliran Player 1: (0,1,2,..,6) ");
                int idx = sc.nextInt();

                //cek input, harus valid
                while (!g.cekInput(idx)) {
                    System.out.println("Input indeks lubang salah, silakan dicoba lagi.");
                    System.out.print("Giliran Player 1: (0,1,2,..,6) ");
                    idx = sc.nextInt();
                }

                g.gerak(idx);
                g.printPapan();
            } else {
                System.out.print("Giliran Player 2: (7,8,9,..,13) ");
                int idx = sc.nextInt();

                //cek input, harus valid
                while (!g.cekInput(idx)) {
                    System.out.println("Input indeks lubang salah, silakan dicoba lagi.");
                    System.out.print("Giliran Player 2: (7,8,9,..,13) ");
                    idx = sc.nextInt();
                }

                g.gerak(idx);
                g.printPapan();
            }
            System.out.println("--- ");
        }
    }

    private static void lawanKomputer(Scanner sc, Game g) {
        while (!g.cekAkhirGame()) {
            if (g.getGiliran() == 0) {
                System.out.print("Giliran Player 1: (0,1,2,..,6) ");
                int idx = sc.nextInt();

                //cek input, harus valid
                while (!g.cekInput(idx)) {
                    System.out.println("Input indeks lubang salah, silakan dicoba lagi.");
                    System.out.print("Giliran Player 1: (0,1,2,..,6) ");
                    idx = sc.nextInt();
                }
                
                g.gerak(idx);
                g.printPapan();
            } else {
                System.out.print("Giliran Player 2: (7,8,9,..,13) ");
                int idx = g.cariJalanTerbaik();
                System.out.println(idx);
                g.gerak(idx);
                g.printPapan();
            }
            System.out.println("--- ");
        }
    }
}
