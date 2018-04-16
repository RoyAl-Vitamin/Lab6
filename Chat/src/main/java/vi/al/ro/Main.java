package vi.al.ro;

import org.apache.commons.cli.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int THREAD_COUNT = 2;


    // Server.java - port for Server
    // Client.java - IP and port for Client
    public static void main(String... args) {

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(createOptions(), args);
            Config config = Config.getInstance();

            if (line.hasOption("P")) {
                String sPort = line.getOptionValue("p");
                int port = Integer.parseInt(sPort);
                config.setServerPort(port);
            }
            if (line.hasOption("p")) {
                String sPort = line.getOptionValue("p");
                int port = Integer.parseInt(sPort);
                config.setClientPort(port);
            }
            if (line.hasOption("h")) {
                String host = line.getOptionValue("h");
                config.setClientHost(host);
            }
        } catch(ParseException | NumberFormatException exp) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            System.err.println( "Using default port");
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

        threadPool.submit(new Server());
        threadPool.submit(new Client());
    }

    /**
     * Добавление CLI аргументов
     */
    private static Options createOptions() {
        Options options = new Options();
        options.addOption("P","server-port",true,"Порт, где сидит сокет сервера");
        options.addOption("p","client-port",true,"Порт, куда коннектится клиент");
        options.addOption("h","client-host",true,"Хост, куда коннектится клиент");
        return options;
    }
}
