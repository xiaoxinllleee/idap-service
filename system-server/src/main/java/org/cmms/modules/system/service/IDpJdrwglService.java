package org.cmms.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.system.entity.DpJdrwgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.entity.SysUser;

/**
 * @Description: 节点任务管理
 * @Author: jeecg-boot
 * @Date:   2021-01-08
 * @Version: V1.0
 */
public interface IDpJdrwglService extends IService<DpJdrwgl> {
    public IPage<DpJdrwgl> getJdrwByJdId(Page<DpJdrwgl> page, String jdid, String rwmc);
    public void extract(String spname,String ksrq,String jsrq,String rwid);
    public void updatezt(String tjrq,String rwid);
    public void updateBatchzt(String ksrq,String jsrq,String rwid);

}
