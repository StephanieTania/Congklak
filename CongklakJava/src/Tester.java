
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game g=new Game();
        Scanner sc=new Scanner(System.in);
        g.printPapan();
        System.out.println("1. Setiap pemain akan bergiliran memasukkan angka indeks lubang yang akan diambil bijinya.");
        System.out.println("2. Pemain tidak dapat mengambil dari lubang yang kosong atau dari lubang daerah lawan.");
        System.out.println("3. Daerah Player 1 adalah bagian bawah dan daerah Player 2 adalah bagian atas.");
        System.out.println("4. Indeks lubang adalah 0 sampai 13 dengan 0 sampai 6 adalah lubang daerah Player 1 dan 7 sampai 13 adalah lubang daerah Player 2.");
        System.out.println("5. Demikian nomor-nomor lubang pada papan:");
        System.out.println("         |07| |08| |09| |10| |11| |12| |13|");
        System.out.println("|RumahP1|                                  |RumahP2|");
        System.out.println("         |06| |05| |04| |03| |02| |01| |00|");
        System.out.println("");
        while(!g.cekAkhirGame()){
            if(g.getGiliran()==0){
                System.out.print("Giliran Player 1: (0,1,2,..,6) ");
                int idx=sc.nextInt();
                
                //cek input, harus valid
                while(!g.cekInput(idx)){
                    System.out.println("Input indeks lubang salah, silakan dicoba lagi.");
                    System.out.print("Giliran Player 1: (0,1,2,..,6) ");
                    idx=sc.nextInt();
                }
                
                g.gerak(idx);
                g.printPapan();
            }
            else{
                System.out.print("Giliran Player 2: (7,8,9,..,13) ");
                int idx=sc.nextInt();
                
                //cek input, harus valid
                while(!g.cekInput(idx)){
                    System.out.println("Input indeks lubang salah, silakan dicoba lagi.");
                    System.out.print("Giliran Player 2: (7,8,9,..,13) ");
                    idx=sc.nextInt();
                }
                
                g.gerak(idx);
                g.printPapan();
            }
            System.out.println("---\n");
        }            
    }
    
}
