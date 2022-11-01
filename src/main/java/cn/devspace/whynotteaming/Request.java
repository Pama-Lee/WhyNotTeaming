package cn.devspace.whynotteaming;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Request {

    @GetMapping("/{route}/{method}")
    public String Requests(@PathVariable("route") String route, @PathVariable("method") String method) {
        return new Router(route, method).start(route, method);
    }

}
