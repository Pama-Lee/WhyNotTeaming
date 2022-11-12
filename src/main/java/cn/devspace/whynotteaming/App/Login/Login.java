package cn.devspace.whynotteaming.App.Login;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.Annotation.Router;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Plugin.AppBase;

import java.util.Map;

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

    //REQ得到请求中的头文件
    @Override
    public String onCall(String route, String method, Map<String,String> Req){
        sendLog("A visit From "+method+"\t"+Req.toString());
        return ResponseString(200,1,"A visit From "+method);
    }

    @Router("ming/mingming")
    public String func(){
        Log.sendLog("崇高了！");
        return "hello!";
    }

    @Router("getLogin")
    public void ming(){
        sendLog("成功了！");
    }


}
