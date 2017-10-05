/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author Firmanda
 */
public class KNN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO codeint  application logic here
        double voteKaya,voteMiskin,voteSedang;
       double [] luasBangunan = {9,10,15,30,16,25,9,8,10,14,12};
       double [] pendapatanBulan = {2000000, 2500000,4000000,5000000
               ,5350000,7000000,500000,700000,1500000,1000000,1750000};
       double [] frekuensiMakan = {3,2,2,4,3,5,0.5,1,2,1,2};
       String [] kategori = {"sedang","sedang","sedang","kaya","kaya","kaya","miskin","miskin","miskin","miskin","?"}; 
            
       double rata2LuasBangunan,rata2PendapatanBulan,rata2FrekuensiMakan;
       double stdevLuasBangunan,stdevPendapatanBulan,stdevFrekuensiMakan;
       
       double [] zScoreLuasBangunan = new double[11];
       double [] zScorePendapatanBulan = new double[11];
       double [] zScoreFrekuensiMakan = new double[11];
       
       double [] euclidean = new double[10];
       
      rata2LuasBangunan = rata2(luasBangunan); 
      rata2PendapatanBulan = rata2(pendapatanBulan);
      rata2FrekuensiMakan = rata2(frekuensiMakan);
      
      stdevFrekuensiMakan = stdev(frekuensiMakan);
      stdevLuasBangunan = stdev(luasBangunan);
      stdevPendapatanBulan = stdev(pendapatanBulan);
      
      zscore(zScoreLuasBangunan, luasBangunan, stdevLuasBangunan, rata2LuasBangunan);
      
      zscore(zScorePendapatanBulan, pendapatanBulan, stdevPendapatanBulan, rata2PendapatanBulan);
      
      zscore(zScoreFrekuensiMakan, frekuensiMakan, stdevFrekuensiMakan, rata2FrekuensiMakan);
            
        euclidian(euclidean, zScoreLuasBangunan, zScorePendapatanBulan, zScoreFrekuensiMakan);
        voteKaya = weightKaya(euclidean);
        voteMiskin = weightMiskin(euclidean);
        voteSedang = weightSedang(euclidean);
        tampil(luasBangunan, pendapatanBulan, frekuensiMakan, kategori, rata2LuasBangunan, rata2PendapatanBulan, rata2FrekuensiMakan, stdevLuasBangunan, stdevPendapatanBulan, stdevFrekuensiMakan, zScoreLuasBangunan, zScorePendapatanBulan, zScoreFrekuensiMakan, euclidean, voteKaya, voteMiskin, voteSedang);
       
        
    }
    
    public static double rata2(double [] arrayData){
    double kembalian = 0;
    for(int i = 0; i<arrayData.length;i++){
        kembalian +=arrayData[i];       
    }
    kembalian = kembalian/arrayData.length;
    return kembalian;
    }
    
    
     public static double jumlahdata(double [] arrayData){
    double kembalian = 0;
    for(int i = 0; i<arrayData.length;i++){
        kembalian +=arrayData[i];       
    }
    return kembalian;
    }
    
    public static double stdev(double [] arrayData){
       
        double X = 0;
        for (int i = 0; i < arrayData.length; i++) {
            X += Math.pow(arrayData[i], 2);        
        }
        
        double meanX = Math.pow(rata2(arrayData), 2);
        
        double std = Math.sqrt((X/arrayData.length)-meanX);
        
        return std;
    }
    
    public static void zscore(double zscore[], double input[], double std, double rata2){
        for (int i = 0; i < zscore.length; i++) {
            zscore[i] = (input[i] - std)/rata2;
        }
    }
    
    public static void euclidian(double euclidian[], double zScoreRumah[], double zScorePendapatan[], double zScoreFrekuensi[]){
        for (int i = 0; i < euclidian.length; i++) {
            euclidian[i] = Math.pow((zScoreRumah[zScoreRumah.length-1] - zScoreRumah[i]), 2)
                    + Math.pow((zScorePendapatan[zScorePendapatan.length-1] - zScorePendapatan[i]), 2)
                    + Math.pow((zScoreFrekuensi[zScoreFrekuensi.length-1] - zScoreFrekuensi[i]), 2);
            
            euclidian[i] = Math.sqrt(euclidian[i]);
        }
    
    }
    
    public static double weightKaya(double [] arrayData){
    double kaya=0;
    kaya = (1/Math.pow(arrayData[3], 2))+(1/Math.pow(arrayData[4], 2))+(1/Math.pow(arrayData[5], 2));
    return kaya;
    }
    
     public static double weightSedang(double [] arrayData){
    double kaya=0;
    kaya = (1/Math.pow(arrayData[0], 2))+(1/Math.pow(arrayData[1], 2))+(1/Math.pow(arrayData[2], 2));
    return kaya;
    }
      public static double weightMiskin(double [] arrayData){
    double kaya=0;
    kaya = (1/Math.pow(arrayData[6], 2))+(1/Math.pow(arrayData[7], 2))+(1/Math.pow(arrayData[8], 2))+(1/Math.pow(arrayData[9], 2));
    return kaya;
    }
      
      public static void tampil(double [] luasBangunan,double [] pendapatanBulan, double [] frekuensiMakan,String [] kategori, double rata2luasBangunan, double rata2pendapatanBulan,double rata2frekuensiMakan, double stdevluasBangunan,double stdevpendapatnBulan,double stdevfrekuensiMakan,double [] zScoreLuasBangunan,double [] zPendapatanBulan, double [] zScoreFrekuensiMakan, double [] euclidean, double voteKaya, double voteMiskin, double voteSedang){
          System.out.println("Data Awal:");
          System.out.println("Luas Bangunan\t\tPendapatan Bulanan\t\tFrekuensiMakan\t\tKategori");
          for (int i = 0; i < luasBangunan.length; i++) {
              System.out.println(luasBangunan[i]+"\t\t\t"+pendapatanBulan[i]+"\t\t\t"+frekuensiMakan[i]+"\t\t\t"+kategori[i]);    
              }
          System.out.println("\nRata-rata Luas Bangunan:"+rata2luasBangunan);
          System.out.println("Rata-rata Pendapatan Bulanan:"+rata2pendapatanBulan);
          System.out.println("Rata-rata Frekuensi:"+rata2frekuensiMakan);
          
          System.out.println("\nStandar Deviasi Luas Bangunan:"+stdevluasBangunan);
          System.out.println("Standar Deviasi Pendapatan Bulanan:"+stdevpendapatnBulan);
          System.out.println("Standar Deviasia Frekuensi:"+stdevfrekuensiMakan);
          
          System.out.println("\nZ-Score Luas Bangunan\t\tZ-Score Pendapatan Bulanan\t\tZ-Score FrekuensiMakan");
          for (int i = 0; i < luasBangunan.length; i++) {
              System.out.println(zScoreLuasBangunan[i]+"\t\t\t"+zPendapatanBulan[i]+"\t\t\t"+zScoreFrekuensiMakan[i]);    
              }
          
           System.out.println("\nEuclidean");
          for (int i = 0; i < euclidean.length; i++) {
              System.out.println(euclidean[i]);    
              }
          
          System.out.println("\nWeighted");
          System.out.println("VoteKaya:"+voteKaya);
          System.out.println("VoteMiskin:"+voteMiskin);
          System.out.println("VoteSedang:"+voteSedang);
          
          
       
          System.out.println("\nKarena paling besar adalah Kaya maka class yang dicari adalah Kaya");
          
      }
      
    
    
}
