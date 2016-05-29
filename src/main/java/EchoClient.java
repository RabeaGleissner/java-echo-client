import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EchoClient {

    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;
    private EchoSocket echoSocket;

    public EchoClient(EchoSocket echoSocket, ConsolePrinter consolePrinter, ConsoleReader consoleReader) {
        this.echoSocket = echoSocket;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    public void start() {
        PrintWriter serverWriter = createWriter();
        BufferedReader consoleReader = createConsoleReader();

        String userInput;
        try {
            while (!(userInput = consoleReader.readLine()).equals("#quit")) {
                sendToServer(serverWriter, userInput);
                printMessageFromServer();
            }
            disconnectClient();
        } catch (IOException e) {
            System.out.println("can't get any more input");
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

    private void disconnectClient() {
        echoSocket.close();
    }

    private String readFromServer(BufferedReader serverReader) {
        try {
            return serverReader.readLine();
        } catch (IOException e) {
            System.out.println("can't read from server");
            e.printStackTrace();
        }
        return null;
    }

    private BufferedReader createConsoleReader() {
        return new BufferedReader(new InputStreamReader(consoleReader.read()));
    }

    private BufferedReader createServerReader() {
        return new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
    }

    private PrintWriter createWriter() {
        return new PrintWriter(echoSocket.getOutputStream(), true);
    }
}
