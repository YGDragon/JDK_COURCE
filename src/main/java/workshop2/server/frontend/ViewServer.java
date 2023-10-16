package workshop2.server.frontend;

import workshop2.service.ServiceMessage;

import javax.swing.*;

public interface ViewServer extends ServiceMessage {
    JTextArea getAreaLog();
}
