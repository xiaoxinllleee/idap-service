package org.cmms.modules.yxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxgl.entity.Khhf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户回访
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface KhhfMapper extends BaseMapper<Khhf> {

    public List<Khhf> selectByZjhm(String zjhm);

}
