package vi.al.ro;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;

public class Config {

    private static Config config = null;

    private int serverPort; // Порт, где сидит сокет сервера
    private String clientHost; // Хост, куда коннектится клиент
    private int clientPort; // Порт, куда коннектится клиент

    private Config() {
        ClassLoader classLoader = Config.class.getClassLoader();
        try (InputStream is = new FileInputStream(new File(classLoader.getResource("config.json").getFile()));
            JsonParser parser = Json.createParser(is)) {
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.KEY_NAME) {
                    switch (parser.getString()) {
                        case "server_port":
                            parser.next();
                            serverPort = parser.getInt();
                            break;
                        case "client_host":
                            parser.next();
                            clientHost = parser.getString();
                            break;
                        case "client_port":
                            parser.next();
                            clientPort = parser.getInt();
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (config == null)
            config = new Config();
        return config;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public void reloadConfig() {
        config = null;
        config = new Config();
    }
}
