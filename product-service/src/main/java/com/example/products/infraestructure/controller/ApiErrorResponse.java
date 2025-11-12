package com.example.products.infraestructure.controller;

import java.time.LocalDateTime;
import lombok.Data;

/** The type Api error response. */
@Data
public class ApiErrorResponse {

  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String message;

  /**
   * Instantiates a new Api error response.
   *
   * @param timestamp the timestamp
   * @param status the status
   * @param error the error
   * @param message the message
   */
  public ApiErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
  }
}
