package org.cmms.modules.system.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.IPUtils;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.modules.system.entity.SysLogMx;
import org.cmms.modules.system.entity.SysLoginInfoLog;
import org.cmms.modules.system.mapper.SysLoginInfoLogMapper;
import org.cmms.modules.system.service.ISysLoginInfoLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description: 登录信息日志表
 * @Author: jeecg-boot
 * @Date: 2023-06-25
 * @Version: V1.0
 */
@Service
public class SysLoginInfoLogServiceImpl extends ServiceImpl<SysLoginInfoLogMapper, SysLoginInfoLog> implements ISysLoginInfoLogService {

    @Resource
    private SysLoginInfoLogMapper sysLoginInfoLogMapper;

    private Date date = null;


    @Override
    public void addLoginInfoLog(String username, String realname, String lx) {
        SysLoginInfoLog sysLoginInfoLog = new SysLoginInfoLog();
        QueryWrapper<SysLoginInfoLog> wrapper = new QueryWrapper<>();
        DateTime rq = DateUtil.beginOfDay(new Date());
        wrapper.eq("rq", rq);
        wrapper.eq("username", username);
        wrapper.isNull("login_time");
        wrapper.eq("login_device", lx);
        SysLoginInfoLog sysLoginInfoLog1 = sysLoginInfoLogMapper.selectOne(wrapper);
        if (sysLoginInfoLog1 == null) {
            try {
                //获取request
                HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
                //设置IP地址
                String ip=IPUtils.getIpAddr(request);
                sysLoginInfoLog.setIp(ip);
            } catch (Exception e) {
                sysLoginInfoLog.setIp("127.0.0.1");
            }
            sysLoginInfoLog.setRq(rq);
            sysLoginInfoLog.setLoginDevice(lx);
            sysLoginInfoLog.setUsername(username);
            sysLoginInfoLog.setRealname(realname);
            sysLoginInfoLog.setLoginTime(new Date());
            this.date=new Date();
            //保存系统日志
            sysLoginInfoLogMapper.insert(sysLoginInfoLog);
        }
    }

    @Override
    public void addLogoutInfoLog(String username, String lx, Date exit) {
        Date date = getDate();
        //获取登录时间
        String format = DateUtil.format(date,"yyyy-MM-dd HH:mm:ss");
        Date parse = DateUtil.parse(format);
        //日期开始日期
        Date rq = DateUtil.beginOfDay(exit);

        QueryWrapper<SysLoginInfoLog> wrapper = new QueryWrapper<>();
        wrapper.eq("rq", rq);
        wrapper.eq("login_time",parse);
        wrapper.eq("username", username);
        wrapper.eq("login_device", lx);
        SysLoginInfoLog sysLogMx = sysLoginInfoLogMapper.selectOne(wrapper);
        if (sysLogMx != null) {
            long createTime = sysLogMx.getLoginTime().getTime();//获取创建时间的时间戳
            long currentTime = exit.getTime();//获取当前时间的时间戳
            long diff = (currentTime - createTime) / 1000 / 60;//获取两个时间相差的分钟
            sysLogMx.setExitTime(exit);
            sysLogMx.setLoginFrequently(String.valueOf(diff));
            //保存系统日志
            sysLoginInfoLogMapper.update(sysLogMx, wrapper);
        }
    }

    public Date getDate() {
        return date;
    }

}
