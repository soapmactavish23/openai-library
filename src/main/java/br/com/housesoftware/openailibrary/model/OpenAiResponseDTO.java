package br.com.housesoftware.openailibrary.model;

import br.com.housesoftware.corelibrary.exception.GenericException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hkprogrammer.api.core.config.Constants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiResponseDTO {
    private List<ChoiceDTO> choices;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChoiceDTO {
        private MessageDTO message;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MessageDTO {
        private String role;
        private String content;
    }

    public String getContent() {
        log.error("Response IA: {}", getChoices());
        if(!getChoices().isEmpty()) {
            return getChoices().get(0).getMessage().getContent();
        } else {
            throw new GenericException(Constants.Messages.IMAGE_PROCESS_ERROR);
        }
    }
}
