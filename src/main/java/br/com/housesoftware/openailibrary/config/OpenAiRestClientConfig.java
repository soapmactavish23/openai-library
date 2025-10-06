package br.com.housesoftware.openailibrary.config;

import br.com.housesoftware.openailibrary.client.OpenAiClient;
import br.com.housesoftware.openailibrary.client.OpenAiRestClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class OpenAiRestClientConfig {
    @Bean
    public OpenAiClient openAiClient(OpenAiRestClientFactory factory) {
        RestClient restClient = factory.openAiRestClient();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();

        return proxyFactory.createClient(OpenAiClient.class);
    }
}
