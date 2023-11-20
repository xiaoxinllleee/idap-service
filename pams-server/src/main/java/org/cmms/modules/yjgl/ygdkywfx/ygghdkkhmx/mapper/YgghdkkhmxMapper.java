package org.cmms.modules.yjgl.ygdkywfx.ygghdkkhmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygghdkkhmx.entity.Ygghdkkhmx;

/**
 * @Description: 员工管户贷款客户明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
public interface YgghdkkhmxMapper extends BaseMapper<Ygghdkkhmx> {
    void init(@Param("ksrq")String ksrq, @Param("jsrq")String jsrq, @Param("jgdm")String jgdm, @Param("yggh")String yggh);
}
