package cn.devspace.whynotteaming.Manager.Command;

public class ConsoleManager implements CommandBase {
/*
    @Commands(Command = "help")
    public String help(String[] args){
        Log.sendLog("这是Help命令!");
        Log.sendLog("参数:"+ Arrays.toString(args));
        return "123";
    }

    @Commands(Command = "helpssss")
    public String helps(String[] args){

        return "123";
    }

    public void registerCommand(){
        Map<String,String> map = AnnotationManager.getCommandsAnnotation(this.getClass());
        for (String method:map.keySet()){
            try {
                Map<CommandBase, Method> min = new HashMap<>();
                min.put(this,this.getClass().getMethod(method,String[].class));
                Server.CommandMap.put(map.get(method),min);
            } catch (NoSuchMethodException e) {
                Log.sendWarn(Server.getInstance().TranslateOne("Command.NotFoundMethod",map.get(method),method));
            }
        }

    }
*/
}
