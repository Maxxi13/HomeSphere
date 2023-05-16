package ro.itschool.homesphere.exceptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AbsentResourceException extends Exception{
    private String message;
    private Integer id;
}
