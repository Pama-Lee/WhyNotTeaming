package cn.devspace.whynotteaming.Server;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Manager.SettingManager;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Plugin.AppBase;
import cn.devspace.whynotteaming.Plugin.PluginBase;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Server extends ManagerBase {

    public static final String VERSION = "0.0.1-alpha";
    public static final String AUTHOR = "Pama Lee";

    public static final String NAME = "WhyNotTeaming(JAVA)";

    public final URL ROOT = this.getClass().getResource("/");
    public String classPath = new File(this.getClass().getResource("").getPath()).getPath() + "/";
    public String AppPath = classPath.replace("Server", "App") + "/";
    public static String RunPath = System.getProperty("user.dir")+"/";
    public LangBase lang;
    public SettingManager settingManager;

    public String Language;

    public static Server Instance;

    protected static Thread currentThread;

    public Map<String, PluginBase> pluginList;
    public static Map<String, AppBase> AppList = new HashMap<>();

    public static Map<String, AppBase> AppClass = new HashMap<>();
    private static Runtime runtime = Runtime.getRuntime();

    public Server() {
        init();
        //初始化多语言
        this.lang = new LangBase();
        this.Language = this.lang.getLanguage();
        this.settingManager = new SettingManager();

        currentThread = Thread.currentThread();
        Instance = this;
    }

    public static void init() {
        if (!new File(RunPath + "resources/").exists()) {
            try {
                InputStream set = new ClassPathResource("whynotteaming.yml").getInputStream();
                InputStream route = new ClassPathResource("route.yml").getInputStream();
                Log.sendLog(RunPath);
                new File(RunPath + "resources/").mkdirs();
                Files.copy(set, Path.of(RunPath + "resources/whynotteaming.yml"));
                Files.copy(route, Path.of(RunPath + "resources/route.yml"));

                Log.sendError("The configuration file is ready, please reopen the program", 200);
            } catch (IOException e) {
                Log.sendError(e.toString(), 1);
            }

        }
    }


    public void Start() {
        //服务器开启

        //处理语言
        Log.sendLog(TranslateOne("Language.Use", this.Language));
        Log.sendLog(TranslateOne("App.Name", getName(), getServerVersion()));
        Log.sendLog(TranslateOne("App.Version", getServerVersion()));
        Log.sendLog(TranslateOne("App.Licence"));

        AppBase.loadApps(this);

        Log.sendLog(TranslateOne("App.Run.UseMemory", getUsedMemory()));
        enableApp();

    }

    private void enableApp(){
        for(String app:this.AppList.keySet()){
            AppBase appClass = this.AppList.get(app);
            appClass.onEnable();
        }
    }


    public static <T> boolean isStartupFromJar(Class<T> clazz) {
        File file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
        return file.isFile();
    }


    public static double getUsedMemory() {
        String res = String.format("%.2f", (double) runtime.totalMemory() / 1024 / 1024 - (double) runtime.freeMemory() / 1024 / 1024);
        return Double.parseDouble(res);
    }

    public static Server getInstance() {
        return Instance;
    }

    public LangBase getLanguages() {
        return this.lang;
    }

    public String getLangSet() {
        return this.Language;
    }

    public static String getServerVersion() {
        return VERSION;
    }

    public static String getAuthor() {
        return AUTHOR;
    }

    public static void Shutdown(int Code) {
        System.exit(Code);
    }

    public static String getName() {
        return NAME;
    }
}
