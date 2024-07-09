package fr.diginamic.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAttributes);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("status", HttpStatus.NOT_FOUND.value());
        errorAttributes.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        errorAttributes.put("message", "Page not found");
        errorAttributes.put("path", request.getRequestURI());
        return errorAttributes;
    }

    public String getErrorPath() {
        return "/error";
    }
}
