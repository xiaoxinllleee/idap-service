package org.cmms.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.shiro.authc.ShiroRealm;
import org.cmms.modules.shiro.authc.aop.JwtFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author: Scott
 * @date: 2018/2/7
 * @description: shiro 配置类
 */

@Slf4j
@Configuration
public class ShiroConfig {

	@Value("${common.shiro.excludeUrls}")
	private String excludeUrls;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String redisPassword;

	@Value(value = "${common.shiro.brokenAccessCheck}")
	private boolean brokenAccessCheck;

	@Value(value = "${common.shiro.corsAllowOrigin:}")
	private String corsAllowOrigin;

	/**
	 * Filter Chain定义说明
	 *
	 * 1、一个URL可以配置多个Filter，使用逗号分隔
	 * 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		if(oConvertUtils.isNotEmpty(excludeUrls)){
			String[] permissionUrl = excludeUrls.split(",");
			for(String url : permissionUrl){
				filterChainDefinitionMap.put(url,"anon");
			}
		}
		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
		filterMap.put("jwt", new JwtFilter(brokenAccessCheck, corsAllowOrigin));
		shiroFilterFactoryBean.setFilters(filterMap);
		//cas验证登录
		filterChainDefinitionMap.put("/khxxgl/khxxglKhxqXxnyzt/tjfx2exp", "anon");
		//filterChainDefinitionMap.put("/nh/vNhPjsx/xendFtl", "anon");
		filterChainDefinitionMap.put("/nh/nhbkbpy/importExcel", "anon");
		filterChainDefinitionMap.put("/cas/client/validateLogin", "anon");
		filterChainDefinitionMap.put("/khlc/jczbgl/pmaFBaseIndexType/**", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/mobile/tbTjfxCktzhzxxBankPmRest/**", "anon"); //登录验证码接口排除
		// 配置不会被拦截的链接 顺序判断
		//filterChainDefinitionMap.put("/mobile/**/**", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/app/appVersionInfo/checkupdate", "anon"); //APP更新
		filterChainDefinitionMap.put("/sys/getzxbg", "anon"); //APP更新


		filterChainDefinitionMap.put("/sys/getCheckCode", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/sys/calletl", "anon"); //etl调用接口

		filterChainDefinitionMap.put("/word/**", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/bigscreen/index/**", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/bigscreen/vDpYwhz/**", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/xdgl/grkhpjsx/downloadAlreadyApprovalYearAudit", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/xdgl/pjsxspjl/camsZcsxGrpjsxxxSpjl/downloadAlreadyApprovalYearAudit2", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/xdgl/pjsxspjl/camsZcsxGrpjsxxxSpjl/downloadAlreadyApprovalYearAuditZh", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/nh/nhbkbpy/bmddc", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/qhywxx/qhywxx/mxdc", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/zbmx/zbmxSet/mxdc", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/qhywxx/qhywxx/wktmxdc", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/xdgl/grkhpjsx/downloadNotApprovalYearAudit", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/xdgl/pjsxspjl/camsZcsxGrpjsxxxSpjl/downloadAlreadyApprovalYearAudit", "anon");
		filterChainDefinitionMap.put("/xdgl/grkhpjsx/downloadAlreadyApprovalYearAuditParams", "anon");
		filterChainDefinitionMap.put("/xdgl/grkhpjsx/farmerRankInfo", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/xdgl/nhxe/nhxe/modelResult", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/xdgl/nhxe/nhxe/getYwhywxxResult", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/sys/randomImage/**", "anon"); //登录验证码接口排除
		filterChainDefinitionMap.put("/sys/login", "anon"); //登录接口排除
		filterChainDefinitionMap.put("/sys/toYdyxpt", "anon"); //登录接口排除
		filterChainDefinitionMap.put("/sys/getLogincs", "anon"); //登录接口排除
		filterChainDefinitionMap.put("/sys/mLogin", "anon"); //登录接口排除
		filterChainDefinitionMap.put("/yxdyglmain/yxdyglMain/download", "anon"); //登录接口排除
		filterChainDefinitionMap.put("/yxdyglmain/yxdyglMain/resetMap", "anon"); //登录接口排除



		filterChainDefinitionMap.put("/sys/surebet", "anon");//
		filterChainDefinitionMap.put("/sys/logout", "anon"); //登出接口排除
		filterChainDefinitionMap.put("/sys/dict/getDictItems/**", "anon"); //数据字段
		filterChainDefinitionMap.put("/sys/getEncryptedString", "anon"); //获取加密串
		filterChainDefinitionMap.put("/sys/sms", "anon");//短信验证码
		filterChainDefinitionMap.put("/sys/phoneLogin", "anon");//手机登录
		filterChainDefinitionMap.put("/sys/getValidatePassWordRule", "anon");//获取校验用户密码的规则
		filterChainDefinitionMap.put("/sys/user/checkOnlyUser", "anon");//校验用户是否存在
		filterChainDefinitionMap.put("/sys/user/resetPassword", "anon");//平板校验用户是否一次登录强制修改密码
		filterChainDefinitionMap.put("/sys/user/updatePassword", "anon");//PC校验用户是否一次登录强制修改密码
		filterChainDefinitionMap.put("/sys/user/register", "anon");//用户注册
		filterChainDefinitionMap.put("/sys/user/querySysUser", "anon");//根据手机号获取用户信息
		filterChainDefinitionMap.put("/sys/user/phoneVerification", "anon");//用户忘记密码验证手机号
		filterChainDefinitionMap.put("/sys/user/passwordChange", "anon");//用户更改密码
		filterChainDefinitionMap.put("/auth/2step-code", "anon");//登录验证码
		filterChainDefinitionMap.put("/sys/common/static/**", "anon");//图片预览 &下载文件不限制token
		filterChainDefinitionMap.put("/sys/common/static/png/**", "anon");//图片预览 &下载文件不限制token
		//filterChainDefinitionMap.put("/sys/common/view/**", "anon");//图片预览不限制token
		filterChainDefinitionMap.put("/sys/common/download/**", "anon");//文件下载不限制token
		filterChainDefinitionMap.put("/sys/common/upload/**", "anon");//文件上传不限制token
		filterChainDefinitionMap.put("/appVersion/queryLatest", "anon");//app最新版本
		filterChainDefinitionMap.put("/sys/common/pdf/**", "anon");//pdf预览
		filterChainDefinitionMap.put("/onlinePreview/**", "anon");//预览
		filterChainDefinitionMap.put("/generic/**", "anon");//pdf预览需要文件
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/doc.html", "anon");
		filterChainDefinitionMap.put("/**/*.js", "anon");
		filterChainDefinitionMap.put("/**/*.css", "anon");
		filterChainDefinitionMap.put("/**/*.html", "anon");
		filterChainDefinitionMap.put("/**/*.svg", "anon");
		filterChainDefinitionMap.put("/**/*.pdf", "anon");
		filterChainDefinitionMap.put("/**/*.jpg", "anon");
		filterChainDefinitionMap.put("/**/*.png", "anon");
		filterChainDefinitionMap.put("/**/*.ico", "anon");

		filterChainDefinitionMap.put("/padjson/**", "anon");
		filterChainDefinitionMap.put("/padjson/login", "anon");
		filterChainDefinitionMap.put("/padjson/userinfo", "anon");
		filterChainDefinitionMap.put("/padjson/tablelist", "anon");

		// update-begin--Author:sunjianlei Date:20190813 for：排除字体格式的后缀
		filterChainDefinitionMap.put("/**/*.ttf", "anon");
		filterChainDefinitionMap.put("/**/*.woff", "anon");
		filterChainDefinitionMap.put("/**/*.woff2", "anon");
		// update-begin--Author:sunjianlei Date:20190813 for：排除字体格式的后缀

		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger**/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/v2/**", "anon");


		//性能监控，放开排除会存在安全漏洞泄露TOEKN（durid连接池也有）
		//filterChainDefinitionMap.put("/actuator/**", "anon");

		//测试示例
		filterChainDefinitionMap.put("/test/jeecgDemo/html", "anon"); //模板页面
		filterChainDefinitionMap.put("/test/jeecgDemo/redis/**", "anon"); //redis测试

		//排除Online请求
		filterChainDefinitionMap.put("/auto/cgform/**", "anon");

		//websocket排除
		filterChainDefinitionMap.put("/websocket/**", "anon");

		//大屏设计器排除
		filterChainDefinitionMap.put("/big/screen/**", "anon");
        //工作流cmms/editor-app
		filterChainDefinitionMap.put("/editor-app/**", "anon");
		filterChainDefinitionMap.put("/act/model/**", "anon");
		filterChainDefinitionMap.put("/act/editor/**", "anon");
		filterChainDefinitionMap.put("/actModel/export/**", "anon");
		filterChainDefinitionMap.put("/actProcess/export/**", "anon");
		filterChainDefinitionMap.put("/actModel/deployByFile/**", "anon");
		filterChainDefinitionMap.put("/actProcess/getHighlightImg/**", "anon");


		//年审导入
		filterChainDefinitionMap.put("/ns/camsNhpjsxNsb/reset", "anon");
		filterChainDefinitionMap.put("/ns/camsNhpjsxNsb/drnsb", "anon");
		filterChainDefinitionMap.put("/ns/camsNhpjsxNsb/drnsb2", "anon");
		filterChainDefinitionMap.put("/ns/camsNhpjsxNsb/drnsb0", "anon");
		filterChainDefinitionMap.put("/ns/camsNhpjsxNsb/drnsb3", "anon");
		filterChainDefinitionMap.put("/ns/camsNhpjsxNsb/nsbcw", "anon");



		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
		filterChainDefinitionMap.put("/**", "jwt");

		// 未授权界面返回JSON
		shiroFilterFactoryBean.setUnauthorizedUrl("/sys/common/403");
		shiroFilterFactoryBean.setLoginUrl("/sys/common/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean("securityManager")
	public DefaultWebSecurityManager securityManager(ShiroRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);

		/*
		 * 关闭shiro自带的session，详情见文档
		 * http://shiro.apache.org/session-management.html#SessionManagement-
		 * StatelessApplications%28Sessionless%29
		 */
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);
        //自定义缓存实现,使用redis
        securityManager.setCacheManager(redisCacheManager());
		return securityManager;
	}

	/**
	 * 下面的代码是添加注解支持
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager redisCacheManager() {
        log.info("===============(1)创建缓存管理器RedisCacheManager");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
        redisCacheManager.setPrincipalIdFieldName("id");
        //用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        log.info("===============(2)创建RedisManager,连接Redis..URL= " + host + ":" + port);
        RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(oConvertUtils.getInt(port));
		redisManager.setTimeout(0);
        if (!StringUtils.isEmpty(redisPassword)) {
            redisManager.setPassword(redisPassword);
        }
        return redisManager;
    }

}
