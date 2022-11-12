package cn.devspace.whynotteaming.Server.Thread;

import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import cn.devspace.whynotteaming.WhyNotTeamingApplication;
import org.springframework.boot.SpringApplication;

public class WebServer implements Runnable {

    private String[] args;

    public WebServer(String[] args){
        this.args = args;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        Log.sendLog(Server.getInstance().TranslateOne("Server.Open"));
        SpringApplication.run(WhyNotTeamingApplication.class, this.args);

    }
}
