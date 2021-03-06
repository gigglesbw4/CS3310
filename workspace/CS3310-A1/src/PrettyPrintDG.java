

/**
 *
 * @author Devlin Grasley (JesusCat)
 */

import java.io.File;
import java.util.Scanner;
import java.text.NumberFormat;

public class PrettyPrintDG {
    
    public static void main (String [] args){
        File file = new File("Backup.csv");
        Scanner scanIn;
        try{
                scanIn=new Scanner (file);
                if(!scanIn.hasNext()){
                    scanIn = new Scanner (file,"Cp1252");
                }
                int location = 0;
                String[] s = scanIn.nextLine().split(",");
                System.out.println("RootPtr is "+s[0]+", N is "+s[1]+", NextEmptyPtr is "+s[2]);
                System.out.println(String.format("%-3.3s> %-3.3s %-3.3s %-3.3s %-3.3s %-18.18s %-15.15s %10.10s %13.13s %4.4s %5.5s",rightPad("LOC",3,'-'),rightPad("LCH",3,'-'),rightPad("RCH",3,'-'),rightPad("CDE", 3,'-'),rightPad("ID", 3,'-'),rightPad("NAME", 18,'-'),rightPad("CONTINENT", 15,'-'),leftPad("AREA", 10, '-'),leftPad("POPULATION",13,'-'),leftPad("LIFE",4,'-'),leftPad("TOMB",5,'-')));
                while(scanIn.hasNext()){
                    s = scanIn.nextLine().replaceAll("NULL","-1").split(",");
                    System.out.println(String.format("%03d> %03d %03d %-3.3s %-3.3s %-18.18s %-15.15s ",location, Integer.parseInt(s[0]),Integer.parseInt(s[1]),s[2],Integer.parseInt(s[3]),s[4],s[5]).replace("-1  ", "NULL").replace("-1.0", "NULL")+String.format("%10.10s %13.13s %4.4s %5.5b",NumberFormat.getIntegerInstance().format(Long.parseLong(s[6])),NumberFormat.getIntegerInstance().format(Long.parseLong(s[7])),Double.parseDouble(s[8]),Integer.parseInt(s[3])==0).replace("  -1", "NULL").replace("-1.0", "NULL"));
                    location++;
                }
                scanIn.close();
        }catch (Exception e){
            System.out.println("[ERROR] Dude, something went wrong! \n"+e);
            e.printStackTrace();
        }
    }
    
    
    private static String rightPad (String s, int n, char c){
        if( s.length()<n){
            for(int i=s.length();i<n;i++){
                s+=c;
            }
        }
        return s;
    }
    private static String leftPad (String s, int n, char c){
        if( s.length()<n){
            for(int i=s.length();i<n;i++){
                s=c+s;
            }
        }
        return s;
    }
    

}
