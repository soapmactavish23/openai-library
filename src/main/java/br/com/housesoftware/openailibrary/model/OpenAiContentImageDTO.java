package br.com.housesoftware.openailibrary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiContentImageDTO extends OpenAiContentDTO{

    @JsonProperty("image_url")
    private OpenAiImageUrl imageUrl;

    @Override
    public String toString() {
        if(imageUrl == null) {
            return "OpenAiContentImageDTO [imageUrl=]";
        } else {
            return "OpenAiContentImageDTO [imageUrl=base64]";
        }
    }

}
