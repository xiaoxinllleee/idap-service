package org.cmms.modules.performance.depositcustomer.ckkhghyj.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.activiti.dynamic.DynamicHandler;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.entity.Ckkhspxx;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.service.ICkkhspxxService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;

import java.util.List;
import java.util.Map;

public class CkkhghyjDynamicJsr extends DynamicHandler {
    @Override
    public List<SysUser> getDynamicUser(Map<String, Object> paramMap) {
        String tableId = (String)paramMap.get("tableId");
        if (StringUtils.isNotEmpty(tableId)) {
            //获取接收人工号
            ICkkhspxxService ckkhspxxService = SpringContextUtils.getBean(ICkkhspxxService.class);
            ISysUserService sysUserService = SpringContextUtils.getBean(ISysUserService.class);
            QueryWrapper<Ckkhspxx> queryWrapper = new QueryWrapper<Ckkhspxx>();
            queryWrapper.eq("table_id", tableId);
            List<Ckkhspxx> ckkhspxxList = ckkhspxxService.list(queryWrapper);
            if (!ckkhspxxList.isEmpty()) {
                Ckkhspxx ckkhspxx = ckkhspxxList.get(0);
                String jsrgh = ckkhspxx.getGhr();
                if (StringUtils.isNotEmpty(jsrgh)) {
                    QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
                    sysUserQueryWrapper.eq("work_no", jsrgh);
                    List<SysUser> sysUserList = sysUserService.list(sysUserQueryWrapper);
                    return sysUserList;
                }
            }
        }
        return null;
    }
}
