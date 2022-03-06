package recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "category", "description", "ingredients", "directions"})
@Entity
@Table(name = "recipe")
public class Recipe {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotBlank(message = "Should be not blank!")
    String name;
    @NotBlank(message = "Should be not blank!")
    String category;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime date;
    @NotBlank(message = "Should be not blank!")
    String description;
    @ElementCollection
    @Size(min = 1, message = "Should be more than 1 ingredients!")
    @NotNull(message = "You should write ingredients!")
    List<String> ingredients;
    @ElementCollection
    @Size(min = 1, message = "Should be more than 1 directions!")
    @NotNull(message = "You should write directions!")
    List<String> directions;

    public Recipe() {
        date = LocalDateTime.now();
    }


}
