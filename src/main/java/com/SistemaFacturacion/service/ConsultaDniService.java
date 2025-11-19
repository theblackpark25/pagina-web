package com.SistemaFacturacion.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultaDniService {
    private final RestTemplate restTemplate;
    @Value("https://api.consultasperu.com/api/v1/query")
    private String apiUrl;
    @Value("bb22078befaa69aac1bcbac19efb3a122ba5d233a148bb041d320a4344a196c3")
    private String apiToken;


    public ConsultaDniService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> consultarDni(String token, String tipoDocumento, String numeroDocumento) {
        // Construimos el cuerpo del request (JSON)
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("type_document", tipoDocumento);
        requestBody.put("document_number", numeroDocumento);

        // Configuramos headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Unimos headers y body
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            return response.getBody();

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error del cliente: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
            throw new RuntimeException("Error de conexión con el servicio externo");
        }
    }
}
