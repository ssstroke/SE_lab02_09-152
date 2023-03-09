import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client
{
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;

    public static void main(String[] args)
    {
        try
        (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
        )
        {
            System.out.print("Enter your name: ");
            String username = scanner.nextLine();

            output.writeUTF(username);

            String serverMessage = input.readUTF();
            System.out.println(serverMessage);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
