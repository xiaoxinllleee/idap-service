package org.cmms.modules.khgl.khglgx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khglgx.entity.Vglgxmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 关联关系明细
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface VglgxmxMapper extends BaseMapper<Vglgxmx> {
    public  int queryCountBykhjl(String khjl);
}
