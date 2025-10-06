package br.com.housesoftware.openailibrary.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OpenAiMessageDTO {
    private String role;
    private List<OpenAiContentDTO> content = new ArrayList<>();
}
