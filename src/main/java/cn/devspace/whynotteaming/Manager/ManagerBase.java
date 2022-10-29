package cn.devspace.whynotteaming.Manager;

import cn.devspace.whynotteaming.Lang.LangBase;
import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ManagerBase extends LangBase {

    public String Map2Json(Map<String, String> Map) {
        Gson gson = new Gson();
        return gson.toJson(Map);
    }

    public Map<String, String> makeResponse(int code, int status, String Message) {
        Map<String, String> Response = new HashMap<>();
        Response.put("code", String.valueOf(code));
        Response.put("status", String.valueOf(status));
        Response.put("message", Message);
        return Response;
    }

    public String ResponseString(int code, int status, String Message){
        return this.Map2Json(this.makeResponse(code,status,Message));
    }

    public static Map<String,String> getSingeYaml(String Path){
        Yaml yaml = new Yaml();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(Path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> Map = yaml.loadAs(bf,Map.class);
        return Map;
    }

    public String getLangSet(){
        return getSingeYaml(this.getClass().getResource("/whynotteaming.yml").getPath().substring(1)).get("app.language");
    }
}
