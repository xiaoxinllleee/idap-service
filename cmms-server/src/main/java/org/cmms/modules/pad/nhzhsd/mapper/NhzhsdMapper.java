package org.cmms.modules.pad.nhzhsd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.nhzhsd.entity.Nhzhsd;

/**
 * @Description: 农户支行审定
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
public interface NhzhsdMapper extends BaseMapper<Nhzhsd> {
    public List<String> getWwcCzfpWg(@Param("khjl")String  khjl);
}
