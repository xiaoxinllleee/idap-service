package org.cmms.modules.pad.nhxxgl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.nhxxgl.entity.YxglKhhfxxb;

import java.util.List;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
public interface YxglKhhfxxbMapper extends BaseMapper<YxglKhhfxxb> {

    List<YxglKhhfxxb> queryHfxxByZjhm(String mainId);

}
