package cn.devspace.whynotteaming;

import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;


@RestController
public class Request extends HttpServlet {
    @GetMapping("**")
    public String Router(HttpServletRequest request) {
        Map<String, Map<String, String>> router = Server.RouterList;
        Set<String> map = router.keySet();
        for (String app : map) {
            for (String method : router.get(app).keySet()) {
                Map<String, String> AppRouters = router.get(app);
                String ReqURI = request.getRequestURI();
                if (ReqURI.equals("/" + app + "/" + AppRouters.get(method))) {
                    try {
                        Method methods = Server.AppList.get(app).getClass().getMethod(method);
                        Object doMethod = Server.AppList.get(app);
                        Object m = methods.invoke(doMethod);
                        if (m != null) {
                            return m.toString();
                        } else {
                            return null;
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        Log.sendWarn(e.toString());
                    }
                }
            }
        }
        return "404";
    }

/*
    @GetMapping("/{route}/{method}")
    public String Requests_GET(@PathVariable("route") String route, @PathVariable("method") String method,@RequestParam Map<String, String> map) {
        if (!map.isEmpty()){
            return new Router(route,method).start(route,method,map);
        }
        return new Router(route, method).start(route, method);
    }

    @PostMapping("/{route}/{method}")
    public String Requests_POST(@PathVariable("route") String route, @PathVariable("method") String method,@RequestBody Map<String, String> map) {
        if (map != null){
            return new Router(route,method).start(route,method,map);
        }
        return new Router(route, method).start(route, method);
    }
    @GetMapping("/")
    public String index(){
        return "<h1 style='color:orange'>Welcome to Use WhyNotTeaming!</h1>";
    }



*/
}
