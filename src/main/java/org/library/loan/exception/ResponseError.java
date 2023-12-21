package org.library.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseError {

    private int status;
    private String message;
    private String detail;
}
