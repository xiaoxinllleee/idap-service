package org.cmms.modules.yxgl.khhfjh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxgl.khhfjh.entity.YxglKhhfjh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户回访计划
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
public interface YxglKhhfjhMapper extends BaseMapper<YxglKhhfjh> {

    public void extract(Map<String,String>sql);

}
