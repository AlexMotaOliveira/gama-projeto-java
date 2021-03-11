package com.gama.exception;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@EnableWebMvc
@Configuration
@ControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler  {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        var bodies = new ArrayList<ResponseErrors.Body>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            bodies.add(new ResponseErrors.Body(nome, mensagem));
        }

        var errors = new ResponseErrors();
        errors.setStatus(status.value());
        errors.setMessage("Campos Inválidos");
        errors.setData(OffsetDateTime.now());
        errors.setBodys(bodies);

        return super.handleExceptionInternal(ex, errors, headers, status, request);
    }


    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, URI> responseBody = new HashMap<>();
        responseBody.put("Acesse o link com os endpoints disponíveis: ", URI.create("http://localhost:8080/swagger-ui.html"));
        responseBody.put("Parece que o endpoint que você busca está em outro castelo!", new URI("") );
        return new ResponseEntity<Object>(responseBody,HttpStatus.NOT_FOUND);
    }

}
