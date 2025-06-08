package com.core.bms.dto;

import com.core.bms.model.User;
import lombok.Data;

@Data
public class SignUpResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
}
