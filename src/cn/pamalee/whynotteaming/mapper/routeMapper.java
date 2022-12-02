package cn.pamalee.whynotteaming.mapper;

import cn.devspace.nucleus.Manager.Annotation.Router;

public class routeMapper {

    @Router("wnt")
    public String wnt(){
        return "成功路由!";
    }



}
