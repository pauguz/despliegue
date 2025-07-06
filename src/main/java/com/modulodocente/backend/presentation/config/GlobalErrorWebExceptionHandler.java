package com.modulodocente.backend.presentation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;

import com.modulodocente.backend.presentation.config.error.ApiErrorHandler;
import com.modulodocente.backend.presentation.config.error.ApiErrorResponse;

import reactor.core.publisher.Mono;
import java.util.Map;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    private ApiErrorHandler apiErrorHandler;

    public GlobalErrorWebExceptionHandler(DefaultErrorAttributes errorAttributes,
                                          ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Autowired
    public void setApiErrorHandler(ApiErrorHandler apiErrorHandler) {
        this.apiErrorHandler = apiErrorHandler;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        // Obtener atributos del error
        Map<String, Object> errorPropertiesMap = getErrorAttributes(
            request, 
            org.springframework.boot.web.error.ErrorAttributeOptions.of(
                org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE
            )
        );

        // Usar ApiErrorHandler para mapear
        Throwable throwable = getError(request);
        ApiErrorResponse response = apiErrorHandler.handle(errorPropertiesMap, throwable);

        // Devolver respuesta JSON con status adecuado
        return ServerResponse
            .status(response.getStatus())
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(response));
    }
}
