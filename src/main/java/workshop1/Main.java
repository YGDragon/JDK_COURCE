package workshop1;

public class Main {
    public static void main(String[] args) {
        ServerSoftware server = new ServerSoftware();
        new ClientApp(server);
    }
}
