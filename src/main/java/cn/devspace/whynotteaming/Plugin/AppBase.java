package cn.devspace.whynotteaming.Plugin;


import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;


import static cn.devspace.whynotteaming.Server.Server.RunPath;

abstract public class AppBase extends ManagerBase {

    protected String callback = null;
    protected static String LoadingApp = null;
    protected static LangBase AppLang=null;

    protected String AppName;

    public AppBase() {

    }
    public void onLoad() {

    }

    public void onEnable(){

    }

    public String onCall(String route,String method){
        return null;
    }



    protected void sendLog(String log){
        Log.sendAppMessage(new Exception().getStackTrace()[1].getClassName(),log);
    }

    public static void loadApps(Server server) {
try {
    Map<String, ArrayList<String>> Maps = getSingeYaml(RunPath + "/resources/whynotteaming.yml", true);
    if (Maps != null) {
        ArrayList<String> enableApps = Maps.get("EnableApp");
        for (String apps : enableApps) {
            LoadingApp = apps;
            Description appDes = new Description(new ClassPathResource("app/" + apps + "/app.yml").getInputStream());
            String main = appDes.getMain();
                Class<?> c = Class.forName(main);
                AppBase app = (AppBase) c.getDeclaredConstructor().newInstance();
                Log.AppStart(Server.getInstance().Translators("App.Start",apps));
                app.localApp(apps);
                //开始执行onload
                app.onLoad();
                Log.AppStart(Server.getInstance().Translators("App.Loaded",apps));
                server.AppList.put(apps,app);
        }
    }
}catch (FileNotFoundException fe){
    Log.sendWarn(Server.getInstance().TranslateOne("App.NotFound",LoadingApp));
    Log.sendWarn(Server.getInstance().TranslateOne("App.Error",LoadingApp));
}
catch (Exception e){
    Log.sendWarn(e.toString());
    Log.sendWarn(Server.getInstance().TranslateOne("App.Error",LoadingApp));
}
    }

    protected LangBase loadLanguage(){
        String language = getLanguage();
        try{
            InputStream langStream = new ClassPathResource("app/"+this.AppName+"/Language/"+language+".ini").getInputStream();
            LangBase lb = new LangBase(langStream);
            AppLang = lb;
            return lb;
        }catch (Exception e){
            Log.sendWarn(e.toString());
            disableApp();
            return null;
        }

    }

    protected String Translation(String key, Object... params){
        return TranslateOne(key,params);
    }
    protected String Translation(String key, String[] param){
        return TranslateOne(key,param);
    }

    public void localApp(String AppName){
        this.AppName = AppName;
    }

    protected String getLocalApp(){
        return this.AppName;
    }

    //TODO: 卸载APP，等待完善
    protected void disableApp(){

    }

}
