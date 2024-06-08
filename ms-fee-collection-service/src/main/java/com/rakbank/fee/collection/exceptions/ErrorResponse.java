package com.rakbank.fee.collection.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	@Schema(description = "Error message associated with the response", example = "Key must not be blank")
	private String errorMessage;

	@Schema(description = "Type of error", example = "TECHNICAL, VALIDATION or FUNCTIONAL ")
	private String errorType;

	@Schema(description = "Error request ID", example = "Unique request timestamp")
	private String errorRequestId;

	@Override
	public String toString() {
		return "ErrorResponse [errorMessage=" + errorMessage + ", errorType=" + errorType + ", erroRequestId="
				+ errorRequestId + "]";
	}
}