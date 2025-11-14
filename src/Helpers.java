import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Helpers {
    public String hostName;
    public String defaultFort;
    public URL host;
    public HttpURLConnection httpURLConnection;
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public  Helpers()
    {

    }
    public void showInfo(){
        try{
            host = new URL("https://" + getHostName());
            ShowMsg("Giao thuc : " + host.getProtocol());
            ShowMsg("Domain : " + host.getHost());
            ShowMsg("Host IP : " +  this.getIp());
            ShowMsg("Port : " + host.getDefaultPort());
            ShowMsg("Path url  : " + host.getPath());
            ShowMsg("Status code :" + this.checkLive());


        } catch (Exception e) {
            ShowMsg("Error : " + e.getMessage());
        }
    }
    public String getIp(){
        try {
            InetAddress address = InetAddress.getByName(host.getHost());
            return address.getHostAddress();

        } catch (Exception e) {
            ShowMsg("Khong lay duoc ip");
        }
        return null;
    }
    public int checkLive(){
        int responseCode;
        try{
            host = new URL("https://" + getHostName());
            httpURLConnection = (HttpURLConnection) host.openConnection();
            httpURLConnection.setRequestMethod("GET");
            responseCode = httpURLConnection.getResponseCode();

            return responseCode;


        } catch (Exception e) {
            ShowMsg("Error : " + e.getMessage());
        }
        return 0;

    }
    public void doPost(){
        String endpoint = "https://postman-echo.com/post";
        String data = "username=admin&password=123";

        try{
            URL strUrl = new URL(endpoint);
            httpURLConnection = (HttpURLConnection) strUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            try (OutputStream os = httpURLConnection.getOutputStream()) {
                os.write(data.getBytes());
            }
            BufferedReader result = new BufferedReader(
              new InputStreamReader(httpURLConnection.getInputStream()) // get data input from server
            );
            String line;
            System.out.println("Phản hồi từ server:");
            while ((line = result.readLine()) != null) {
                System.out.println(line);
            }
            result.close();

//            ShowMsg("Ket noi thanh cong" + httpURLConnection.getResponseCode());
        } catch (Exception e) {
            ShowMsg("Error : " + e.getMessage());
        }


    }
    public void ShowMsg(String msg){
        System.out.println(msg.toUpperCase());
    }
}
