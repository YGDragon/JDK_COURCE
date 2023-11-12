package workshop2.client;

import workshop2.client.frontend.ViewClient;
import workshop2.server.Server;

import java.io.IOException;

public class Client {

    private String name;
    public boolean connected;//состояние подключения к серверу
    ViewClient viewClient;
    Server server;

    public String getName() {
        return viewClient.getClientName();
    }

    public Client(ViewClient viewClient, Server server) {
        this.viewClient = viewClient;
        this.server = server;
        server.setClient(this);
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isServerOn() {
        return server.isStateOn();
    }

    //проверка соединения с сервером
    public void connectToServer() throws IOException {
        if (server.checkConnectToClient()) {
            viewClient.getUserLogging().setVisible(false);
            viewClient.getAreaLog().setText("");
            outputToClientWindow(viewClient.MESSAGE_CONNECT);
            connected = true;
            String logServer = server.getLogFromServer();
            if (logServer != null) {
                outputToClientWindow(logServer);
            }
        } else {
            outputToClientWindow(viewClient.MESSAGE_NO_CONNECT);
        }
    }

    //отключение клиента от сервера
    public void disconnectToServer() throws IOException {
        if (!server.isStateOn()) {
            viewClient.getUserLogging().setVisible(true);
            connected = false;
            outputToClientWindow(viewClient.MESSAGE_DISCONNECT);
        }
    }

    protected void outputToClientWindow(String message) throws IOException {
        viewClient.getAreaLog().append(message + "\n");
    }

    public void handlingMessageByClient() throws IOException {
        outputToClientWindow(viewClient.getClientMessage());
        server.handlingMessageFromClient(viewClient.getClientMessage());
        viewClient.setClientMessage();
    }
}
