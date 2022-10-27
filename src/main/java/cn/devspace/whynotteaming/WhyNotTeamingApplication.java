package cn.devspace.whynotteaming;


import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Message.MessageBase;
import cn.devspace.whynotteaming.Server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhyNotTeamingApplication {
//入口类
    public static void main(String[] args) {
        //init Server
        Server server = new Server();


        //init
        SpringApplication.run(WhyNotTeamingApplication.class, args);

    }

}
