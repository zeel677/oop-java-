import java.util.Scanner;
public class passwordcheck 
{
    public static void main(String []arg)
    {
        String password;
         Scanner scanner=new Scanner(System.in);
        System.out.print("ENTER PASSWORD=");
       password = scanner.nextLine();
        if (password.length()<8) 
        {
            System.out.println("Password is weak of length"+password.length());
        }
        else if (!password.matches(".*[A-Z].*")) 
        {
            System.out.println("Password is medium must add [A-Z] and special character.");
        }
        else if(!password.matches(".*[a-z].*"))
        {
            System.out.println("Password is medium must add [a-z] and special character");
        }
        else if(!password.matches(".*[#%@$&].*"))
        {
            System.out.println("Password medium.");
         }
       else 
       {
           System.out.println("Password is strong .");
       }
       
    }
    Scanner.close();
}