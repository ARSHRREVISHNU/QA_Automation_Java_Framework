package API.Model.Reqres.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class GetSingleUserResponseBody {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("support")
    private Support support;

    @Setter
    private int statusCode;

    @Getter
    public static class Data{
        @JsonProperty("id")
        private int id;
        @JsonProperty("email")
        private String email;
        @JsonProperty("first_name")
        private String first_name;
        @JsonProperty("last_name")
        private String last_name;
        @JsonProperty("avatar")
        private String avatar;
    }


    @Getter
    public static class Support{

        @JsonProperty("url")
        private String url;
        @JsonProperty("text")
        private String text;

    }

}
