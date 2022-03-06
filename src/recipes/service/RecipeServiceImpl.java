package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.entity.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public int saveOrUpdateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteRecipe(int id) {
        recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> getRecipeByCategoryOrName(Map<String, String> categoryAndName) {
        if (categoryAndName.size() != 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You mustn't pass 0 or more than 1 parameters!");
        }
        for (Map.Entry<String, String> param: categoryAndName.entrySet()) {
            switch (param.getKey()) {
                case "category":
                    return recipeRepository.findAllByCategory(param.getValue());
                    break;
                case "name":
                    return recipeRepository.findAllByName(param.getValue());
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong parameter!");
            }
        }
    }
}
