package com.modulodocente.backend.presentation.config.error;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

    private String type;     // URI que identifica el tipo de error
    private String title;    // Título corto del error
    private int status;      // Código HTTP
    private String detail;   // Mensaje detallado
    private String instance; // Ruta del endpoint que causó el error

    public ApiErrorResponse() {}

    public ApiErrorResponse(String type, String title, HttpStatus status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status.value();
        this.detail = detail;
        this.instance = instance;
    }

    // Getters y Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}

