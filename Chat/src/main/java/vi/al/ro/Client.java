package vi.al.ro;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {

    // порт сервера
    private final int SERVER_PORT;
    // адрес сервера
    private final String SERVER_ADDRESS;

    public Client() {
        Config config = Config.getInstance();
        SERVER_ADDRESS = config.getServerHost();
        SERVER_PORT = config.getServerPort();
    }

    public void run() {

        try {
            InetAddress ipServerAddress = InetAddress.getByName(SERVER_ADDRESS); // создаем объект который отображает вышеописанный IP-адрес.
            Socket socket = new Socket(ipServerAddress, SERVER_PORT); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Any of you heard of a socket with IP address " + SERVER_ADDRESS + " and port " + SERVER_PORT + "?");
            System.out.println("Yes! I just got hold of the program.");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
            System.out.println();

            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                System.out.println("Sending this line to the server...");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("The server was very polite. It sent me this : " + line);
                System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
