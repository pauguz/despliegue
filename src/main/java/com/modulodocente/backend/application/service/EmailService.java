package com.modulodocente.backend.application.service;

import com.modulodocente.backend.dto.NotificacionRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public Mono<String> enviarCorreo(NotificacionRequestDTO request, List<FilePart> archivos) {
        return Flux.fromIterable(archivos)
                .flatMap(filePart -> filePart.content().reduce(new ByteArrayOutputStream(), (baos, dataBuffer) -> {
                    try {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        baos.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return baos;
                }).map(baos -> new ArchivoAdjunto(filePart.filename(), baos.toByteArray())))
                .collectList()
                .flatMap(listaAdjuntos -> Mono.fromCallable(() -> {
                    try {
                        MimeMessage mensaje = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

                        helper.setTo(request.getCorreos().toArray(new String[0]));
                        helper.setSubject(request.getTitulo());
                        helper.setText(request.getMensaje(), true);

                        for (ArchivoAdjunto adjunto : listaAdjuntos) {
                            InputStreamSource fuente = new ByteArrayResource(adjunto.getContenido());
                            helper.addAttachment(adjunto.getNombreArchivo(), fuente);
                        }

                        mailSender.send(mensaje);
                        return "Correo enviado con éxito.";

                    } catch (MessagingException e) {
                        e.printStackTrace();
                        return "Error al enviar el correo: " + e.getMessage();
                    }
                }));
    }

    private static class ArchivoAdjunto {
        private final String nombreArchivo;
        private final byte[] contenido;

        public ArchivoAdjunto(String nombreArchivo, byte[] contenido) {
            this.nombreArchivo = nombreArchivo;
            this.contenido = contenido;
        }

        public String getNombreArchivo() {
            return nombreArchivo;
        }

        public byte[] getContenido() {
            return contenido;
        }
    }
}
