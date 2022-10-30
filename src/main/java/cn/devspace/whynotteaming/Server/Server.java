package cn.devspace.whynotteaming.Server;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Manager.SettingManager;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Plugin.AppBase;
import cn.devspace.whynotteaming.Plugin.AppLoader;
import cn.devspace.whynotteaming.Plugin.PluginBase;
import com.aio.util.FreeMarkerUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Server extends ManagerBase {

    public static final String VERSION = "0.0.1-alpha";
    public static final String AUTHOR = "Pama Lee";

    public static final String NAME = "WhyNotTeaming(JAVA)";

    public final URL ROOT = this.getClass().getResource("/");
    public String classPath = new File(this.getClass().getResource("").getPath()).getPath()+"/";
    public String AppPath = classPath.replace("Server","App")+"/";
    public String RunPath = System.getProperty("user.dir")+"/";
    public LangBase lang;
    public SettingManager settingManager;

    public String Language;

    public static Server Instance;

    protected static Thread currentThread;

    public Map<String, PluginBase> pluginList;
    public Map<String, AppBase> AppList;
    private static Runtime runtime = Runtime.getRuntime();

    public Server(){
        //初始化多语言
        this.lang = new LangBase();
        this.Language = this.lang.getLanguage();
        init();
       this.settingManager = new SettingManager();

        currentThread = Thread.currentThread();
        Instance = this;
    }

    public void init(){
        if (!new File(RunPath+"resources/").exists()){
            try{
                InputStream set = new ClassPathResource("whynotteaming.yml").getInputStream();
                InputStream route = new ClassPathResource("route.yml").getInputStream();

                new File(RunPath+"resources/").mkdirs();
                Files.copy(set, Path.of(RunPath + "resources/whynotteaming.yml"));
                Files.copy(route, Path.of(RunPath + "resources/route.yml"));

                Log.sendError("The configuration file is ready, please reopen the program",200);
            }catch (IOException e){
                Log.sendError(e.toString(),1);
            }

        }
    }


    public void Start(){
        //服务器开启

        //处理语言
        Log.sendLog(TranslateOne("Language.Use",this.Language));
        Log.sendLog(TranslateOne("App.Name",getName(),getServerVersion()));
        Log.sendLog(TranslateOne("App.Version",getServerVersion()));
        Log.sendLog(TranslateOne("App.Licence"));
        Log.sendLog(TranslateOne("App.Run.UseMemory",getUsedMemory()));
        Log.sendLog(AppPath);

        new AppLoader(this);

    }


    public static<T> boolean isStartupFromJar(Class<T> clazz) {
        File file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
        return file.isFile();
    }



    public static double getUsedMemory(){
        String res = String.format("%.2f",(double)runtime.totalMemory() / 1024 / 1024 - (double) runtime.freeMemory() / 1024 / 1024);
        return Double.parseDouble(res);
    }

    public static Server getInstance(){
        return Instance;
    }

    public LangBase getLanguages(){
        return this.lang;
    }

    public String getLangSet(){
        return this.Language;
    }

    public static String getServerVersion(){
        return VERSION;
    }

    public static String getAuthor(){
        return AUTHOR;
    }

    public static void Shutdown(int Code){
        System.exit(Code);
    }

    public static String getName(){
        return NAME;
    }
}
