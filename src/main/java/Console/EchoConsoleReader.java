package Console;

import Client.ConsoleReader;

import java.io.InputStream;

public class EchoConsoleReader implements ConsoleReader {

    @Override
    public InputStream read() {
        return System.in;
    }
}
