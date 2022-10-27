package cn.devspace.whynotteaming;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhyNotTeamingApplication {
//入口类
    public static void main(String[] args) {
        //init SpringBoot
        SpringApplication.run(WhyNotTeamingApplication.class, args);
        //init Server
        Server initServer = new Server();
        //init Lang
        LangBase initLang = new LangBase();

    }

}
