import java.util.Scanner;

class cipher{
    public static void main(String[] args){


        String plaintext;
        String keys;


        System.out.println("Cipher");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a text to be encrypted: ");
        plaintext = input.nextLine();

        System.out.println("Enter Keys: ");
        keys = input.nextLine();

    }

    public void validation(){
        System.out.println("Add code here: ");
    }

    public static void ciphering(){
        int i;

        for(i=0; i<=25; i++){
            System.out.println(i);
        }
    }

    String name;
    String output;
    String plaintext3;

    
    public void conditionStatement(){
        if(name.isEmpty()) {
            System.out.println("Error in this code we need back up");
        }else{
            output.getText(plaintext3.toUpperCase());
        }
    }
}



