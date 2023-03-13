import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;

    public static void main(String[] args)
    {
        try
        (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
        )
        {
            System.out.println("Connected to " + socket.getInetAddress());

            while (true)
            {
                String serverMessage = dataInputStream.readUTF();
                System.out.println("SERVER: " + serverMessage);

                String clientMessage = scanner.nextLine();
                dataOutputStream.writeUTF(clientMessage);
            }
        }
        catch (IOException e)
        {
            System.out.println("Disconnected");
        }
    }
}
