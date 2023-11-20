package org.cmms.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.entity.DpJdrwgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.SysUser;

/**
 * @Description: 节点任务管理
 * @Author: jeecg-boot
 * @Date:   2021-01-08
 * @Version: V1.0
 */
public interface DpJdrwglMapper extends BaseMapper<DpJdrwgl> {
    IPage<DpJdrwgl> getJdrwByJdId(Page page, @Param("jdid") String jdid, @Param("rwmc") String rwmc);
    public void extract(@Param("spname") String spname, @Param("ksrq")  String ksrq,@Param("jsrq")  String jsrq, @Param("rwid")  String rwid);
    public void updatezt(@Param("tjrq") String tjrq, @Param("rwid")  String rwid);
    public void updateBatchzt(@Param("ksrq") String ksrq,@Param("jsrq") String jsrq, @Param("rwid")  String rwid);

}
