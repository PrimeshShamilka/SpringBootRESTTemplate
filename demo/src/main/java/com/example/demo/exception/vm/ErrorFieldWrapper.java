package com.example.demo.exception.vm;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ErrorFieldWrapper {
    private String requestId;
    private List<ErrorField> error;
}
