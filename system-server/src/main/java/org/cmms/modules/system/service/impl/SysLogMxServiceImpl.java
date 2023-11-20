package org.cmms.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.IPUtils;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.system.entity.SysLog;
import org.cmms.modules.system.entity.SysLogMx;
import org.cmms.modules.system.mapper.SysLogMapper;
import org.cmms.modules.system.mapper.SysLogMxMapper;
import org.cmms.modules.system.service.ISysLogMxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import javax.xml.namespace.QName;
import java.util.Date;
import java.util.List;

/**
 * @Description: 系统日志明细表
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
@Service
public class SysLogMxServiceImpl extends ServiceImpl<SysLogMxMapper, SysLogMx> implements ISysLogMxService {

    @Resource
    private SysLogMxMapper sysLogMxMapper;



    @Override
    public void addLoginMxLog(String userid,String username,String lx) {
        SysLogMx sysLogMx = new SysLogMx();
        QueryWrapper<SysLogMx> wrapper = new QueryWrapper<>();
        DateTime rq = DateUtil.beginOfDay(new Date());
        wrapper.eq("rq",rq);
        wrapper.eq("userid", userid);
        wrapper.eq("login_device",lx);
        List<SysLogMx> sysLogMx1 = sysLogMxMapper.selectList(wrapper);
            if (CollUtil.isEmpty(sysLogMx1)){
                try {
                    //获取request
                    HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
                    //设置IP地址
                    sysLogMx.setIp(IPUtils.getIpAddr(request));
                } catch (Exception e) {
                    sysLogMx.setIp("127.0.0.1");
                }
                sysLogMx.setRq(rq);
                sysLogMx.setLoginDevice(lx);
                sysLogMx.setUserid(userid);
                sysLogMx.setCreateBy(username);
                sysLogMx.setUsername(username);
                sysLogMx.setId(UUIDGenerator.generate());
                sysLogMx.setNumberOfLogins(1L);
                sysLogMx.setCreateTime(new Date());
                //保存系统日志
                sysLogMxMapper.insert(sysLogMx);
            }else {
                //sysLogMx1有一样的数据从第二条数据遍历删除，保留一条数据
                for (int i = 1; i < sysLogMx1.size(); i++) {
                    sysLogMxMapper.deleteById(sysLogMx1.get(i).getId());
                }
                //删除完之后第一条数据记录次数
                if (sysLogMx1.get(0).getNumberOfLogins() !=null){
                    Long count=sysLogMx1.get(0).getNumberOfLogins()+1;
                    sysLogMx.setNumberOfLogins(count);
                }
                try {
                    //获取request
                    HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
                    //设置IP地址
                    sysLogMx.setIp(IPUtils.getIpAddr(request));
                } catch (Exception e) {
                    sysLogMx.setIp("127.0.0.1");
                }
                sysLogMx.setCreateTime(new Date());
                sysLogMxMapper.update(sysLogMx,wrapper);
            }

    }

    @Override
    public void addLogoutMxLog(String userid,String lx,Date exit) {
        Date rq = DateUtil.beginOfDay(exit);
        QueryWrapper<SysLogMx> wrapper = new QueryWrapper<>();
        wrapper.eq("rq",rq);
        wrapper.eq("userid",userid);
        wrapper.eq("login_device",lx);
        SysLogMx sysLogMx = sysLogMxMapper.selectOne(wrapper);
        if (sysLogMx != null){
            long createTime =sysLogMx.getCreateTime().getTime();//获取创建时间的时间戳
            long currentTime =exit.getTime();//获取当前时间的时间戳
            long diff=(currentTime-createTime)/1000/60;//获取两个时间相差的分钟
            sysLogMx.setExitTime(exit);
            sysLogMx.setLoginFrequently(String.valueOf(diff));
            //保存系统日志
            sysLogMxMapper.update(sysLogMx,wrapper);
        }
    }

}
