package workshop2;

import workshop2.client.frontend.FrontClient;
import workshop2.server.frontend.FrontServer;

public class Main {
    public static void main(String[] args) {
        FrontServer frontServer = new FrontServer();
        new FrontClient(frontServer);
    }
}
