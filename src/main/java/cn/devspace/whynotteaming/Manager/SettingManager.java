package cn.devspace.whynotteaming.Manager;

import cn.devspace.whynotteaming.Message.Log;

import java.util.Map;

public class SettingManager extends ManagerBase{


    public String getSetting(String key){
        Map<String,String> Map = super.getSingeYaml(System.getProperty("user.dir")+"/resources/whynotteaming.yml");
       return Map.get(key);
    }

}
