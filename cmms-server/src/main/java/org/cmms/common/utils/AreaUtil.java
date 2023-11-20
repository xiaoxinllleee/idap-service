package org.cmms.common.utils;

import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.shiro.vo.DefContants;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.service.IEjyxdyglService;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.service.ISjyxdyglService;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import org.cmms.modules.yxdygl.yjyxdygl.service.IYjyxdyglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AreaUtil {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private IYjyxdyglService yjyxdyglService;
    @Autowired
    private IEjyxdyglService ejyxdyglService;
    @Autowired
    private ISjyxdyglService sjyxdyglService;

    /**
     * 保存用户一级营销单元权限MAP数据
     * @param request
     * @return
     */
    public Boolean yjyxdyqxSave(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        if(oConvertUtils.isEmpty(token)) {
            return false;
        }
        String username = JwtUtil.getUsername(token);
        yjyxdyqxSave(username);
        return true;
    }

    /**
     * 保存用户一级营销单元权限MAP数据
     * @param username
     * @return
     */
    public Boolean yjyxdyqxSave(String username) {
        List<Yjyxdygl> yjyxdyglList = yjyxdyglService.queryDataByUser(username);
        HashMap<String, Object> yjyxdyglMap = new HashMap<String, Object>();
        for (Yjyxdygl yjyxdygl : yjyxdyglList) {
            yjyxdyglMap.put(yjyxdygl.getDybh(), yjyxdygl.getDymc());
        }
        return redisUtil.hmset(CommonConstant.PREFIX_USER_YJYXDY + username, yjyxdyglMap);
    }

    /**
     * 获取用户对应的一级营销单元权限MAP
     * @return
     */
    public Map<Object, Object> getYjyxdyqx(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        String username = JwtUtil.getUsername(token);
        Map<Object, Object> map = redisUtil.hmget(CommonConstant.PREFIX_USER_YJYXDY + username);
        return map;
    }

    /**
     * 保存用户二级营销单元权限MAP数据
     * @param request
     * @return
     */
    public Boolean ejyxdyqxSave(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        if(oConvertUtils.isEmpty(token)) {
            return false;
        }
        String username = JwtUtil.getUsername(token);
        ejyxdyqxSave(username);
        return true;
    }

    /**
     * 保存用户二级营销单元权限MAP数据
     * @param username
     * @return
     */
    public Boolean ejyxdyqxSave(String username) {
        List<Ejyxdygl> ejyxdyglList = ejyxdyglService.queryDataByUser(username);
        HashMap<String, Object> ejyxdyglMap = new HashMap<String, Object>();
        for (Ejyxdygl ejyxdygl : ejyxdyglList) {
            ejyxdyglMap.put(ejyxdygl.getDybh(), ejyxdygl.getDymc());
        }
        return redisUtil.hmset(CommonConstant.PREFIX_USER_EJYXDY + username, ejyxdyglMap);
    }

    /**
     * 获取用户对应的二级营销单元权限MAP
     * @return
     */
    public Map<Object, Object> getEjyxdyqx(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        String username = JwtUtil.getUsername(token);
        Map<Object, Object> map = redisUtil.hmget(CommonConstant.PREFIX_USER_EJYXDY + username);
        return map;
    }

    /**
     * 保存用户三级营销单元权限MAP数据
     * @param request
     * @return
     */
    public Boolean sjyxdyqxSave(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        if(oConvertUtils.isEmpty(token)) {
            return false;
        }
        String username = JwtUtil.getUsername(token);
        sjyxdyqxSave(username);
        return true;
    }

    /**
     * 保存用户三级营销单元权限MAP数据
     * @param username
     * @return
     */
    public Boolean sjyxdyqxSave(String username) {
        List<Sjyxdygl> sjyxdyglList = sjyxdyglService.queryDataByUser(username);
        HashMap<String, Object> sjyxdyglMap = new HashMap<String, Object>();
        for (Sjyxdygl sjyxdygl : sjyxdyglList) {
            sjyxdyglMap.put(sjyxdygl.getDybh(), sjyxdygl.getDymc());
        }
        return redisUtil.hmset(CommonConstant.PREFIX_USER_SJYXDY + username, sjyxdyglMap);
    }

    /**
     * 获取用户对应的三级营销单元权限MAP
     * @return
     */
    public Map<Object, Object> getSjyxdyqx(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        String username = JwtUtil.getUsername(token);
        Map<Object, Object> map = redisUtil.hmget(CommonConstant.PREFIX_USER_SJYXDY + username);
        return map;
    }
}
