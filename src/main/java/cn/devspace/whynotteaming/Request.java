package cn.devspace.whynotteaming;

import cn.devspace.whynotteaming.Message.Log;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Request {

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

}
