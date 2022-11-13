package cn.devspace.whynotteaming.Lang;

import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import io.netty.util.internal.EmptyArrays;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 多语言基础
 */
public class LangBase {

    protected Map<String, String> lang;

    private String Language;

    public LangBase() {
        String Language = getLanguage();
        this.Language = Language;
        lang = loadLangFile(Language);
    }

    public LangBase(InputStream AppInputStream) {
        String Language = getLanguage();
        this.Language = Language;
        lang = loadLangFile(AppInputStream);
    }


    public String getLanguage() {
        try {
            Yaml yaml = new Yaml();
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/resources/whynotteaming.yml"));
            Map<String, String> Map = yaml.loadAs(br, Map.class);
            //Log.sendLog(Map.get("app.language"));
            return Map.get("app.language");
        } catch (FileNotFoundException e) {
            Server.init();
            Log.sendError("The Setting File [whynotteaming.yml] Not Found!", -1);
            return null;
        }
    }

    public String TranslateOne(String key, String... params) {
        return TranslateOne(key, Objects.requireNonNullElse(params, EmptyArrays.EMPTY_STRINGS), null);
    }

    public String Translate(String key, String... params) {
        return TranslateOne(key, Objects.requireNonNullElse(params, EmptyArrays.EMPTY_STRINGS), null);
    }

    /**
     * 接收Object类型参数
     *
     * @param key
     * @param params
     * @return
     */
    protected String TranslateOne(String key, Object... params) {
        if (params != null) {
            String[] paramsToSting = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                paramsToSting[i] = Objects.toString(params[i]);
            }
            return TranslateOne(key, paramsToSting, null);
        }
        return TranslateOne(key, EmptyArrays.EMPTY_STRINGS, null);
    }


    /**
     * 带单字符串性翻译
     *
     * @param key
     * @param param
     * @param Level
     * @return
     */
    public String TranslateOne(String key, String param, int Level) {
        return TranslateOne(key, new String[]{param}, null);
    }

    public String TranslateOne(String key, String[] params, String Level) {
        String Text = getValue(key);
        if (Text == null) {
            return null;
        }
        for (int i = 0; i < params.length; i++) {
            Text = Text.replace("{%" + i + "}", Translation(String.valueOf(params[i]), null));
        }
        return Text;
    }

    protected String Translation(String text, String Level) {
        StringBuilder newText = new StringBuilder();
        text = String.valueOf(text);
        String replaceString = null;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (replaceString != null) {
                String t = getValue(replaceString.substring(1));
            } else if (c == '%') {
                replaceString = String.valueOf(c);
            } else {
                newText.append(c);
            }
        }
        return newText.toString();
    }

    protected String getValue(String key) {
        if (this.lang != null && this.lang.get(key) != null) {
            return this.lang.get(key);
        }
        return null;
    }


    public Map<String, String> getLang() {
        return lang;
    }

    private Map<String, String> loadLangFile(String Language) {

        try (InputStream langStream = new ClassPathResource("Language/" + Language + ".ini").getInputStream()) {
            InputStreamReader langStreamReader = new InputStreamReader(langStream);
            BufferedReader Reader = new BufferedReader(langStreamReader);
            return parasLang(Reader);
        } catch (IOException e) {
            Log.sendError("The Language [" + Language + ".ini] File cannot open!", 778);
            return null;
        } catch (NullPointerException ne) {
            Log.sendError("The Language [" + Language + "] Not Found! Please check your setting in [whynotteaming.yml]", 777);
            return null;
        }

    }

    private Map<String, String> loadLangFile(InputStream LangInputStream) {

        try {
            InputStreamReader langStreamReader = new InputStreamReader(LangInputStream);
            BufferedReader Reader = new BufferedReader(langStreamReader);
            return parasLang(Reader);
        } catch (IOException e) {
            Log.sendError("The Language [" + Language + ".ini] File cannot open!", 778);
            return null;
        } catch (NullPointerException ne) {
            Log.sendError("The Language [" + Language + "] Not Found! Please check your setting in [whynotteaming.yml]", 777);
            return null;
        }

    }


    private Map<String, String> parasLang(BufferedReader reader) throws IOException {
        Map<String, String> res = new HashMap<>();
        String Line;
        while ((Line = reader.readLine()) != null) {
            Line = Line.trim();
            if (Line.isEmpty() || Line.charAt(0) == '#') {
                continue;
            }
            String[] r = Line.split("=", 2);
            if (r.length < 2) {
                continue;
            }
            String key = r[0].replace(" ", "");
            String value = r[1].replace("\"", "");
            if (value.length() > 1 && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
                value = value.substring(1, value.length() - 1).replace("\\\"", "\"").replace("\\\\", "\\");
            }
            if (value.isEmpty()) {
                continue;
            }
            res.put(key, value);

        }
        return res;
    }

}
