package br.com.housesoftware.openailibrary.client;

import br.com.housesoftware.openailibrary.model.OpenAiRequestDTO;
import br.com.housesoftware.openailibrary.model.OpenAiResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface OpenAiClient {

    @PostExchange
    OpenAiResponseDTO sendMessage(@RequestBody OpenAiRequestDTO dto);

}