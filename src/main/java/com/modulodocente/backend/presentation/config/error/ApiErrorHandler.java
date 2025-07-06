package com.modulodocente.backend.presentation.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class ApiErrorHandler {

    public ApiErrorResponse handle(Map<String, Object> errorAttributes, Throwable error) {
        // Obtener ruta del endpoint, si no hay, usar "/api/desconocido"
        String path = errorAttributes.getOrDefault("path", "/api/desconocido").toString();

        // Detectar el nombre de la excepción
        String errorName = error.getClass().getSimpleName().toLowerCase(Locale.ROOT);
        String message = error.getMessage() != null ? error.getMessage() : "Error inesperado";

        // Imprimir log (puedes redirigirlo a logger si lo prefieres)
        System.out.println("🔴 Error capturado: " + errorName + " - " + message);

        switch (errorName) {


            case "illegalargumentexception":
                return new ApiErrorResponse(
                    "/v1/errors/argumento-invalido",
                    "Argumento inválido",
                    HttpStatus.BAD_REQUEST,
                    message,
                    path
                );

            case "nullpointerexception":
                return new ApiErrorResponse(
                    "/v1/errors/nulo",
                    "Referencia nula",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Se encontró una referencia nula en el sistema",
                    path
                );

            case "unsupportedoperationexception":
                return new ApiErrorResponse(
                    "/v1/errors/operacion-no-soportada",
                    "Operación no soportada",
                    HttpStatus.NOT_IMPLEMENTED,
                    message,
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
