package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.entity.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class RecipeController {

    RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe/new")
    public Map<String, Integer> addRecipe(@Valid @RequestBody Recipe recipe, BindingResult bindingResult) {
        return Map.of("id", recipeService.addRecipe(recipe));
    }

    @GetMapping("/recipe/{id}")
    public Recipe addRecipe(@PathVariable("id") int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("/recipe/search")
    public List<Recipe> getSortedRecipes(@RequestParam @Size(min = 1, max = 1, message = "You mustn't pass 0 or more than 1 parameters!") Map<String,
                                         @NotBlank(message = "Value of parameter should be not blank!") String> categoryAndName) {
        return recipeService.getRecipeByCategoryOrName(categoryAndName);
    }

    @DeleteMapping("/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable("id") int id) {
        recipeService.deleteRecipe(id);
    }

    @PutMapping("/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeRecipe(@PathVariable("id") int id, @RequestBody @Valid Recipe recipe, BindingResult bindingResult) {
        recipeService.saveOrUpdateRecipe(recipe);
    }


}
