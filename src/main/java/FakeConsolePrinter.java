public class FakeConsolePrinter implements ConsolePrinter {

    private String printed;

    @Override
    public void print(String message) {
        printed = message;
    }

    public String printed() {
        return printed;
    }
}
