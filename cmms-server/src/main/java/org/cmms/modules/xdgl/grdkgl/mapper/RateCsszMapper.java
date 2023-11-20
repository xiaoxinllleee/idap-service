package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.RateCssz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: Rate_cssz
 * @Author: jeecg-boot
 * @Date:   2020-08-30
 * @Version: V1.0
 */
public interface RateCsszMapper extends BaseMapper<RateCssz> {

    public  List<RateCssz> querycssz ();
}
