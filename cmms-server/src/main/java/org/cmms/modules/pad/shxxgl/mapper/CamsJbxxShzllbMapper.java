package org.cmms.modules.pad.shxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.shxxgl.entity.CamsJbxxShzllb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商户资料列表
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface CamsJbxxShzllbMapper extends BaseMapper<CamsJbxxShzllb> {
    public List<CamsJbxxShzllb> getByShid(String shid);
}
