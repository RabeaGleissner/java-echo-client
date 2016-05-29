import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoSocket {

    private Socket socket;

    public EchoSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() {
        try {
            return socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("could not get output stream");
            e.printStackTrace();
        }
        return null;
    }

    public InputStream getInputStream() {
        try {
            return socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("could not close socket");
            e.printStackTrace();
        }
    }
}
