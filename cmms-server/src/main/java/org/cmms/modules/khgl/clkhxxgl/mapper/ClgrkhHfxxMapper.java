package org.cmms.modules.khgl.clkhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.clkhxxgl.entity.ClgrkhHfxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yxgl.entity.Khhffjxx;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
public interface ClgrkhHfxxMapper extends BaseMapper<ClgrkhHfxx> {

    List<ClgrkhHfxx> queryHfxxByZjhm(String mainId);

    List<String> queryTodayHfxx(String yggh);
}
