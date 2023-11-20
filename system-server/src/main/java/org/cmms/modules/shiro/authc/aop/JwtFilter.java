package org.cmms.modules.shiro.authc.aop;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.license.LicenseVerify;
import org.cmms.modules.shiro.authc.JwtToken;
import org.cmms.modules.shiro.vo.DefContants;
import org.cmms.modules.system.entity.SysPermission;
import org.cmms.modules.system.entity.SysSubSystem;
import org.cmms.modules.system.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 鉴权登录拦截器
 * @Author: Scott
 * @Date: 2018/10/7
 **/
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
	private boolean brokenAccessCheck;

	private String corsAllowOrigin;

	public JwtFilter() {

	}

	public JwtFilter(boolean brokenAccessCheck, String corsAllowOrigin) {
		this.brokenAccessCheck = brokenAccessCheck;
		this.corsAllowOrigin = corsAllowOrigin;
	}
	/**
	 * 执行登录认证
	 *
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		try {

			executeLogin(request, response);
		} catch (Exception e) {
			throw new AuthenticationException("Token失效，请重新登录", e);
		}
		if (brokenAccessCheck) {
			checkBrokenAccess(request, response);
		}
//		checkLicense(request, response);
		return true;
	}

	/**
	 *
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String servletPath = httpServletRequest.getServletPath();
		if("/sys/login".equalsIgnoreCase(servletPath)) {
			return true;
		}
		String token = httpServletRequest.getHeader(DefContants.X_ACCESS_TOKEN);

		JwtToken jwtToken = new JwtToken(token);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
		getSubject(request, response).login(jwtToken);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}

	private void checkLicense(ServletRequest request, ServletResponse response) {
		LicenseVerify licenseVerify = new LicenseVerify();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String servletPath = httpServletRequest.getServletPath();
		if(!"/sys/login".equalsIgnoreCase(servletPath)) {
			return;
		}
		//校验证书是否有效
		boolean verifyResult = licenseVerify.verify();

		if(verifyResult){
			return;
		}else{
			throw new AuthenticationException("您的证书无效，请核查服务器是否取得授权或重新申请证书！");
		}
	}

	private void checkBrokenAccess(ServletRequest request, ServletResponse response) {
		log.info("BrokenAccessFilter");
		ISysPermissionService sysPermissionService = SpringContextUtils.getBean(ISysPermissionService.class);
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader(DefContants.X_ACCESS_TOKEN);
		String username = JwtUtil.getUsername(token);
		List<SysSubSystem> subSystemList = sysPermissionService.querySubSystemByUser(username);
		String subSystemListStr = subSystemList.stream().map(SysSubSystem::getId).collect(Collectors.joining(","));
		List<SysPermission> metaList = sysPermissionService.queryAllPermissionByUser(username, subSystemListStr);
		//获取所有的菜单
		//获取配置了后台地址的菜单，如果配置了地址，说明需要权限校验
		List<SysPermission> permissionList = sysPermissionService.list();
		List<String> urlServerList = permissionList.stream().filter(item -> StringUtils.isNotEmpty(item.getUrlServer())).map(SysPermission::getUrlServer).collect(Collectors.toList());
		//获取本人权限菜单
		String servletPath = httpServletRequest.getServletPath();
		List<String> urlServers = metaList.stream().filter(item -> StringUtils.isNotEmpty(item.getUrlServer())).map(SysPermission::getUrlServer).collect(Collectors.toList());
		boolean needCheck = false;
		boolean allow = false;
		for (int i = 0; i < urlServerList.size(); i++) {
			String urlServer = urlServerList.get(i);
			if(servletPath.startsWith(urlServer)) {
				needCheck = true;
				break;
			}
		}
		if (needCheck) {
			for (int i = 0; i < urlServers.size(); i++) {
				String urlServer = urlServers.get(i);
				if(servletPath.startsWith(urlServer)) {
					allow = true;
					break;
				}
			}
		} else {
			allow = true;
		}

		if (!allow) {
			throw new AuthenticationException("权限校验失败");
		}
	}

	/**
	 * 对跨域提供支持
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String userAgent = httpServletRequest.getHeader("User-Agent");
		Enumeration<String> headNames = httpServletRequest.getHeaderNames();
		//log.info("Origin：" + httpServletRequest.getHeader("Origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
		boolean needCheckCors = false;
		if(StringUtils.isNotEmpty(corsAllowOrigin)) {
			while (headNames.hasMoreElements()) {
				String headName = headNames.nextElement();
				if("Origin".equalsIgnoreCase(headName)) {
					needCheckCors = true;
					break;
				}
			}
		}
		if (needCheckCors) {
			httpServletResponse.setHeader("Access-control-Allow-Origin", corsAllowOrigin);
			String requestOrigin = httpServletRequest.getHeader("Origin");
			String[] allowOriginList = corsAllowOrigin.split(",");
			boolean originOk = false;
			for(String allowOrigin : allowOriginList){
				if (allowOrigin.equalsIgnoreCase(requestOrigin)) {
					originOk = true;
					break;
				}
			}
			if (!originOk) {
				httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
				return false;
			}
		} else {
			httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
		}
		UserAgent ua = UserAgentUtil.parse(userAgent);
//		if (ua.isMobile()) {
//			String origin = httpServletRequest.getHeader("Origin");
//			if (!StringUtils.isEmpty(origin)) {
//				boolean allow = false;
//				for (String allowOrigin : CommonConstant.ALLOW_ORIGINS) {
//					if(origin.startsWith(allowOrigin)) {
//						allow = true;
//						break;
//					}
//				}
//				if (!allow) {
//					httpServletResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
//					return false;
//				}
//			}
//		}


		// 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
		if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpServletResponse.setStatus(HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}
}
