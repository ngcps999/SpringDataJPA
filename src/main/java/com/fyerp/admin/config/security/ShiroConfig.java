//package com.fyerp.admin.config.security;
//
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//    @Value("${spring.redis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.pool.max-wait}")
//    private long maxWaitMillis;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        //拦截器
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//
//        //配置不会被监听的连接，顺序判断
//        filterChainDefinitionMap.put("static/**","anon");
//        filterChainDefinitionMap.put("/ajaxLogin", "anon");
//        filterChainDefinitionMap.put("swagger-ui.html","anon");
//        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("logout","logout");
//
//
//        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        filterChainDefinitionMap.put("/**", "authc"); // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        // 登录成功后要跳转的链接
//        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/unauth");
//
//        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * 凭证匹配器
//     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//     * ）
//     *
//     * @return
//     */
////    @Bean
////    public HashedCredentialsMatcher hashedCredentialsMatcher() {
////        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
////        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
////        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
////        return hashedCredentialsMatcher;
////    }
//
//    @Bean
//    public MyShiroRealm myShiroRealm() {
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
////        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
//        return myShiroRealm;
//    }
//
//
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
//        // 自定义session管理 使用redis
//        securityManager.setSessionManager(sessionManager());
//        // 自定义缓存实现 使用redis
//        securityManager.setCacheManager(redisCacheManager());
//        return securityManager;
//    }
//
//    //自定义sessionManager
//    @Bean
//    public SessionManager sessionManager() {
//        MySessionManager mySessionManager = new MySessionManager();
//        mySessionManager.setSessionDAO(redisSessionDAO());
//        return mySessionManager;
//    }
//
//    /**
//     * 配置shiro redisManager
//     * <p>
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        redisManager.setPort(port);
//        redisManager.setExpire(1800);// 配置缓存过期时间
//        redisManager.setTimeout(timeout);
//        redisManager.setPassword(password);
//        return redisManager;
//    }
//
//    /**
//     * cacheManager 缓存 redis实现
//     * <p>
//     * 使用的是shiro-redis开源插件
//     *
//     * @return
//     */
//    @Bean
//    public RedisCacheManager redisCacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        return redisCacheManager;
//    }
//
//    /**
//     * RedisSessionDAO shiro sessionDao层的实现 通过redis
//     * <p>
//     * 使用的是shiro-redis开源插件
//     */
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
//    }
//
//    /**
//     * 开启shiro aop注解支持.
//     * 使用代理方式;所以需要开启代码支持;
//     *
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    /**
//     * 注册全局异常处理
//     * @return
//     */
//    @Bean(name = "exceptionHandler")
//    public HandlerExceptionResolver handlerExceptionResolver() {
////        return new MyExceptionHandler();
//        return null;
//    }
//
//}
