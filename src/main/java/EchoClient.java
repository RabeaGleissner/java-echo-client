import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EchoClient {

    private final ConsolePrinter consolePrinter;
    private EchoSocket echoSocket;

    public EchoClient(EchoSocket echoSocket, ConsolePrinter consolePrinter) {
        this.echoSocket = echoSocket;
        this.consolePrinter = consolePrinter;
    }

    public void start() {
        PrintWriter serverWriter = createWriter();
        BufferedReader consoleReader = createConsoleReader();

        String userInput;
        try {
            while ((userInput = consoleReader.readLine()) != null) {
                sendToServer(serverWriter, userInput);
                printMessageFromServer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessageFromServer() {
        String message = readFromServer(createServerReader());
        consolePrinter.print(message);
    }

    public void sendToServer(PrintWriter serverWriter, String userInput) {
        serverWriter.println(userInput);
    }

    private String readFromServer(BufferedReader serverReader) {
        try {
            return serverReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedReader createConsoleReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private BufferedReader createServerReader() {
        return new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
    }

    private PrintWriter createWriter() {
        return new PrintWriter(echoSocket.getOutputStream(), true);
    }
}
