package br.com.housesoftware.openailibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
public class OpenAiRequestDTO {
    private String model;
    private List<OpenAiMessageDTO> messages = new ArrayList<>();

    public void dtoForImage(String question, MultipartFile image) throws IOException {
        this.setModel("gpt-4o");

        OpenAiMessageDTO message = new OpenAiMessageDTO();
        message.setRole("user");

        OpenAiContentTextDTO text = new OpenAiContentTextDTO();
        text.setType("text");
        text.setText(question);

        OpenAiContentImageDTO imageContent = new OpenAiContentImageDTO();
        imageContent.setType("image_url");
        OpenAiImageUrl imageUrl = new OpenAiImageUrl();
        imageUrl.setUrl("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image.getBytes()));
        imageContent.setImageUrl(imageUrl);

        message.setContent(List.of(text, imageContent));
        this.setMessages(List.of(message));
    }

}
