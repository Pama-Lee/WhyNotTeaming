package cn.pamalee.whynotteaming;

import cn.devspace.nucleus.Manager.DataBase.DataBase;
import cn.devspace.nucleus.Plugin.PluginBase;
import cn.pamalee.whynotteaming.entity.User;
import cn.pamalee.whynotteaming.mapper.routeMapper;
import org.hibernate.Session;

/**
 * Plugin需要继承PluginBase才能被加载
 */
public class main extends PluginBase {
    private DataBase dataBase = null;

    @Override
    public void onLoad() {
        //需要在onLoad时加载路由和注册命令等
        sendLog("WhyNotTeaming正在启动...");
        //注册路由
        initRoute(routeMapper.class);
        //注册数据库
        DataBase database = new DataBase(this.getClass(),new User());
        this.dataBase = database;
        Session user = database.getSession();
        User user1 = new User();
        user1.setUsername("小明");
        user1.setEmail("1249072779@qq.com");
        user1.setPhone("17377307886");
        user.save(user1);
        user.getTransaction().commit();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}
