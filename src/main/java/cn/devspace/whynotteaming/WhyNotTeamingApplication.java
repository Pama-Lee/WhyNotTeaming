/**
 *   _      ____        _  __     __ ______               _
 *  | | /| / / /  __ __/ |/ /__  / //_  __/__ ___ ___ _  (_)__  ___ _
 *  | |/ |/ / _ \/ // /    / _ \/ __// / / -_) _ `/  ' \/ / _ \/ _ `/
 *  |__/|__/_//_/\_, /_/|_/\___/\__//_/  \__/\_,_/_/_/_/_/_//_/\_, /
 *              /___/                                         /___/
 *              Author: Pama Lee
 *
 */
package cn.devspace.whynotteaming;

import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static cn.devspace.whynotteaming.Server.Server.isStartupFromJar;

@SpringBootApplication
public class WhyNotTeamingApplication {
//入口类
    public static void main(String[] args) {
          Server server = new Server();
          server.Start();
        //init
        SpringApplication.run(WhyNotTeamingApplication.class, args);

    }

}
