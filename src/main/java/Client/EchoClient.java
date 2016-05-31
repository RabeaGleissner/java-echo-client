package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EchoClient {

    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;
    private final EchoSocket echoSocket;

    public EchoClient(EchoSocket echoSocket, ConsolePrinter consolePrinter,
                      ConsoleReader consoleReader) {
        this.echoSocket = echoSocket;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    public void start() {
        PrintWriter serverWriter = createWriter();
        BufferedReader bufferedConsoleReader = createBufferedConsoleReader();

        String userInput = readFromStream(bufferedConsoleReader);
        if (userInput != null) {
            while (!userInput.equals("#quit")) {
                sendToServer(serverWriter, userInput);
                printMessageFromServer();
                userInput = readFromStream(bufferedConsoleReader);
            }
        }
        disconnectClient(serverWriter);
    }

    public void printMessageFromServer() {
        consolePrinter.print(readFromServer(createServerReader()));
    }

    public void sendToServer(PrintWriter serverWriter, String userInput) {
        serverWriter.println(userInput);
    }

    private String readFromStream(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void disconnectClient(PrintWriter serverWriter) {
        sendToServer(serverWriter, "stop");
        echoSocket.close();
    }

    private String readFromServer(BufferedReader serverReader) {
        return readFromStream(serverReader);
    }

    private BufferedReader createBufferedConsoleReader() {
        return new BufferedReader(new InputStreamReader(consoleReader.read()));
    }

    private BufferedReader createServerReader() {
        return new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
    }

    private PrintWriter createWriter() {
        return new PrintWriter(echoSocket.getOutputStream(), true);
    }
}
