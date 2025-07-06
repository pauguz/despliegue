package com.modulodocente.backend.presentation.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class ApiErrorHandler {
  
  public ApiErrorResponse handle(Map<String, Object> errorAttributes, Throwable error) {
    String path = (String) errorAttributes.getOrDefault("path", "/api/desconocido");

    String errorName = error.getClass().getSimpleName().toLowerCase(Locale.ROOT);

    switch (errorName) {


      case "illegalargumentexception":
        return new ApiErrorResponse(
            "/v1/errors/argumento-invalido",
            "Argumento inválido",
            HttpStatus.BAD_REQUEST,
            error.getMessage(),
            path
        );

      case "unsupportedoperationexception":
        return new ApiErrorResponse(
            "/v1/errors/operacion-no-soportada",
            "Operación no soportada",
            HttpStatus.NOT_IMPLEMENTED,
            error.getMessage(),
            path
        );

      case "runtimeexception":
        return new ApiErrorResponse(
            "/v1/errors/runtime",
            "Error en ejecución",
            HttpStatus.INTERNAL_SERVER_ERROR,
            error.getMessage(),
            path
        );

      default:
        return new ApiErrorResponse(
            "/v1/errors/desconocido",
            "Error inesperado",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Ocurrió un error inesperado, contacte al administrador",
            path
        );
    }
  }
}
