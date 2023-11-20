package org.cmms.modules.performance.loancustomer.dkkhyj.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.activiti.dynamic.DynamicHandler;
import org.cmms.modules.performance.loancustomer.dkkhspxx.entity.Dkkhspxx;
import org.cmms.modules.performance.loancustomer.dkkhspxx.service.IDkkhspxxService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;

import java.util.List;
import java.util.Map;

public class DkkhyjDynamicJsr extends DynamicHandler {
    @Override
    public List<SysUser> getDynamicUser(Map<String, Object> paramMap) {
        String tableId = (String)paramMap.get("tableId");
        if (StringUtils.isNotEmpty(tableId)) {
            //获取接收人工号
            IDkkhspxxService dkkhspxxService = SpringContextUtils.getBean(IDkkhspxxService.class);
            ISysUserService sysUserService = SpringContextUtils.getBean(ISysUserService.class);
            QueryWrapper<Dkkhspxx> queryWrapper = new QueryWrapper<Dkkhspxx>();
            queryWrapper.eq("table_id", tableId);
            List<Dkkhspxx> dkkhspxxList = dkkhspxxService.list(queryWrapper);
            if (!dkkhspxxList.isEmpty()) {
                Dkkhspxx dkkhspxx = dkkhspxxList.get(0);
                String jsrgh = dkkhspxx.getGhr();
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
