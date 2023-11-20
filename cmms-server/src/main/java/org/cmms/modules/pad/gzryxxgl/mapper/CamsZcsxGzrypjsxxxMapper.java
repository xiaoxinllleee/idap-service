package org.cmms.modules.pad.gzryxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzrypjsxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 公职人员评级授信表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
public interface CamsZcsxGzrypjsxxxMapper extends BaseMapper<CamsZcsxGzrypjsxxx> {

    public CamsZcsxGzrypjsxxx getByGzryid(String gzryid);

}
