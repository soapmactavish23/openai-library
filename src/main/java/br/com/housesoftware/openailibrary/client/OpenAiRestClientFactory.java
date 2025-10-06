package br.com.housesoftware.openailibrary.client;

import br.com.housesoftware.corelibrary.exception.GenericException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

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
                    throw new GenericException("Erro na chamada para IA");
                }).build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(Duration.ofSeconds(15));
        factory.setConnectTimeout(Duration.ofSeconds(15));
        return factory;
    }


}
