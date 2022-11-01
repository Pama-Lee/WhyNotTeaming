package cn.devspace.whynotteaming.Manager;

import cn.devspace.whynotteaming.Lang.LangBase;
import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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

    public String ResponseString(int code, int status, String Message) {
        return this.Map2Json(this.makeResponse(code, status, Message));
    }

    public static Map<String, String> getSingeYaml(String Path) {
        Yaml yaml = new Yaml();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(Path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> Map = yaml.loadAs(bf, Map.class);
        return Map;
    }
    public static Map<String, ArrayList<String>> getSingeYaml(String Path,boolean ArrayList) {
        Yaml yaml = new Yaml();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(Path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<String, ArrayList<String>> Map = yaml.loadAs(bf, Map.class);
        return Map;
    }

    public static String Translators(String key){
        return TranslateOne(key);
    }

    public String getJarPath() {
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (System.getProperty("os.name").contains("dows")) {
            path = path.substring(1, path.length());
        }
        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf("."));
            return path.substring(0, path.lastIndexOf("/"));
        }
        return path.replace("target/classes/", "");
    }

    public static String Translators(String key,Object... params){
        return TranslateOne(key,params);
    }
    public String getLangSet() {
        return getSingeYaml(this.getClass().getResource("/whynotteaming.yml").getPath().substring(1)).get("app.language");
    }
}
