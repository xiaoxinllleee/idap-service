package org.cmms.modules.khgl.khzhfx.khzhhz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khzhfx.khzhhz.entity.Khjlhz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户转化客户经理汇总
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
public interface KhjlhzMapper extends BaseMapper<Khjlhz> {
    public void extract(String ksrq,String jsrq);
}
