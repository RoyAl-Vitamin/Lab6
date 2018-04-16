package vi.al.ro;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;

public class Config {

    private static Config config = null;

    private int serverPort;
    private String serverHost;

    private Config() {
        ClassLoader classLoader = Config.class.getClassLoader();
        try (InputStream is = new FileInputStream(new File(classLoader.getResource("config.json").getFile()));
            JsonParser parser = Json.createParser(is)) {
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.KEY_NAME) {
                    switch (parser.getString()) {
                        case "serverPort":
                            parser.next();
                            serverPort = parser.getInt();
                            break;
                        case "serverHost":
                            parser.next();
                            serverHost = parser.getString();
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

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public void reloadConfig() {
        config = null;
        config = new Config();
    }
}
