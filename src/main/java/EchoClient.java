import java.io.PrintWriter;

public class EchoClient {

    private EchoSocket echoSocket;

    public EchoClient(EchoSocket echoSocket) {
        this.echoSocket = echoSocket;
    }

    PrintWriter out = createWriter();

    public PrintWriter createWriter() {
        return new PrintWriter(echoSocket.getOutputStream(), true);
    }
}
