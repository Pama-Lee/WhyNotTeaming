package cn.devspace.whynotteaming.Server;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Manager.SettingManager;
import cn.devspace.whynotteaming.Message.Log;

public class Server extends ManagerBase {

    public static final String VERSION = "0.0.1-alpha";
    public static final String AUTHOR = "Pama Lee";

    public LangBase lang;
    public SettingManager settingManager;

    public String Language;

    public Server(){
        this.settingManager = new SettingManager();
        //初始化多语言
        this.lang = new LangBase();
        String test = getLanguage().TranslateOne("Test.Test","hello");
        String author = Server.getAuthor();
        Log.sendLog(author);
        Log.sendWarn(test);
    }


    public LangBase getLanguage(){
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
}
