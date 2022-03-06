package recipes.service;

import recipes.entity.Recipe;

import java.util.List;
import java.util.Map;

public interface RecipeService {

    int saveOrUpdateRecipe(Recipe recipe);
    Recipe getRecipe(int id);
    void deleteRecipe(int id);
    List<Recipe> getRecipeByCategoryOrName(Map<String, String> categoryAndName);
}
