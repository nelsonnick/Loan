package com.wts.common;

import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.wts.common.model._MappingKit;
import com.wts.controller.*;

/**
 * Config class
 *
 * @author wts
 * @date 2019/12/6
 */
public class Config extends JFinalConfig {
    static Prop p;

    /**
     * 启动入口，运行此 main 方法可以启动项目，此 main 方法可以放置在任意的 Class 类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        UndertowServer.start(Config.class);
    }

    /**
     * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
     * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
     */
    static void loadConfig() {
        if (p == null) {
            p = PropKit.useFirstFound("demo-config-pro.txt", "demo-config-dev.txt");
        }
    }

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        loadConfig();

        me.setDevMode(p.getBoolean("devMode", false));

        /**
         * 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
         * 注入动作支持任意深度并自动处理循环注入
         */
        me.setInjectDependency(true);

        // 配置对超类中的属性进行注入
        me.setInjectSuperClass(true);
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
//        调试环境
//        String base = "d";
//        开发环境及上线环境
        String base = "";
        me.add(base + "/", MainController.class);
        me.add(base + "/loan", LoanController.class);
        me.add(base + "/statistics", StatisticsController.class);
    }

    public void configEngine(Engine me) {
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // 配置 druid 数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
        me.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        me.add(arp);

    }

    public static DruidPlugin createDruidPlugin() {
        loadConfig();

        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
        me.add(new TxByMethods("save", "update"));
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        //设置上下文路径
        me.add(new ContextPathHandler("contextPath"));
    }
}