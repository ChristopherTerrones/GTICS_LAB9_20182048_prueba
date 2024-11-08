package com.example.prueba_lab9.dao;

import com.example.prueba_lab9.entity.Meal;
import com.example.prueba_lab9.entity.MealDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class MealDao {
    public List<Meal> buscarPorNombre(String nombre) {

        List<Meal> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + nombre;

        ResponseEntity<MealDTO> responseEntity = restTemplate.getForEntity(endPoint, MealDTO.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            MealDTO body = responseEntity.getBody();
                lista = body.getMeals();
        }
        return lista;
    }

    public Meal buscarPorId(String id) {

        Meal meal = null;

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id;

        ResponseEntity<MealDTO> forEntity = restTemplate.getForEntity(url, MealDTO.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            MealDTO mealdto = forEntity.getBody();
            meal = mealdto.getMeals().get(0);
        }
        return meal;
    }
}
