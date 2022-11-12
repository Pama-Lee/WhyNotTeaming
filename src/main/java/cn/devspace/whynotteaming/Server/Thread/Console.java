package cn.devspace.whynotteaming.Server.Thread;

import cn.devspace.whynotteaming.Manager.Command.CommandManager;
import cn.devspace.whynotteaming.Manager.Command.ConsoleManager;
import cn.devspace.whynotteaming.Message.Log;

import java.util.Scanner;

public class Console implements Runnable{


    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        Log.sendLog("send:");
        while (true){
            Scanner scanner = new Scanner(System.in);
            // 读取整行
            String command= scanner.nextLine();
            CommandManager.ConsoleSupport(command);
        }
    }
}
