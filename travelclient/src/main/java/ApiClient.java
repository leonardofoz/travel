import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

/**
 * Main method to execute the API client.
 *
 * @author leonardofoz
 * @since 0.1.0
 */
public class ApiClient {

    private static final String API_URL = "http://localhost:8080/";
    private static final String USERNAME = "someuser";
    private static final String PASSWORD = "psw";

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        try {
            String locationURL = API_URL + "travel/locations/country/US";
            String response = apiClient.sendGetRequest(locationURL);
            System.out.println(response);
        } catch (IOException e) {
            System.err.println("An error occurred while sending the request: " + e.getMessage());
        }
    }

    private String sendGetRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        String credentials = USERNAME + ":" + PASSWORD;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);

        int responseCode = connection.getResponseCode();
        String response;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            response = convertStreamToString(inputStream);
        } else {
            throw new IOException("The request failed with a response code: " + responseCode);
        }

        connection.disconnect();
        return response;
    }

    private String convertStreamToString(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
