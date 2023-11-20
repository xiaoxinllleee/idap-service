package org.cmms.modules.tjfx.grcdksjmx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.grcdksjmx.entity.KhglGrcdsjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人存贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2020-11-28
 * @Version: V1.0
 */
public interface KhglGrcdsjmxMapper extends BaseMapper<KhglGrcdsjmx> {

    public void init(Map<String,Object> sql);

    public List<Map> getgrjynck(String zjhm);

    public String getCsz(String csbm);

}
