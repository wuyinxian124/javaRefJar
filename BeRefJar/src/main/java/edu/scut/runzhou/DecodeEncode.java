package edu.scut.runzhou;

/**
 * Hello world!
 *
 */
public class DecodeEncode
{
    public void action(){

    }
    public String action1(String par){
        System.out.println("-----"+par);
        return "-----"+par;
    }
    public byte[] action(byte[] par){
        String lala = "++++++++"+new String(par);
        System.out.println(lala);
        return lala.getBytes();
    }
    public static String action(String par){
        System.out.println("-----"+par);
        return "-----"+par;
    }
}
