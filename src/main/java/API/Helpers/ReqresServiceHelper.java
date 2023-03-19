package API.Helpers;

import API.Model.Reqres.Request.CreateUserRequestBody;
import API.Model.Reqres.Response.CreateUserResponseBody;
import API.Model.Reqres.Response.GetSingleUserResponseBody;
import CommonUtilities.CommonUtility;
import Constants.EndPoints;
import Constants.FilePaths;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresServiceHelper {

     static ReqresServiceHelper reqresServiceHelperInstance;
    private static final String BASE_URL = CommonUtility.readPropertyFile(FilePaths.API_CONFIG_PROPERTIES).getProperty("reqresBaseUrl");

    private ReqresServiceHelper(){
        RestAssured.baseURI = BASE_URL;
    }

    public static ReqresServiceHelper getInstance(){

        if(reqresServiceHelperInstance == null){

            synchronized(ReqresServiceHelper.class)
            {
                reqresServiceHelperInstance = new ReqresServiceHelper();
            }
        }
        return reqresServiceHelperInstance;
    }

    public GetSingleUserResponseBody getSingleUser(int id){
        Response response = RestAssured
                            .given()
                            .pathParam("id", id)
                            .contentType(ContentType.JSON)
                            .get(EndPoints.GET_SINGLE_USER)
                            .andReturn();

            response.then().log().body();
        GetSingleUserResponseBody getSingleUserResponseBody = response.as(GetSingleUserResponseBody.class);
        getSingleUserResponseBody.setStatusCode(response.getStatusCode());
        return getSingleUserResponseBody;
    }



    public CreateUserResponseBody createUser(CreateUserRequestBody createUserRequestBody){
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(createUserRequestBody)
                .post(EndPoints.CREATE_USER)
                .andReturn();

            response.then().log().body();


            CreateUserResponseBody createUserResponseBody = response.as(CreateUserResponseBody.class);
            createUserResponseBody.setStatusCode(response.getStatusCode());
            return createUserResponseBody;
    }





}
