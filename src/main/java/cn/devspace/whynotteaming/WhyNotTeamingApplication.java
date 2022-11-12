/**
 * _      ____        _  __     __ ______               _
 * | | /| / / /  __ __/ |/ /__  / //_  __/__ ___ ___ _  (_)__  ___ _
 * | |/ |/ / _ \/ // /    / _ \/ __// / / -_) _ `/  ' \/ / _ \/ _ `/
 * |__/|__/_//_/\_, /_/|_/\___/\__//_/  \__/\_,_/_/_/_/_/_//_/\_, /
 * /___/                                         /___/
 * Author: Pama Lee
 */
package cn.devspace.whynotteaming;


import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import cn.devspace.whynotteaming.Server.Thread.Console;
import cn.devspace.whynotteaming.Server.Thread.WebServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class WhyNotTeamingApplication {
    //入口类
    public static void main(String[] args) {
        try{
            Server server = new Server();
            server.Start();
        }catch (Exception e){
            Log.sendWarn(e.toString());
        }
        //init
        WebServer webServer = new WebServer(args);
        webServer.run();

        Log.sendLog("测试");
        Console console = new Console();
        console.run();
    }

}
