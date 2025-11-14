import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class OTPClient {
    public BufferedReader console;
    public BufferedReader in;
    public PrintWriter out;
    public int port = 8080;
    public String host = "localhost";
    public OTPClient(){

    }
    public void main(){

        try(Socket serverSocket = new Socket(host,port)){
            System.out.println("=== CLIENT ĐÃ KẾT NỐI ĐẾN SERVER ===");
            BufferedReader console = new BufferedReader(new

                    InputStreamReader(System.in));

            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new

                    InputStreamReader(serverSocket.getInputStream()));
            while (true){
                System.out.print("Nhập Lệnh CODE để nhận OTP:");
                String userInput = console.readLine().trim();

                if (userInput.isEmpty()) {
                    System.out.println("Vui lòng gõ lệnh !");
                    return;
                }
                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Thoát chương trình.");
                    break;
                }
                String msg = userInput;
                out.println(msg);
                System.out.println("Đã gửi lệnh : " + msg);
                String response = in.readLine();
                if (response != null) {
                    System.out.println("Server trả lời: " + response);
                } else {
                    System.out.println("Server đã ngắt kết nối.");
                    break;
                }
                System.out.println(); // Dòng trống cho đẹp

            }
        } catch (Exception e) {
            System.out.println("Không thể kết nối tới server !");
        }
    }
}
