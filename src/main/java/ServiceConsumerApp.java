import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

public class ServiceConsumerApp {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String key = "country" + i;
            String value = "capital" + i;
            sendRequest(key, value);
        }
    }

    private static void sendRequest(String country, String capital) {
        URL servletURL;
        try {
            servletURL = new URL("http://localhost:8080/storeInfo");
            HttpURLConnection servletConnection = (HttpURLConnection) servletURL.openConnection();
            servletConnection.setRequestMethod("POST");
            servletConnection.setRequestProperty("User-Agent", "ServiceConsumer");
            servletConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            servletConnection.setDoOutput(true);
            String urlParameters = "country=" + country + "&capital=" + capital;
            DataOutputStream wr = new DataOutputStream(servletConnection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = servletConnection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + servletURL);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            ObjectInputStream in = new ObjectInputStream(servletConnection.getInputStream());
            Map<String, String> text = (TreeMap<String, String>) in.readObject();

            in.close();

            //print result
            System.out.println(text.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
