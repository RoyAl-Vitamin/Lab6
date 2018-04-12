package vi.al.ro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int THREAD_COUNT = 2;

    public static void main(String... args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

        threadPool.submit(new Server());
        threadPool.submit(new Client());

    }
}
