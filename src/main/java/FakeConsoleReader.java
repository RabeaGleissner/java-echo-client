import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeConsoleReader implements ConsoleReader {
    private final List<String> messages;

    public FakeConsoleReader() {
        messages = new LinkedList<>();
    }

    public void input(String ... input) {
        messages.addAll(Arrays.asList(input));
    }

    @Override
    public InputStream read() {
        String word = messages.remove(0);
        return new ByteArrayInputStream(word.getBytes());
    }
}
