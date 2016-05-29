import org.junit.Test;

import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class EchoClientTest {

    @Test
    public void printsMessageReceivedFromServer() {
        FakeSocket fakeSocket = new FakeSocket();
        FakeConsolePrinter fakeConsolePrinter = new FakeConsolePrinter();
        fakeSocket.input("hello");
        EchoClient echoClient = new EchoClient(new EchoSocket(fakeSocket), fakeConsolePrinter);
        echoClient.printMessageFromServer();
        assertEquals("hello", fakeConsolePrinter.printed());
    }

    @Test
    public void sendsMessageToServer() {
       FakeSocket fakeSocket = new FakeSocket();
        FakeConsolePrinter fakeConsolePrinter = new FakeConsolePrinter();
        EchoClient echoClient = new EchoClient(new EchoSocket(fakeSocket), fakeConsolePrinter);
        echoClient.sendToServer(new PrintWriter(fakeSocket.getOutputStream(), true), "hello");
        assertEquals("hello\n", fakeSocket.printedMessage());

    }
}