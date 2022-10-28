package cn.devspace.whynotteaming.Lang;

import io.netty.util.internal.EmptyArrays;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 多语言基础
 */
public class LangBase {

    protected Map<String,String> lang;

    public LangBase(){
        this.lang = loadLangFile("chs");
    }
    public String TranslateOne(String key, String... params){
        return this.TranslateOne(key,Objects.requireNonNullElse(params, EmptyArrays.EMPTY_STRINGS),null);
    }

    /**
     * 接收Object类型参数
     * @param key
     * @param params
     * @return
     */
    protected String TranslateOne(String key,Object... params){
        if (params != null){
            String[] paramsToSting = new String[params.length];
            for (int i = 0;i<params.length;i++){
                paramsToSting[i] = Objects.toString(params[i]);
            }
            return this.TranslateOne(key,paramsToSting,null);
        }
        return this.TranslateOne(key,EmptyArrays.EMPTY_STRINGS,null);
    }

    /**
     * 带单字符串性翻译
     * @param key
     * @param param
     * @param Level
     * @return
     */
    public String  TranslateOne(String key,String param, int Level){
        return this.TranslateOne(key,new String[]{param},null);
    }

    public String TranslateOne(String key,String[] params,String Level){
        String Text = this.getValue(key);
        for (int i=0;i<params.length;i++){
            Text = Text.replace("{%"+i+"}",this.Translation(String.valueOf(params[i]),null));
        }
        return Text;
    }

    protected String Translation(String text,String Level){
        StringBuilder newText = new StringBuilder();
        text = String.valueOf(text);
        String replaceString = null;
        int length = text.length();
        for (int i=0;i<length;i++){
            char c = text.charAt(i);
            if (replaceString != null){
                String t = this.getValue(replaceString.substring(1));
            }
            else if (c == '%'){
                replaceString = String.valueOf(c);
            }else{
                newText.append(c);
            }
        }
        return newText.toString();
    }

    protected String getValue(String key){
        if (this.lang.get(key) != null){
            return this.lang.get(key);
        }
        return null;
    }


    public Map<String,String> getLang(){
        return this.lang;
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
