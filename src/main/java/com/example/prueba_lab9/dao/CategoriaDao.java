package com.example.prueba_lab9.dao;

import com.example.prueba_lab9.entity.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CategoriaDao {
    public List<Categoria> listar() {
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "https://www.themealdb.com/api/json/v1/1/categories.php";

        ResponseEntity<CategoryResponse> responseEntity = restTemplate.getForEntity(endPoint, CategoryResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            return responseEntity.getBody().getCategories();
        }
        return List.of(); // Retorna una lista vacía si no hay éxito
    }
}

class CategoryResponse {
    private List<Categoria> categories;

    public List<Categoria> getCategories() {
        return categories;
    }

    public void setCategories(List<Categoria> categories) {
        this.categories = categories;
    }
}
