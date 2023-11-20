package org.cmms.modules.dklldj.jbxxgl.zhrpcx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.entity.Rate_zhckrp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-28
 * @Version: V1.0
 */
public interface Rate_zhckrpMapper extends BaseMapper<Rate_zhckrp> {
    public void extract(Map<String,String> tj);
}
