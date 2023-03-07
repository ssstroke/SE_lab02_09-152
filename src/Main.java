import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length > 0)
            System.out.println("Hello, " + args[0] + "!");

        double x;
        double y;

        try (Scanner sysInScanner = new Scanner(System.in))
        {
            System.out.print("Enter real X: ");
            x = Double.parseDouble(sysInScanner.nextLine());

            System.out.print("Enter real Y: ");
            y = Double.parseDouble(sysInScanner.nextLine());

            double result = 3 * Math.pow(Math.cos(x - Math.PI / 6), 2) /
            (0.5 + Math.sin(y * y));

            System.out.print("Result: ");
            if (Double.isInfinite(result))
                System.out.println("undefined");
            else
                System.out.println(result);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Forbidden value");
        }
    }
}
