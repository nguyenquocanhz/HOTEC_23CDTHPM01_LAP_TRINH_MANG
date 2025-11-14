import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class OTPServer {
    public BufferedReader console;
    public BufferedReader in;
    public PrintWriter out;
    public OTPServer(){

    }
    public void main(){
        int port = 8080;
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("=== SERVER ĐANG CHẠY TRÊN CỔNG " + port + "===");
            System.out.println("Đang chờ Client kết nối (localhost)...");
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client ip : " + clientSocket.getInetAddress());
                this.serverListen(clientSocket);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void serverListen(Socket clientSocket){
        try(
                BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                clientSocket
        ){
            try
            {
                String input;
                while ((input = in.readLine()) != null) {
                    if (input.equalsIgnoreCase("CODE"))
                    {
                        System.out.println("Nhận từ Client: " + input);
                        int num = this.getOTP();
                        out.println("Mã OTP : " + num);
                        System.out.println("Gửi OTP: " + num);
                    }
                    else
                    {
                        out.println("Sai lệnh rồi");
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public int getOTP(){
        Random random = new Random();
        return random.nextInt(1000,9999);
    }

    public void SendNoti(PrintWriter chat, String otpToken){
        chat.println("Mã OTP : " + otpToken);
    }
}
