package org.springboot.restapi.dto;

//import jakarta.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springboot.restapi.models.User;

@Getter
@Setter
public class CommentDTO {
    @Nullable
    private Long id;
    @NotBlank(message = "Content cannot be blank")
    @Size(max = 500, message = "Content cannot exceed 500 characters")
    private String content;

    @Nullable
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;



}
