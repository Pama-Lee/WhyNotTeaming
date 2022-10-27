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
        LangBase initLang = new LangBase();

        MessageBase message = new MessageBase();
        String author = Server.getAuthor();
        author = message.BaseFormat(MessageBase.METHOD_LOG,author);
        System.out.print(author);

        //init
        SpringApplication.run(WhyNotTeamingApplication.class, args);

    }

}
