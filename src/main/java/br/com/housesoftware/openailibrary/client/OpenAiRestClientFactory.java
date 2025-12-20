package br.com.housesoftware.openailibrary.client;

import br.com.housesoftware.corelibrary.exception.GenericException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiRestClientFactory {

    private final RestClient.Builder builder;

    @Value("${web.openai-url}")
    private String urlOpenAi;

    @Value("${web.openai-key}")
    private String openAiKey;

    public RestClient openAiRestClient() {
        return builder.baseUrl(urlOpenAi)
                .defaultHeader("Authorization", "Bearer " + openAiKey)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .requestFactory(generateClientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    String body = "";
                    try {
                        body = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
                    } catch (Exception ignored) {}

                    log.error("OpenAI error: status={} uri={} body={}",
                            response.getStatusCode().value(),
                            request.getURI(),
                            body
                    );

                    throw new GenericException(
                            "Erro na chamada para IA. Status: " + response.getStatusCode().value()
                    );
                }).build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(Duration.ofSeconds(15));
        factory.setConnectTimeout(Duration.ofSeconds(15));
        return factory;
    }


}
