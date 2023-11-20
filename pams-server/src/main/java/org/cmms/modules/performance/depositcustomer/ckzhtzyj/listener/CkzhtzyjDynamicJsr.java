package org.cmms.modules.performance.depositcustomer.ckzhtzyj.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.activiti.dynamic.DynamicHandler;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.entity.Ckzhspxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.service.ICkzhspxxService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;

import java.util.List;
import java.util.Map;

public class CkzhtzyjDynamicJsr extends DynamicHandler {
    @Override
    public List<SysUser> getDynamicUser(Map<String, Object> paramMap) {
        String tableId = (String)paramMap.get("tableId");
        if (StringUtils.isNotEmpty(tableId)) {
            //获取接收人工号
            ICkzhspxxService ckzhspxxService = SpringContextUtils.getBean(ICkzhspxxService.class);
            ISysUserService sysUserService = SpringContextUtils.getBean(ISysUserService.class);
            QueryWrapper<Ckzhspxx> queryWrapper = new QueryWrapper<Ckzhspxx>();
            queryWrapper.eq("table_id", tableId);
            List<Ckzhspxx> ckzhspxxList = ckzhspxxService.list(queryWrapper);
            if (!ckzhspxxList.isEmpty()) {
                Ckzhspxx ckzhspxx = ckzhspxxList.get(0);
                String jsrgh = ckzhspxx.getTzr();
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
