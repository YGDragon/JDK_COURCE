package workshop2.client.frontend;

import workshop2.client.Client;
import workshop2.server.frontend.FrontServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class FrontClient extends JFrame implements ViewClient {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int LOCAL_X = 700;
    private static final int LOCAL_Y = 150;
    private static final String TITLE_WINDOW = "Chat client";
    private static final String PLACEHOLDER_IP = "IP address";
    private static final String PLACEHOLDER_PORT = "Port number";
    private static final String PLACEHOLDER_NAME = "Login";
    private static final String PLACEHOLDER_PASS = "Password";
    protected JButton buttonLogin = new JButton("login");
    protected JButton buttonSend = new JButton("send");
    protected JTextField fieldHostIP;
    protected JTextField fieldHostPort;
    protected JTextField fieldUserName;
    protected JPasswordField fieldPassword;
    protected JTextField fieldUserMessage;
    protected JTextArea areaLog;
    protected JPanel userLogging;

    private Client client;

    public FrontClient(FrontServer frontServer) {
        client = new Client(this, frontServer.getServer());
        setBounds(LOCAL_X, LOCAL_Y, WIDTH, HEIGHT);
        setTitle(TITLE_WINDOW);
        setResizable(false);
        createClientWindow();
        setVisible(true);
    }

    public JPanel getUserLogging() {
        return userLogging;
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
                try {
                    client.connectToServer();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
                if (client.isServerOn() && client.isConnected()) {
                    try {
                        client.handlingMessageByClient();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        //отслеживание нажатия клавиши Enter
        fieldUserMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        client.handlingMessageByClient();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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

    public void setClientMessage() {
        fieldUserMessage.setText("");
    }

    public JTextArea getAreaLog() {
        return areaLog;
    }
}
