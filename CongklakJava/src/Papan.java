/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author StephanieTania
 */
public class Papan {
    private Lubang[] lubang;
    private int rumah0,rumah1;

    public Papan() {
        rumah0=rumah1=49;
        lubang=new Lubang[14];
        for(int i=0;i<lubang.length;i++){
            lubang[i]=new Lubang();
        }
    }
    
    public Lubang[] getLubang() {
        return lubang;
    }

    public int getRumah0() {
        return rumah0;
    }

    public void setRumah0(int rumah0) {
        this.rumah0 = rumah0;
    }

    public int getRumah1() {
        return rumah1;
    }

    public void setRumah1(int rumah1) {
        this.rumah1 = rumah1;
    }
    
    public void siapkanPapan(){
        for(int i=0;i<14;i++){
            lubang[i].setIsi(0);
            lubang[i].setBisaDiisi(true);
        }
        int i=0;
        while(i<7&&rumah0>=7){
            lubang[i].setIsi(7);
            i++;
            rumah0-=7;
        }
        while(i<7){
            lubang[i].setBisaDiisi(false);
            i++;
        }        
        while(i<14&&rumah1>=7){
            lubang[i].setIsi(7);
            i++;
            rumah1-=7;
        }
        while(i<14){
            lubang[i].setBisaDiisi(false);
            i++;
        }
    }
    
    public void printPapan(){
        System.out.print("    ");
        for(int i=7;i<14;i++){
            System.out.printf(" |%02d| ",lubang[i].getIsi());
        }
        System.out.println("");
        System.out.printf("|%02d|",rumah0);
        for(int i=0;i<7;i++){
            System.out.print("      ");
        }
        System.out.printf("|%02d|",rumah1);
        System.out.println("");
        System.out.print("    ");
        for(int i=6;i>=0;i--){
            System.out.printf(" |%02d| ",lubang[i].getIsi());
        }
        System.out.println("");
    }
}
