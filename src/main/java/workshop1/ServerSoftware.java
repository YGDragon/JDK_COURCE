package workshop1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ServerSoftware extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int LOCAL_X = 700;
    private static final int LOCAL_Y = 450;
    private static final String TITLE_WINDOW = "Chat server";
    private static final String MESSAGE_CONNECTION = "Подключен пользователь...";
    private static final String MESSAGE_NO_CONNECTION = "Отключен пользователь...";
    private static final String MESSAGE_ON = "Сервер запущен";
    private static final String MESSAGE_ALREADY_RUN = "Сервер уже запущен";
    private static final String MESSAGE_OFF = "Сервер остановлен";
    private static final String MESSAGE_ALREADY_OFF = "Сервер уже остановлен";
    private static final String MESSAGE_NOT_FILE = "Отсутствует лог-файл сервера!!!";
    private static final String MESSAGE_NEW_FILE = "Создан новый лог-файл сервера!!!";

    private static final String PATH_LOG = "src\\main\\java\\workshop1\\log.txt";

    ClientApp client;

    private boolean stateOn;//состояние работы сервера
    protected JButton buttonStart = new JButton("Start");
    protected JButton buttonStop = new JButton("Stop");
    protected JTextArea areaLog;

    ServerSoftware() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(LOCAL_X, LOCAL_Y, WIDTH, HEIGHT);
        setTitle(TITLE_WINDOW);
        createServerWindow();
        setResizable(false);
        setVisible(true);
    }

    public boolean isStateOn() {
        return stateOn;
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
                if (stateOn) {
                    outputToServerWindow(MESSAGE_ALREADY_RUN);
                } else {
                    stateOn = true;
                    outputToServerWindow(MESSAGE_ON);
                }
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stateOn) {
                    outputToServerWindow(MESSAGE_ALREADY_OFF);
                } else {
                    stateOn = false;
                    disconnectToClient();
                    outputToServerWindow(MESSAGE_OFF);
                }
            }
        });
        buttonsZone.add(buttonStart, BorderLayout.WEST);
        buttonsZone.add(buttonStop, BorderLayout.EAST);
        return buttonsZone;
    }

    //проверка соединения с клиентом
    protected boolean checkConnectToClient(ClientApp client) {
        if (stateOn) {
            this.client = client;
            outputToServerWindow(MESSAGE_CONNECTION + client.getClientName());
            return true;
        }
        return false;
    }

    //отключение клиента от сервера
    protected void disconnectToClient() {
        if (client != null) {
            client.disconnectToServer();
            outputToServerWindow(MESSAGE_NO_CONNECTION + client.getClientName());
        }
    }

    //вывод сообщений в окно сервера
    public void outputToServerWindow(String message) {
        areaLog.append(message + "\n");
    }

    //запись сообщений чата в лог-файл сервера
    private void writeToLogFile(String message) {
        try (FileWriter fw = new FileWriter(PATH_LOG, true)) {
            fw.write(message + "\n");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    //чтение сообщений из лог-файла сервера
    private String readFromLogFile() {
        StringBuilder messageLog = new StringBuilder();
        try (FileReader fr = new FileReader(PATH_LOG)) {
            int i = fr.read();
            while (i != -1) {
                messageLog.append(((char) i));
                i = fr.read();
            }
            return messageLog.toString();
        } catch (Exception e) {
            e.printStackTrace();
            outputToServerWindow(MESSAGE_NOT_FILE);
            new File(PATH_LOG);
            outputToServerWindow(MESSAGE_NEW_FILE);
            return null;
        }
    }

    public String getLogFromServer() {
        return readFromLogFile();
    }

    //обработка сообщений от клиента
    public void handlingMessageFromClient(String message) {
        writeToLogFile(message);
    }
}
