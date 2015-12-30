/**
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 * <p>
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 */
package io.github.nixtabyte.telegram.jtelebot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nixtabyte.telegram.jtelebot.response.json.*;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConnectionIntegrationTest {

    // PUT YOUR TEST TOKEN HERE...
    public String token = "";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSendMessage() throws ClientProtocolException, IOException {
        String method = "sendMessage";
        String url = "https://api.telegram.org/bot" + token + "/" + method;

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("chat_id", "-7155093"));
        nvps.add(new BasicNameValuePair("text", "hi you"));
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setKeyboard(new String[][]{{"yes", "double yes"},
                {"no"}, {"robert"}});
        keyboard.setOneTimeKeyboard(true);
        keyboard.setResizeKeyboard(false);
        keyboard.setSelective(false);
        nvps.add(new BasicNameValuePair("reply_markup", objectMapper.writeValueAsString(keyboard)));
        UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        request.setEntity(uefe);

        // add request header
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response
                .getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);

            result.append(line);
        }
        TelegramResponse<Message> telegramResponse = objectMapper.readValue(
                result.toString(),
                objectMapper
                        .getTypeFactory()
                        .constructParametricType(
                                TelegramResponse.class, Message.class));

        System.out.println(telegramResponse);

    }

    @Test
    public void testUpdate() throws ClientProtocolException, IOException {
        String method = "getUpdates";
        String url = "https://api.telegram.org/bot" + token + "/" + method;

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("offset", "0"));
        nvps.add(new BasicNameValuePair("limit", "100"));
        nvps.add(new BasicNameValuePair("timeout", "0"));

        UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        request.setEntity(uefe);

        // add request header
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response
                .getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);

            result.append(line);
        }

        TelegramResponse<Update> telegramResponse = objectMapper.readValue(
                result.toString(),
                objectMapper
                        .getTypeFactory()
                        .constructParametricType(
                                TelegramResponse.class, Update.class));

        System.out.println(telegramResponse);

    }

    @Test
    public void test() throws ClientProtocolException, IOException {
        String method = "getMe";
        String url = "https://api.telegram.org/bot" + token + "/" + method;

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        // add request header
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response
                .getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);

            result.append(line);
        }
        TelegramResponse<User> telegramResponse = objectMapper.readValue(
                result.toString(),
                objectMapper
                        .getTypeFactory()
                        .constructParametricType(
                                TelegramResponse.class, User.class));

        System.out.println(telegramResponse);
    }
}
