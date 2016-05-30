import org.junit.Test;

import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoClientTest {

    @Test
    public void printsMessageReceivedFromServer() {
        FakeSocket fakeSocket = new FakeSocket();
        FakeConsolePrinter fakeConsolePrinter = new FakeConsolePrinter();
        fakeSocket.input("hello");
        EchoClient echoClient = new EchoClient(new EchoSocket(fakeSocket), fakeConsolePrinter,
                new FakeConsoleReader());

        echoClient.printMessageFromServer();

        assertEquals("hello", fakeConsolePrinter.printed());
    }

    @Test
    public void sendsMessageToServer() {
        FakeSocket fakeSocket = new FakeSocket();
        FakeConsolePrinter fakeConsolePrinter = new FakeConsolePrinter();
        EchoClient echoClient = new EchoClient(new EchoSocket(fakeSocket),
                fakeConsolePrinter, new FakeConsoleReader());

        echoClient.sendToServer(new PrintWriter(fakeSocket.getOutputStream(), true), "hello");

        assertEquals("hello\n", fakeSocket.printedMessage());
    }

    @Test
    public void disconnectsClientWhenUserQuits() {
        FakeSocket fakeSocket = new FakeSocket();
        FakeConsoleReader fakeConsoleReader = new FakeConsoleReader();
        fakeConsoleReader.input("#quit");
        fakeSocket.input("#quit");
        EchoClient echoClient = new EchoClient(new EchoSocket(fakeSocket),
                new FakeConsolePrinter(), fakeConsoleReader);

        echoClient.start();

        assertTrue(fakeSocket.closed);
    }

    @Test
    public void printsUserInputUntilUserQuits() {
        FakeSocket fakeSocket = new FakeSocket();
        FakeConsoleReader fakeConsoleReader = new FakeConsoleReader();
        fakeConsoleReader.input("hey\n#quit");
        fakeSocket.input("hey\n#quit");
        EchoClient echoClient = new EchoClient(new EchoSocket(fakeSocket),
                new FakeConsolePrinter(), fakeConsoleReader);

        echoClient.start();

        assertTrue(fakeSocket.closed);
    }
}