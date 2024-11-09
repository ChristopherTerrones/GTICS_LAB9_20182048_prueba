package com.example.prueba_lab9.controller;

import com.example.prueba_lab9.dao.CategoriaDao;
import com.example.prueba_lab9.dao.MealDao;
import com.example.prueba_lab9.entity.Favorite;
import com.example.prueba_lab9.entity.Meal;
import com.example.prueba_lab9.repository.FavoriteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/telemeal")
public class TelemealController {
    final CategoriaDao categoriaDao;
    final MealDao mealDao;
    final FavoriteRepository favoriteRepository;

    public TelemealController(CategoriaDao categoriaDao, MealDao mealDao, FavoriteRepository favoriteRepository) {
        this.categoriaDao = categoriaDao;
        this.mealDao = mealDao;
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping({"/list", "", "/"})
    public String listarCategorias(Model model) {
        model.addAttribute("listaCategorias", categoriaDao.listar());
        return "categoria/list";
    }
    @GetMapping("/search")
    public String buscarComidas(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("listaComidas", mealDao.buscarPorNombre(nombre));
        return "categoria/meal/list";
    }

    @GetMapping("/detail")
    public String detalleComida(@RequestParam("id") String id, Model model) {
        Meal meal = mealDao.buscarPorId(id);
        boolean isFavorite = favoriteRepository.existsByMealId(Integer.valueOf(meal.getIdMeal()));
        model.addAttribute("detalleComida", meal);
        model.addAttribute("isFavorite", isFavorite);
        return "categoria/meal/detail";
    }
    @PostMapping("/addFavorite")
    public String agregarAFavoritos(@RequestParam("id") String id, Model model) {

        Meal meal = mealDao.buscarPorId(id);
        if (!favoriteRepository.existsByMealId(Integer.valueOf(meal.getIdMeal()))) {
            Favorite favorite = new Favorite();
            favorite.setMealId(Integer.valueOf(meal.getIdMeal()));
            favorite.setMealName(meal.getStrMeal());
            favorite.setMealCategory(meal.getStrCategory());
            favorite.setMealImage(meal.getStrMealThumb());
            favoriteRepository.save(favorite);
            model.addAttribute("isFavorite", true);
            model.addAttribute("message", "Congratulations! You added this meal to favorites.");
        } else {
            model.addAttribute("isFavorite", true);
        }
        model.addAttribute("detalleComida", meal);
        return "categoria/meal/detail";
    }

}
