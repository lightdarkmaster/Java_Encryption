import java.util.*;
public class Monoalphabetic {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
       int key = 10;
       String message;
       
       System.out.println("Enter message to be encrypted: ");
       message = sc.nextLine();  
        char [] chars = message.toCharArray();
        
        for (char c: chars){
             c+=key;
           
             System.out.print(c);  
        }
        
    
    }