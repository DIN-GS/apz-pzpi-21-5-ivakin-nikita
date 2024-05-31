package org.example;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class IoT {

    protected static String serverUrlSend = "";
    protected static String serverUrlAuth = "";
    private static String info;
    private static String token;

    protected static int id = 0;
    protected static String login = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get("src", "config.txt").toString()))){
            IoT.serverUrlSend = reader.readLine();
            IoT.serverUrlAuth = reader.readLine();
            IoT.id  = Integer.parseInt(reader.readLine());
            IoT.login = reader.readLine();
        } catch (IOException e) {
            System.out.println("File with url config doesn't exist update IoT! For this call tech support.");
            return;
        }
        if (!(serverUrlSend.equals("")) && id != 0 && !(login.equals(""))) {
            System.out.println("Device is configured and ready for work.");
            System.out.print("Enter password for activation: ");
            String password = scanner.nextLine();

            // Authenticate and get the token
            if (authenticate(login, password)) {
                System.out.println("Authentication successful. Token: " + token);

                while (true) {
                    System.out.print("Waiting for Supply Car ID: ");
                    int carId = scanner.nextInt();

                    System.out.println("Scanning successful. Sending data to server...");
                    System.out.println(sendScanData(carId, id));

                }
            } else {
                System.out.println("Authentication failed.");
            }
        } else {
            System.out.println("Device isn't configured ask for technical support.");
        }
    }

    private static boolean authenticate(String email, String password) {
        try {
            URL url = new URL(serverUrlAuth);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = new JSONObject()
                    .put("email", email)
                    .put("password", password)
                    .toString();

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
            System.out.println("Authentication Response Code: " + code);

            if (code == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                token = jsonResponse.getString("access_token");
                return true;
            } else {
                System.out.println("Authentication failed with response code: " + code);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private static String sendScanData(int carId, int scannerId) {
        String responseString = "";
        try {
            String endpointUrl = IoT.serverUrlSend;

            URL url = new URL(endpointUrl + "?supplyCarId=" + carId + "&scanningDeviceId=" + scannerId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setDoOutput(true);

            String jsonInputString = new JSONObject().put("id", carId).toString();

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
            System.out.println("Response Code: " + code);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responseString = response.toString();
            }

        } catch (Exception e) {
            System.out.println("Something went wrong in sending data maybe scanner id was wrong call tech support and stop the car.");;
        }
        return responseString;
    }
}
