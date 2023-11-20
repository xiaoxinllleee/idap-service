package org.cmms.modules.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.cmms.common.aspect.annotation.PermissonSszh;
import org.cmms.common.system.api.ISysBaseAPI;
import org.cmms.common.system.util.JeecgDataAutorUtils;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.system.vo.SysPermissionDataRuleModel;
import org.cmms.common.system.vo.SysUserCacheInfo;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysPermissionDataRuleService;
import org.cmms.modules.system.service.ISysPermissionService;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class PermissonSszhAspect {
    @Autowired
    private ISysPermissionService sysPermissionService;

    @Autowired
    private ISysBaseAPI sysBaseAPI;


    @Pointcut("@annotation(org.cmms.common.aspect.annotation.PermissonSszh)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object arround(ProceedingJoinPoint point) throws  Throwable{
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        PermissonSszh pd = method.getAnnotation(PermissonSszh.class);
        String ruleColumn = pd.value();
        if(oConvertUtils.isNotEmpty(ruleColumn)) {
            String username = JwtUtil.getUserNameByToken(request);
            //1.通过注解属性ruleColumn
            List<SysPermissionDataRuleModel> dataRules = new ArrayList<SysPermissionDataRuleModel>();
            SysPermissionDataRuleModel sysPermissionDataRuleModel =new SysPermissionDataRuleModel();
            sysPermissionDataRuleModel.setRuleColumn(ruleColumn);
            sysPermissionDataRuleModel.setRuleValue("#{sysMultiOrgCode}");
            sysPermissionDataRuleModel.setRuleConditions("IN");
            sysPermissionDataRuleModel.setRuleName("所属支行");
            dataRules.add(sysPermissionDataRuleModel);
            if(dataRules!=null && dataRules.size()>0) {
                JeecgDataAutorUtils.installDataSearchConditon(request, dataRules);
                SysUserCacheInfo userinfo = sysBaseAPI.getCacheUser(username);
                JeecgDataAutorUtils.installUserInfo(request, userinfo);
            }
        }
        return  point.proceed();
    }

}
