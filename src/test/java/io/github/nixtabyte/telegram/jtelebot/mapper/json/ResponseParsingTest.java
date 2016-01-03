package io.github.nixtabyte.telegram.jtelebot.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nixtabyte.telegram.jtelebot.response.json.TelegramResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResponseParsingTest {
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testSomething() throws Exception {
        String reply = "{\"ok\":true,\"result\":{\"message_id\":9,\"from\":{\"id\":128891170,\"first_name\":\"LoggerBot\",\"username\":\"LoggerBot\"},\"chat\":{\"id\":116644997,\"first_name\":\"Aldrin\",\"last_name\":\"Leal\",\"username\":\"aldrinleal\",\"type\":\"private\"},\"date\":1451822466,\"text\":\"hi you\"}}";

        TelegramResponse<?> response = objectMapper.readValue(reply, TelegramResponse.class);

        assertThat(response, is(not(nullValue())));

    }
}
