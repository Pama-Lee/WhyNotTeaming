package cn.devspace.whynotteaming.Plugin;


import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;


import static cn.devspace.whynotteaming.Server.Server.RunPath;

abstract public class AppBase extends ManagerBase {

    protected String callback = null;
    protected static String LoadingApp = null;

    public AppBase() {

    }

    private Server server;

    public AppBase(Server server) {
        this.server = server;
    }

    public void onLoad() {

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
                Log.AppStart(Translators("App.Start",apps));
                //开始执行onload
                app.onLoad();
                server.AppList.put(apps,app);
        }
    }
}catch (FileNotFoundException fe){
    Log.sendWarn(TranslateOne("App.NotFound",LoadingApp));
    Log.sendWarn(TranslateOne("App.Error",LoadingApp));
}
catch (Exception e){
    Log.sendWarn(e.toString());
    Log.sendWarn(TranslateOne("App.Error",LoadingApp));
}
    }
}
