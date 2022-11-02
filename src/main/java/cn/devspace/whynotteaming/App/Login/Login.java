package cn.devspace.whynotteaming.App.Login;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Plugin.AppBase;

public class Login extends AppBase {

    private LangBase lang;
    public void onLoad() {
        this.lang = loadLanguage();
        sendLog("On Loading!!");
    }

    public void onEnable(){
        sendLog("on Enable!!");
       String f2 =  this.lang.Translate("Test","成功！！");
       sendLog(f2);
    }

    public String onCall(String route,String method){
        sendLog("A visit From "+method);
        return ResponseString(200,1,"A visit From "+method);
    }
}
