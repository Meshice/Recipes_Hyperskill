package recipes.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findAllByCategory(String category);
    List<Recipe> findAllByName(String name);

}
