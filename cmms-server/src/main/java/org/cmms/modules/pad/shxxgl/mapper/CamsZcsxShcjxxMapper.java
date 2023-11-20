package org.cmms.modules.pad.shxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShcjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商户采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface CamsZcsxShcjxxMapper extends BaseMapper<CamsZcsxShcjxx> {
    public CamsZcsxShcjxx getByShid(String shid);
}
