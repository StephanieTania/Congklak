
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
        while(!g.cekAkhirGame()){
            if(g.getGiliran()==0){
                System.out.print("Giliran Player 1: (0,1,2,..,6)\n");
                int idx=sc.nextInt();
                
                //cek input, harus valid
                
                g.gerak(idx);
                g.printPapan();
            }
            else{
                System.out.println("Giliran Player 2: (7,8,9,..,13)\n");
                int idx=sc.nextInt();
                
                //cek input, harus valid
                
                g.gerak(idx);
                g.printPapan();
            }
        }            
    }
    
}
