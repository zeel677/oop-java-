class Sum extends Thread
{
public void run()
{
    int sum=0;
    for(int i=0;i<=10;i++)
    {
        sum+=i;
    }
    System.out.println("SUM="+sum);
}
}
public class AddNumber{
    public static void main(String[] args)
    {
             Sum s=new Sum();
             s.start();
    }
}