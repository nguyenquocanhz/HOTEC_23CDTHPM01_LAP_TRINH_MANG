import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server
{
    void main() {
        int port = 8080;
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//            IO.println("=== SERVER ĐANG CHẠY TRÊN CỔNG " + port +
//                    " ===");
//            IO.println("Đang chờ Client kết nối (localhost)...");
//            Socket clientSocket = serverSocket.accept();
//            IO.println("Client đã kết nối!");
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(clientSocket.getInputStream()));
//
//            PrintWriter out = new PrintWriter(
//                    clientSocket.getOutputStream(), true);
//
//            String name = in.readLine();
//            SendMsg(out,name);
//
//            clientSocket.close();
//
//            IO.println("Kết nối đã đóng.");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("=== CHAT SERVER – CỔNG " + port + " ===");
            Socket client = serverSocket.accept();
            System.out.println("Client đã tham gia chat!");
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(),
                    true);
            BufferedReader console = new BufferedReader(new
                    InputStreamReader(System.in));
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if ("bye".equalsIgnoreCase(msg)) break;
                        System.out.println("Client: " + msg);
                    }
                } catch (IOException _) {}
            }).start();

            String serverMsg;
            while ((serverMsg = console.readLine()) != null) {
                if ("bye".equalsIgnoreCase(serverMsg))
                {
                    out.println("bye");
                    break;
                }
                out.println(serverMsg);
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    void ShowMsg(String chat) {
        IO.println(chat);
    }

    void SendMsg(PrintWriter out, String name) {
        out.println("Xin chào, " + name + "!");
    }

}
