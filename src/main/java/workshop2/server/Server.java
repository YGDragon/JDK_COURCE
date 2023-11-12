package workshop2.server;

import workshop2.client.Client;
import workshop2.server.frontend.ViewServer;
import workshop2.server.storage.RepositoryServer;

import java.io.IOException;

public class Server {
    private boolean stateOn;//состояние работы сервера
    ViewServer viewServer;
    RepositoryServer repository;

    private Client client;

    public Server(ViewServer viewServer, RepositoryServer repository) {
        this.viewServer = viewServer;
        this.repository = repository;
    }

    public boolean isStateOn() {
        return stateOn;
    }

    public void setStateOn(boolean stateOn) {
        this.stateOn = stateOn;
    }

    //проверка соединения с клиентом
    public boolean checkConnectToClient() {
        if (stateOn) {
            outputToServerWindow(viewServer.MESSAGE_CONNECTION + client.getName());
            return true;
        }
        return false;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //отключение клиента от сервера
    public void disconnectToClient() throws IOException {
        client.disconnectToServer();
        outputToServerWindow(viewServer.MESSAGE_NO_CONNECTION + client.getName());
    }

    //вывод сообщений в окно сервера
    public void outputToServerWindow(String message) {
        viewServer.getAreaLog().append(message + "\n");
    }


    public void handlingMessageFromClient(String clientMessage) {
        repository.writeToLogFile(clientMessage);
    }

    public String getLogFromServer() {
        return repository.readFromLogFile();
    }

}
