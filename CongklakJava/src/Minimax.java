/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Minimax {
    private int[] isiLubang;
    private boolean[] bisaDiisi;
    
    public Minimax(){
        isiLubang=new int[14];
        bisaDiisi=new boolean[14];
    }
    
    public void setState(Lubang[] lubang){
        for(int i=0;i<lubang.length;i++){
            isiLubang[i]=lubang[i].getIsi();
            if(lubang[i].isBisaDiisi()){
                bisaDiisi[i]=true;
            }
        }
    }
}
