package cn.devspace.whynotteaming.Lang;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LangBase {

    private File LangFile;

    public LangBase(){

        Map<String,String> test = loadLangFile("chs");

        System.out.print(test.get("Test"));
    }


    private Map<String,String> loadLangFile(String Language){
        try(InputStream langStream = this.getClass().getResourceAsStream("/language/"+Language+".ini")){
            InputStreamReader langStreamReader = new InputStreamReader(langStream, StandardCharsets.UTF_8);
            BufferedReader Reader = new BufferedReader(langStreamReader);
            return parasLang(Reader);
        }catch (IOException e){
            System.out.print(e.toString());
            return null;
        }

    }

    private Map<String,String> parasLang(BufferedReader reader) throws IOException{
        Map<String,String> res = new HashMap<>();
        String Line;
        while ((Line = reader.readLine()) != null){
            Line = Line.trim();
            if (Line.isEmpty() || Line.charAt(0) == '#') {
                continue;
            }
            String[] r = Line.split("=", 2);
            if (r.length < 2) {
                continue;
            }
            String key = r[0];
            String value = r[1];
            if (value.length() > 1 && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
                value = value.substring(1, value.length() - 1).replace("\\\"", "\"").replace("\\\\", "\\");
            }
            if (value.isEmpty()) {
                continue;
            }
            res.put(key,value);

        }
        return res;
    }

}
