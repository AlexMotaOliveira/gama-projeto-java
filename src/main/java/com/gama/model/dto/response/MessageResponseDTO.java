package com.gama.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private String message;

    public static MessageResponseDTO createMessageResponse(String s) {
        return MessageResponseDTO
                .builder()
                .message(s)
                .build();
    }
}
