package workshop2.client.frontend;

import workshop2.service.ServiceMessage;

import javax.swing.*;
import java.awt.*;

public interface ViewClient extends ServiceMessage {
    String getClientName();

    void setClientMessage();

    String getClientMessage();

    JTextArea getAreaLog();

    Component getUserLogging();
}
