package com.pumpkin.vacinados.util;

import com.pumpkin.vacinados.model.pessoa.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static ResponseEntity<Object> warningMessage(String response, HttpStatus status) {

        Map<String, Object> map = new HashMap<>();

        map.put("Atenção:", response);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> sucessMessage(String response, Pessoa pessoa , HttpStatus status) {

        Map<String, Object> map = new HashMap<>();

        map.put("Sucesso:", response);
        map.put("Dados:", pessoa);

        return new ResponseEntity<Object>(map,status);
    }
}
