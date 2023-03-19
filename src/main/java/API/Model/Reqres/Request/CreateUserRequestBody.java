package API.Model.Reqres.Request;


import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateUserRequestBody {


    private String name;
    private String job;
    private String email;


    public CreateUserRequestBody(Builder builder){

        this.name = builder.name;
        this.email = builder.email;
        this.job = builder.job;

    }

    public static class Builder{

        private String name;
        private String job;
        private String email;


        public Builder(){
            this.name = "shrre";
            this.job = "job";
            this.email = String.format("%s@gmail.com", UUID.randomUUID());
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder job(String job)
        {
            this.job = job;
            return this;
        }

        public Builder email(String email)
        {
            this.email = email;
            return this;
        }

        public CreateUserRequestBody build(){
            CreateUserRequestBody createUserRequestBody = new CreateUserRequestBody(this);
            return createUserRequestBody;
        }
    }


}
