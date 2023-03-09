import java.io.*;
import java.net.*;

public class Server
{
    private static final int PORT = 34522;

    public static void main(String[] args)
    {
        try (ServerSocket server = new ServerSocket(PORT);)
        {
            System.out.println("Server is up and running.");

            while (true)
            {
                Session session = new Session(server.accept());
                session.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

class Session extends Thread
{
    private final Socket socket;
    private String username = null;

    public Session(Socket socketForClient)
    {
        this.socket = socketForClient;
    }

    public void run()
    {
        try
        (
            DataInputStream input = new DataInputStream(this.socket.getInputStream());
            DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
        )
        {
            this.username = input.readUTF();

            System.out.println(this.username + " has come to us.");
            output.writeUTF("Hello, " + this.username + "!");

            this.socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
