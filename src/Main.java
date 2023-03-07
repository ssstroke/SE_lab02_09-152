import java.util.ArrayList;

public class Main
{
    private static String username;
    private static int variant;
    private static ArrayList<Double> argsArrayList = new ArrayList<>();
    private static double result;

    public static void main(String[] args)
    {
        if (args.length < 3)
        {
            printUsage();
            return;
        }

        try
        {
            setData(args);
        }
        catch (NumberFormatException e)
        {
            printUsage();
            return;
        }

        try
        {
            switch (variant)
            {
                case 1:
                    result = v1(argsArrayList);
                    break;
                case 2:
                    result = v2(argsArrayList);
                    break;
                case 3:
                    result = v3(argsArrayList);
                    break;
                case 4:
                    result = v4(argsArrayList);
                    break;
                case 5:
                    result = v5(argsArrayList);
                    break;
                case 6:
                    result = v6(argsArrayList);
                    break;
                case 7:
                    result = v7(argsArrayList);
                    break;
                default:
                    printUsage();
                    return;
            }
        }
        catch (IllegalArgumentException e)
        {
            printUsage();
            return;
        }

        printResult();
    }

    private static void setData(String[] args)
    throws NumberFormatException
    {
        username = args[0];
        variant = Integer.parseInt(args[1]);
        for (int i = 2; i != args.length; i++)
            argsArrayList.add(Double.parseDouble(args[i]));
    }

    private static double v1(ArrayList<Double> args)
    throws IllegalArgumentException
    {
        if (args.size() != 5)
        throw new IllegalArgumentException();

        double a = args.get(0);
        double b = args.get(1);
        double c = args.get(2);
        double n = args.get(3);
        double x = args.get(4);

        return (5 * Math.pow(a, n * x)) / (b + c) -
        Math.sqrt(Math.abs(Math.cos(Math.pow(x, 3))));
    }

    private static double v2(ArrayList<Double> args)
    throws IllegalArgumentException
    {
        if (args.size() != 4)
        throw new IllegalArgumentException();

        double a = args.get(0);
        double x = args.get(1);
        double y = args.get(2);
        double o = args.get(3);

        return Math.abs(x - y) / Math.pow(1 + 2 * x, a) -
        Math.pow(Math.E, Math.sqrt(1 + o));
    }

    private static double v3(ArrayList<Double> args)
    throws IllegalArgumentException
    {
        if (args.size() != 4)
        throw new IllegalArgumentException();

        double a0 = args.get(0);
        double a1 = args.get(1);
        double a2 = args.get(2);
        double x = args.get(3);

        return Math.sqrt(a0 + a1 * x + a2 *
        Math.pow(Math.abs(Math.sin(x)), 1.0 / 3.0));
    }

    private static double v4(ArrayList<Double> args)
    throws IllegalArgumentException
    {
        if (args.size() != 2)
        throw new IllegalArgumentException();

        double a = args.get(0);
        double x = args.get(3);

        return Math.log(Math.abs(Math.pow(a, 7))) +
        Math.atan(x * x) + Math.PI / Math.sqrt(Math.abs(a + x));
    }

    private static double v5(ArrayList<Double> args)
    throws IllegalArgumentException
    {
        if (args.size() != 5)
        throw new IllegalArgumentException();

        double a = args.get(0);
        double b = args.get(1);
        double c = args.get(2);
        double d = args.get(3);
        double x = args.get(4);

        return Math.pow((a + b) * (a + b) / (c + d) +
        Math.pow(Math.E, Math.sqrt(x + 1)), 0.25);
    }

    private static double v6(ArrayList<Double> args)
    throws IllegalArgumentException
    {
        if (args.size() != 1)
        throw new IllegalArgumentException();

        double x = args.get(0);

        return Math.pow(Math.E, 2 * Math.sin(4 * x) + 
        Math.pow(Math.cos(x * x), 2) / (3 * x));
    }

    private static double v7(ArrayList<Double> args)
    throws IllegalArgumentException
    {
        if (args.size() != 1)
        throw new IllegalArgumentException();

        double x = args.get(0);

        return 0.25 * ((1 + x * x) / (1 - x) + 0.5 * Math.tan(x));
    }

    private static void printResult()
    {
        System.out.println("Hello, " + username + "!");
        System.out.println("Variant: " + variant);

        for (Double arg : argsArrayList)
            System.out.print(arg + " ");
        System.out.println();
        
        if (Double.isInfinite(result))
            System.out.println("Result: undefined");
        else
            System.out.println("Result: " + result);
    }

    private static void printUsage()
    {
        System.out.printf("""
        Usage: Main <username> <variant> [args...]\n
        where variant is an integer in [1-7] range;
        where args are real numbers and args quantity depends on variant:
        1 - 5 args\t(a, b, c, n, x)
        2 - 4 args\t(a, x, y, o)
        3 - 4 args\t(a0, a1, a2, x)
        4 - 2 args\t(a, x)
        5 - 5 args\t(a, b, c, d, x)
        6 - 1 arg\t(x)
        7 - 1 arg\t(x)
        """);
    }
}
