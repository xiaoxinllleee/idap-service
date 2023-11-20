package org.cmms.modules.bigscreen.mapper;

import java.util.List;

import org.cmms.modules.bigscreen.entity.VDpYwhz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.bigscreen.entity.Xzzcsxgzjd;
import org.cmms.modules.bigscreen.entity.Xzzcsxgzjdcun;

/**
 * @Description: 大屏业务汇总视图
 * @Author: jeecg-boot
 * @Date:   2023-10-07
 * @Version: V1.0
 */
public interface VDpYwhzMapper extends BaseMapper<VDpYwhz> {

    List<Xzzcsxgzjd> getMaxList();
    List<Xzzcsxgzjdcun> getCunList(String wgbh);
}
