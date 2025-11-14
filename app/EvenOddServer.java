import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EvenOddServer {
    public EvenOddServer()
    {

    }

    void main() {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("=== SERVER ĐANG CHẠY TRÊN CỔNG " + port + "===");
            System.out.println("Đang chờ Client kết nối (localhost)...");
            while (true) { // Chấp nhận nhiều client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client đã kết nối từ: " +
                clientSocket.getInetAddress());

                serverListen(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    void serverListen(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                clientSocket // tự đóng
        ) {
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Nhận từ Client: " + input);
                if (input.startsWith("CHECK ")) {
                    String numStr = input.split(" ")[1].trim();
                    try {
                        int num = Integer.parseInt(numStr);
                        String result = (num % 2 == 0)
                                ? num + " là số chẵn"
                                : num + " là số lẻ";
                        out.println(result);
                        System.out.println("Gửi: " + result);
                    } catch (NumberFormatException e) {
                        out.println("Lỗi: '" + numStr + "' không phải số nguyên hợp lệ");

                    }
                } else {
                    out.println("Lệnh không hợp lệ. Dùng: CHECK <số>");
                }
            }
        } catch (IOException e) {
            System.out.println("Client ngắt kết nối.");
        } finally {
            System.out.println("Kết nối với Client đã đóng.\n");
        }
    }

}
