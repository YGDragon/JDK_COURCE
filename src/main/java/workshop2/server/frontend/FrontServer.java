package workshop2.server.frontend;

import workshop2.server.Server;
import workshop2.server.storage.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FrontServer extends JFrame implements ViewServer{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int LOCAL_X = 700;
    private static final int LOCAL_Y = 450;
    private static final String TITLE_WINDOW = "Chat server";
    protected JButton buttonStart = new JButton("Start");
    protected JButton buttonStop = new JButton("Stop");
    private JTextArea areaLog;

    private Server server;

    public FrontServer() {
        this.server = new Server(this, new Repository());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(LOCAL_X, LOCAL_Y, WIDTH, HEIGHT);
        setTitle(TITLE_WINDOW);
        createServerWindow();
        setResizable(false);
        setVisible(true);
    }

    private void createServerWindow() {
        add(getLogZone());
        add(getButtonsZone(), BorderLayout.SOUTH);
    }

    private Component getLogZone() {
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        return new JScrollPane(areaLog);
    }

    private Component getButtonsZone() {
        JPanel buttonsZone = new JPanel(new GridLayout(1, 2));
        buttonStart = new JButton("Start");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isStateOn()) {
                    server.outputToServerWindow(MESSAGE_ALREADY_RUN);
                } else {
                    server.setStateOn(true);
                    server.outputToServerWindow(MESSAGE_ON);
                }
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!server.isStateOn()) {
                    server.outputToServerWindow(MESSAGE_ALREADY_OFF);
                } else {
                    server.setStateOn(false);
                    try {
                        server.disconnectToClient();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    server.outputToServerWindow(MESSAGE_OFF);
                }
            }
        });
        buttonsZone.add(buttonStart, BorderLayout.WEST);
        buttonsZone.add(buttonStop, BorderLayout.EAST);
        return buttonsZone;
    }

    public Server getServer(){
        return server;
    }

    public JTextArea getAreaLog() {
        return areaLog;
    }
}
