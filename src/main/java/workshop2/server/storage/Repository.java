package workshop2.server.storage;

import workshop2.service.ServiceMessage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Repository implements RepositoryServer, ServiceMessage {
    private static final String PATH_LOG = "src\\main\\java\\workshop2\\server\\storage\\log.txt";

    public void writeToLogFile(String message) {
        try (FileWriter fw = new FileWriter(PATH_LOG, true)) {
            fw.write(message + "\n");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    //чтение сообщений из лог-файла сервера
    public String readFromLogFile() {
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
            new File(PATH_LOG);
            return null;
        }
    }
}
