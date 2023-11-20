package org.cmms.modules.pad.nhczfp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.nhczfp.entity.Nhczfp;
import org.cmms.modules.pad.nhczfp.entity.NhczfpVo;

/**
 * @Description: 农户村组复评
 * @Author: jeecg-boot
 * @Date:   2023-03-27
 * @Version: V1.0
 */
public interface NhczfpMapper extends BaseMapper<Nhczfp> {
    public List<NhczfpVo> getYwcbkbpyList(@Param("wgbh")String wgbh);
}
