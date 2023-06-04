package com.example.projekt_studia_java.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KategoriaValidator implements
        ConstraintValidator<KategoriaValidation, String> {

    public void initialize(KategoriaValidator categoryValidator) {
    }


    RestTemplate connector = new RestTemplate();
    String url="http://localhost:8090/kategorie";
    @Override
    public boolean isValid(String kategoria, ConstraintValidatorContext cxt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<String, String>();
        map.add("kategoria",kategoria);
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String,String>>(map,headers);
        try {
            ResponseEntity<String> response = connector.postForEntity(url,request,String.class);
        }
        catch(Exception e){
            return false;
        }
        return true;


    }

}