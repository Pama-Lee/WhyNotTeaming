package cn.devspace.whynotteaming.Server;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Manager.SettingManager;
import cn.devspace.whynotteaming.Message.Log;

public class Server extends ManagerBase {

    public static final String VERSION = "0.0.1-alpha";
    public static final String AUTHOR = "Pama Lee";

    public static final String NAME = "WhyNotTeaming(JAVA)";

    public LangBase lang;
    public SettingManager settingManager;

    public String Language;

    public static Server Instance;

    protected static Thread currentThread;

    private static Runtime runtime = Runtime.getRuntime();

    public Server(){
        this.settingManager = new SettingManager();
        //初始化多语言
        this.lang = new LangBase();
        this.Language = this.lang.getLanguage();

        currentThread = Thread.currentThread();
        Instance = this;
    }

    public void Start(){
        //服务器开启

        //处理语言
        Log.sendLog(TranslateOne("Language.Use",this.Language));
        Log.sendLog(TranslateOne("App.Name",getName(),getServerVersion()));
        Log.sendLog(TranslateOne("App.Version",getServerVersion()));
        Log.sendLog(TranslateOne("App.Licence"));
        Log.sendLog(TranslateOne("App.Run.UseMemory",getUsedMemory()));


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
