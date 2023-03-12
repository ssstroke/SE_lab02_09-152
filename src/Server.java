import java.io.*;
import java.net.*;

public class Server
{
    public static final int OPEN    =     0;
    public static final int CLOSED  =    -1;

    private static final int PORT = 34522;

    public static void main(String[] args) throws IOException
    {
        try (ServerSocket serverSocket = new ServerSocket(PORT);)
        {
            System.out.println("Server is up and running");

            while (true)
            {
                Session session = new Session(serverSocket);
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
    private int messsageCounter = 0;

    public Session(ServerSocket serverSocket) throws IOException
    {
        this.socket = serverSocket.accept();
    }

    @Override
    public void run()
    {
        try
        (
            DataInputStream input = new DataInputStream(this.socket.getInputStream());
            DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
        )
        {
            this.username = input.readUTF();
            System.out.println(this.username + " connected");
            output.writeUTF("Hello, " + this.username + "!");

            while (true)
            {
                // Send client one byte to indicate server's state
                output.write(Server.OPEN);

                String clientMessage = input.readUTF();
                this.messsageCounter++;

                if (clientMessage.equals("bye"))
                {
                    output.writeUTF("Closing socket");
                    this.closeSocket();
                    break;
                }
                else
                {
                    System.out.printf("Got message (%d) from %s: %s\n",
                    this.messsageCounter, this.username, clientMessage);

                    output.writeUTF(this.messsageCounter + " - " + clientMessage);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void closeSocket() throws IOException
    {
        this.socket.close();
        System.out.println("User " + this.username + " left");
    }
}
