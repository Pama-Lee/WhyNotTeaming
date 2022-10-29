package cn.devspace.whynotteaming.Manager;

import cn.devspace.whynotteaming.Message.Log;

import java.util.Map;

public class SettingManager extends ManagerBase{

    public String getSetting(String key){
        Map<String,String> Map = super.getSingeYaml(this.getClass().getResource("/whynotteaming.yml").getPath().substring(1));
       return Map.get(key);
    }

}
