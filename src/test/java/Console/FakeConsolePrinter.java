package Console;

import Client.ConsolePrinter;

import java.util.LinkedList;
import java.util.List;

public class FakeConsolePrinter implements ConsolePrinter {

    private String printed;
    private final List<String> messages = new LinkedList<>();

    @Override
    public void print(String message) {
        printed = message;
        messages.add(message);
    }

    public String printed() {
        return printed;
    }

    public String printedMessages() {
        return messages.toString();
    }
}
