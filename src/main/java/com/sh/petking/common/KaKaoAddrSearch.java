package com.sh.petking.common;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;

public class KaKaoAddrSearch {
    static final String restApi = "758b89b906bf079dad3af90e28c940e4";
    static String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=";

    public static String AddrToLcLaAndLcLo(String addr) {
        String auth = "KakaoAK " + restApi;

        try {
            //인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
            String address = URLEncoder.encode(addr, "UTF-8");

            URL url = new URL(apiUrl + address);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //get으로 받아오면 된다. 자세한 사항은 카카오개발자센터에 나와있다.
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", restApi);
            con.setRequestProperty("content-type", "application/json");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //response 객체를 출력해보자
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addr;
    }
}
