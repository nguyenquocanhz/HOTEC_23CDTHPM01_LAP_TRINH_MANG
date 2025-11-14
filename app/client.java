import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class client {
    void main(){
//        String host = "localhost"; // Chạy trên cùng 1 máy
//        int port = 8080;
//        try (Socket socket = new Socket(host, port)) {
//            System.out.println("=== CLIENT ĐÃ KẾT NỐI ĐẾN SERVER ===");
//            BufferedReader console = new BufferedReader(
//                    new InputStreamReader(System.in));
//            PrintWriter out = new PrintWriter(socket.getOutputStream(),
//                    true);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//            System.out.print("Nhập tên của bạn: ");
//            String name = console.readLine();
//            out.println(name);
//            String response = in.readLine();
//            System.out.println("Server trả lời: " + response);
//        } catch (IOException e) {
//            System.out.println("Không thể kết nối đến Server! (Server chưa chạy?)");
//        }
        String host = "localhost";
        int port = 8080;
        try (Socket socket = new Socket(host, port)) {
            System.out.println("=== CHAT CLIENT – ĐÃ KẾT NỐI ===");
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),
                    true);
            BufferedReader console = new BufferedReader(new
                    InputStreamReader(System.in));
            while(true){
                System.out.print("Nhập số cần kiểm tra (hoặc 'exit' để thoát):");
                String userInput = console.readLine().trim();
                // Tự động thêm lệnh CHECK
                String message = "CHECK " + userInput;
                out.println(message);
                System.out.println("Đã gửi: " + message);
                // Nhận phản hồi từ server
                String response = in.readLine();
                if (response != null) {
                    System.out.println("Server trả lời: " + response);
                } else {
                    System.out.println("Server đã ngắt kết nối.");
                    break;
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Không kết nối được Server!");
        }
    }
    void ShowMsg(String chat){
        System.out.println(chat);
    }
}
