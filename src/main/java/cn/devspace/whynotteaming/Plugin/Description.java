package cn.devspace.whynotteaming.Plugin;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class Description {

    private String name;
    private String author;
    private String version;
    private String main;
    private String description;

    public Description(String YamlString){
        Yaml yml = new Yaml();
        this.LoadMap(yml.loadAs(YamlString, Map.class));
    }

    public void LoadMap(Map<String,Object> map){
        if (map != null){
            this.name = (String) map.get("Name");
            this.author = (String) map.get("Author");
            this.version = (String) map.get("Version");
            this.main = (String) map.get("Main");
            this.description = (String) map.get("Description");

        }
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getVersion(){
        return this.version;
    }

    public String getDescription(){
        return this.description;
    }

    public String getMain(){
        return this.main;
    }

}
