package com.spring.security.config.security.handler.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.util.ResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.spring.security.exception.member.MemberErrorMessage.CHECK_EMAIL_OR_PSWD;


@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Invalid Username or Password";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid Username or Password";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "User not exists";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "Expired password";
        }
        logErrorDetails(exception, request.getRequestURI());
        handleFailureResponse(response , errorMessage);
    }

    private void logErrorDetails(AuthenticationException accessDeniedException, String requestUri) {
        log.error("Not Authorized Request in AuthenticationFailureHandler ", accessDeniedException);
        log.error("Request Uri: {}", requestUri);
    }

    private void handleFailureResponse(HttpServletResponse response, String errorMsg) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(ResponseDTO.getFailResult(errorMsg)));
    }
}
