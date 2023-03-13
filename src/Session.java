import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session extends Thread
{
    private final Socket socket;

    public Session(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        (
            DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        )
        {
            double x;
            double y;

            while (true)
            {
                try
                {
                    dataOutputStream.writeUTF("Enter X: ");
                    x = Double.parseDouble(dataInputStream.readUTF());

                    dataOutputStream.writeUTF("Enter Y: ");
                    y = Double.parseDouble(dataInputStream.readUTF());

                    double result = Server.evaluate(x, y);

                    dataOutputStream.writeUTF("Result: " + result);
                    break;
                }
                catch (NumberFormatException e)
                {
                    dataOutputStream.writeUTF("Invalid number");
                }
            }

            this.socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
