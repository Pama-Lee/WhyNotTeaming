package cn.devspace.whynotteaming.Manager;

import cn.devspace.whynotteaming.Manager.Annotation.Router;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationManager {

    public static Map<String,String> getRouterAnnotation(Class<?> classes){
        Method[] methods = classes.getMethods();
        Map<String,String> res = new HashMap<>();
        for (Method method:methods){
            Router router =  method.getAnnotation(Router.class);
            if (router!=null){
                res.put(method.getName(),router.value());
                //Log.sendLog(method.getName());
            }
        }
        return res;
    }


}
