package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws IOException {

        String nome = "joão";
        URL uri = new URL("https://api.agify.io/?name="+nome);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setRequestMethod("GET");
        int resCode = conn.getResponseCode();

        if (resCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();

            String inputLine = bufferedReader.readLine();
            while ( inputLine != null) {
                stringBuffer.append(inputLine);
                inputLine = bufferedReader.readLine();
            }
            bufferedReader.close();

            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            String name = (String)jsonObject.get("name");
            int age = (int)jsonObject.get("age");

            Pessoa pessoa = new Pessoa(name, age);
            System.out.println(pessoa);


        } else {
            System.out.println("GET request not worked");
        }



    }
}