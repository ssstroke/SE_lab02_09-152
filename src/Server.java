import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
    private static final int PORT = 34522;

    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {
            while (!serverSocket.isClosed())
            {
                Session session = new Session(serverSocket.accept());
                session.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static double evaluate(double x, double y)
    {
        return 3 * Math.pow(Math.cos(x - Math.PI / 6), 2) /
            (0.5 + Math.sin(y * y));
    }
}
