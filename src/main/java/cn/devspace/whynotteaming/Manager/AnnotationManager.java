package cn.devspace.whynotteaming.Manager;

import cn.devspace.whynotteaming.Manager.Annotation.Commands;
import cn.devspace.whynotteaming.Manager.Annotation.Router;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解管理器
 * 为令内置源码避免使用springboot原始注解
 */
public class AnnotationManager {

    public static Map<String, String> getRouterAnnotation(Class<?> classes) {
        Method[] methods = classes.getMethods();
        Map<String, String> res = new HashMap<>();
        for (Method method : methods) {
            Router router = method.getAnnotation(Router.class);
            if (router != null) {
                res.put(method.getName(), router.value());
            }
        }
        return res;
    }

    public static Map<String, String> getCommandsAnnotation(Class<?> classes) {
        Method[] methods = classes.getMethods();
        Map<String, String> res = new HashMap<>();
        for (Method method : methods) {
            Commands commands = method.getAnnotation(Commands.class);
            if (commands != null) {
                res.put(method.getName(), commands.Command());
            }
        }
        return res;
    }

}
