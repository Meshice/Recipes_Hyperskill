package recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"timestamp","status","error","message","path"})
public class NotValidException {

    @JsonIgnore
    final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    LocalDateTime timestamp;
    Integer status;
    String error;
    String message;
    String path;

    public NotValidException(HttpStatus httpStatus, String message, HttpServletRequest request) {
        this.timestamp = LocalDateTime.parse(LocalDateTime.now().format(dateFormat), dateFormat);
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = request.getRequestURL().toString();
    }
}
