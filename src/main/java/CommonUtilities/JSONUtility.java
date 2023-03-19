package CommonUtilities;

import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;

public class JSONUtility {



    @SneakyThrows
    public static Object readvalueFromJSON(String filepath, String regex){

        File fileSchema = new File(filepath);
        JSONTokener schemaFile = new JSONTokener(new FileInputStream(fileSchema));
        JSONObject json = new JSONObject(schemaFile);
        return JsonPath.read(json.toString(), regex);

    }


}
