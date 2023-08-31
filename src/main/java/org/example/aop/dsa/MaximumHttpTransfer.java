package org.example.aop.dsa;

/**
 * @author = mbalaji on 31-08-2023
 * @project = spring-aop
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MaximumHttpTransfer {

    public static void main(String[] args) {
        List<String> strings = maximumTransfer("Bob Martin", "Bourg");
        strings.stream().forEach(System.out::println);
    }

    public static List<String> maximumTransfer(String name, String city) {

        long totalPages;

        JSONObject jsonObj = null;

        List<Float> creditList = new ArrayList<>();

        List<Float> debitList = new ArrayList<>();

        int curPage = 1;

        List<String> strList = new ArrayList<>();

        try {

            do {

                String url2 = "https://jsonmock.hackerrank.com/api/transactions?page=" + curPage;

                jsonObj = getResponseFromUrl(url2);

                totalPages = (long) jsonObj.get("total_pages");

                JSONArray dataArray = (JSONArray) jsonObj.get("data");

                for (int i = 0; i < dataArray.size(); i++) {

                    JSONObject dataObject = (JSONObject) dataArray.get(i);

                    String tempName = (String) dataObject.get("userName");

                    JSONObject location = (JSONObject) dataObject.get("location");

                    String tempCity = (String) location.get("city");

                    if (name.equals(tempName) && (city.equals(tempCity))) {

                        String txnType = (String) dataObject.get("txnType");

                        if (txnType.equals("credit")) {

                            creditList.add(NumberFormat.getCurrencyInstance(Locale.US)
                                    .parse((String) dataObject.get("amount")).floatValue());

                        } else {

                            debitList.add(NumberFormat.getCurrencyInstance(Locale.US)
                                    .parse((String) dataObject.get("amount")).floatValue());

                        }

                    }

                }

                curPage++;

            } while (curPage < totalPages);

        } catch (Exception e) {
            e.printStackTrace();
        }

        strList.add(NumberFormat.getCurrencyInstance(Locale.US).format(Collections.max(creditList)));

        strList.add(NumberFormat.getCurrencyInstance(Locale.US).format(Collections.max(debitList)));

        return strList;

    }

    static JSONObject getResponseFromUrl(String urlString) {

        JSONObject jsonObject = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                JSONParser jsonParser = new JSONParser();
                jsonObject = (JSONObject) jsonParser
                        .parse(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}

