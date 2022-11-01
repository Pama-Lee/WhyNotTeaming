package cn.devspace.whynotteaming.App.Login;

import cn.devspace.whynotteaming.Plugin.AppBase;

public class Login extends AppBase {
    public void onLoad() {

        sendLog("hello");
    }

    public String onCall(String route,String method){
        sendLog("A visit From "+method);
        return ResponseString(200,1,"A visit From "+method);
    }
}
