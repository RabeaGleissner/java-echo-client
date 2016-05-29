public class EchoConsolePrinter implements ConsolePrinter {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
