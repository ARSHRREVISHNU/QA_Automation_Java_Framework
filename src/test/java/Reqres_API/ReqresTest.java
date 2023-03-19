package Reqres_API;

import API.Helpers.ReqresServiceHelper;
import API.Model.Reqres.Request.CreateUserRequestBody;
import API.Model.Reqres.Response.CreateUserResponseBody;
import API.Model.Reqres.Response.GetSingleUserResponseBody;
import Constants.FilePaths;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ReqresTest extends APITestUtils{

    @Test
    public void getUserTest(Method m){
        setMethodName(m);
        int id = (int) getValueFromJson(FilePaths.REQRES_DATA, "id");
        GetSingleUserResponseBody getSingleUserResponseBody = reqresServiceHelper.getSingleUser(id);
        Assert.assertEquals(getSingleUserResponseBody.getStatusCode(), 200);
    }

    @Test
    public void createUserTest(Method m){
        setMethodName(m);
        String name = getValueFromJson(FilePaths.REQRES_DATA, "data[0].name").toString();
        String email = getValueFromJson(FilePaths.REQRES_DATA,"data[0].mail").toString();
        String job = getValueFromJson(FilePaths.REQRES_DATA, "data[0].job").toString();
        CreateUserRequestBody createUserRequestBody = new CreateUserRequestBody.Builder().name(name).email(email).job(job).build();
        CreateUserResponseBody createUserResponseBody = reqresServiceHelper.createUser(createUserRequestBody);
        Assert.assertEquals(createUserResponseBody.getStatusCode(), 201);
        Assert.assertEquals(createUserResponseBody.getName(), name);
        Assert.assertEquals(createUserResponseBody.getJob(), job);
        Assert.assertEquals(createUserResponseBody.getEmail(), email);
    }

}
