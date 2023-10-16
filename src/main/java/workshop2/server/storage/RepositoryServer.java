package workshop2.server.storage;

public interface RepositoryServer {

    void writeToLogFile(String message);

    String readFromLogFile();
}
