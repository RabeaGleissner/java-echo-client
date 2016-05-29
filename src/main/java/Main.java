import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            EchoClient client = new EchoClient(new EchoSocket(new Socket("localhost", 1234)),
                    new EchoConsolePrinter());
            client.start();
        } catch (IOException e) {
            System.out.println("could not connect to socket");
            e.printStackTrace();
        }
    }
}
