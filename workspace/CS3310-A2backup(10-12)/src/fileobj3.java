
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class fileobj3
{
public static void main(String args[]) throws IOException
{

String s = "";


//THIS IS THE VALUE I WANT TO CONVERT
int poo = 1958201;

String hexstr = Integer.toHexString(poo); 
System.out.println("Converts Int to Hex to Ascii");
System.out.print("Int= "+poo+" \nHex= "+ hexstr+"\n");

if(hexstr.length()==6){
	hexstr = "00"+hexstr.substring(0, 6);
}

int decpoo = 0;
byte[] buf = new byte[4];
byte[] hexbuf= new byte[4];;

String Hexarry[] = new String[4];
int Decarry[] = new int[4];
char Chararry[] = new char[4];
for (int c=0; c<hexstr.length(); c=c+2) {
Hexarry[c/2] = hexstr.substring(c, c+2);	
Decarry[c/2] = hex2decimal(hexstr.substring(c, c+2));
Chararry[c/2] = (char)Decarry[c/2];
System.out.println("Hex= "+ Hexarry[c/2] +" Dec= "+Decarry[c/2] +" ASCII= "+(char)Decarry[c/2] );

s = s+(char)Decarry[c/2];
System.out.println((byte)(Chararry[c/2]));
} 
System.out.println("\nNow to convert back to Int to check: "+s);

buf=s.getBytes();
hexbuf=s.getBytes();
ByteArrayInputStream b = new ByteArrayInputStream(buf);
DataInputStream d = new DataInputStream(b);
ByteArrayInputStream x = new ByteArrayInputStream(hexbuf);
DataInputStream y = new DataInputStream(x); 



System.out.print("Byte values:  ");
for (int i=0; i<buf.length; i++){
System.out.print(buf[i]+" ");
}
System.out.println("");
System.out.print("Hex values:  ");
for (int i=0; i<hexbuf.length; i++){
System.out.print(Integer.toHexString(hexbuf[i])+" ");

}
System.out.println("");

int test = y.readInt();
String test2 = String.valueOf(test);
System.out.println(Integer.parseInt(test2));
if(Integer.parseInt(test2)==poo){
	System.out.println("They are equal");	
}


}


public static int hex2decimal(String s) {
    String digits = "0123456789ABCDEF";
    s = s.toUpperCase();
    int val = 0;
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        int d = digits.indexOf(c);
        val = 16*val + d;
    }
    return val;
}

}



/*
1234 = 49-50-51-52 = 31-32-33-34 = 825373492

goal:1958201

 current result = 1950625
 
 


As we can see, each "0" in the String is turning into a byte of 48. Mysterious until we realize that 48 is hex 30 which is the 
charset representation of zero. So if we get out our handy hex to decimal converter and type in 30303030 and convert that to 
decimal we get 808464432 which is what the above program prints out.

So let's look at your example:

For the int "1234" converts to bytes in decimal = 49-50-51-52 which converts to 31-32-33-34 in hex. Converting the number 
31323334 to decimal gives: 825373492 which is exactly the answer. I will leave the rest an an excercise to the reader.

*/


