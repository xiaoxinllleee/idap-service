package org.cmms.modules.khpjsx.pjsjxsjxarea.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khpjsx.pjsjxsjxarea.entity.PjsxSjxArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 区域数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-17
 * @Version: V1.0
 */
public interface PjsxSjxAreaMapper extends BaseMapper<PjsxSjxArea> {

    PjsxSjxArea querySjxid(Map<String,String> sql);

}
