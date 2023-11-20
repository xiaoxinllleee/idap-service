package org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.entity.Dkyebmxqktj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 新增不良贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-23
 * @Version: V1.0
 */
public interface DkyebmxqktjMapper extends BaseMapper<Dkyebmxqktj> {
    String getByName(String ygxm);}
