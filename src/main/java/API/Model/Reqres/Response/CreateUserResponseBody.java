package API.Model.Reqres.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateUserResponseBody {

    @Setter
    private int statusCode;

    private String name;
    private String email;
    private String job;
    private int id;
    private String createdAt;


}
