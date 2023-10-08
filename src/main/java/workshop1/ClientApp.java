package workshop1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientApp extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int LOCAL_X = 700;
    private static final int LOCAL_Y = 150;
    private static final String TITLE_WINDOW = "Chat client";
    private static final String PLACEHOLDER_IP = "IP address";
    private static final String PLACEHOLDER_PORT = "Port number";
    private static final String PLACEHOLDER_NAME = "Login";
    private static final String PLACEHOLDER_PASS = "Password";
    private static final String MESSAGE_CONNECT = "***Соединение с сервером установлено***";
    private static final String MESSAGE_NO_CONNECT = "***Сервер недостепен***";
    private static final String MESSAGE_DISCONNECT = "***Соединение с сервером завершено***";

    ServerSoftware server;
    public boolean connected;//состояние подключения к серверу

    protected JButton buttonLogin = new JButton("login");
    protected JButton buttonSend = new JButton("send");

    protected JTextField fieldHostIP;
    protected JTextField fieldHostPort;
    protected JTextField fieldUserName;
    protected JPasswordField fieldPassword;
    protected JTextField fieldUserMessage;
    protected JTextArea areaLog;

    protected JPanel userLogging;

    ClientApp(ServerSoftware server) {
        this.server = server;
        setBounds(LOCAL_X, LOCAL_Y, WIDTH, HEIGHT);
        setTitle(TITLE_WINDOW);
        setResizable(false);
        createClientWindow();
        setVisible(true);
    }

    private void createClientWindow() {
        add(setLoggingZone(), BorderLayout.NORTH);
        add(setLogZone());
        add(setMessageZone(), BorderLayout.SOUTH);
    }

    private Component setLoggingZone() {
        userLogging = new JPanel();
        userLogging.setLayout(new GridLayout(2, 3));
        fieldHostIP = new JTextField(PLACEHOLDER_IP);
        fieldHostPort = new JTextField(PLACEHOLDER_PORT);
        fieldUserName = new JTextField(PLACEHOLDER_NAME);
        fieldPassword = new JPasswordField(PLACEHOLDER_PASS);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });
        userLogging.add(fieldHostIP);
        userLogging.add(fieldHostPort);
        userLogging.add(new JPanel());
        userLogging.add(fieldUserName);
        userLogging.add(fieldPassword);
        userLogging.add(buttonLogin);
        return userLogging;
    }

    private Component setLogZone() {
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        return new JScrollPane(areaLog);
    }

    private Component setMessageZone() {
        JPanel messageZone = new JPanel(new BorderLayout());
        fieldUserMessage = new JTextField();
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isStateOn() && connected) {
                    handlingMessageByClient();
                }
            }
        });
        //отслеживание нажатия клавиши Enter
        fieldUserMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handlingMessageByClient();
                }
            }
        });
        messageZone.add(fieldUserMessage);
        messageZone.add(buttonSend, BorderLayout.EAST);
        return messageZone;
    }

    public String getClientName() {
        return fieldUserName.getText();
    }

    public String getClientMessage() {
        return getClientName() + ": " + fieldUserMessage.getText();
    }

    private void setClientMessage() {
        fieldUserMessage.setText("");
    }

    //проверка соединения с сервером
    protected void connectToServer() {
        if (server.checkConnectToClient(this)) {
            userLogging.setVisible(false);
            areaLog.setText("");
            outputToClientWindow(MESSAGE_CONNECT);
            connected = true;
            String logServer = server.getLogFromServer();
            if (logServer != null) {
                outputToClientWindow(logServer);
            }
        } else {
            outputToClientWindow(MESSAGE_NO_CONNECT);
        }
    }

    //отключение клиента от сервера
    public void disconnectToServer() {
        if (!server.isStateOn()) {
            userLogging.setVisible(true);
            connected = false;
            outputToClientWindow(MESSAGE_DISCONNECT);
        }
    }

    protected void outputToClientWindow(String message) {
        areaLog.append(message + "\n");
    }

    private void handlingMessageByClient() {
        outputToClientWindow(getClientMessage());
        server.handlingMessageFromClient(getClientMessage());
        setClientMessage();
    }
}
