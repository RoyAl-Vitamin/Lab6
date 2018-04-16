package vi.al.ro;

import org.apache.commons.cli.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int THREAD_COUNT = 2;

    public static void main(String... args) {

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(createOptions(), args);
            Config config = Config.getInstance();

            if (line.hasOption("p")) {
                String sPort = line.getOptionValue("p");
                int port = Integer.parseInt(sPort);
                config.setServerPort(port);
            }
            if (line.hasOption("h")) {
                String host = line.getOptionValue("h");
                config.setServerHost(host);
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
        options.addOption("p","port",true,"use a third-party server port");
        options.addOption("h","host",true,"use a third-party server hostname");
        return options;
    }
}
