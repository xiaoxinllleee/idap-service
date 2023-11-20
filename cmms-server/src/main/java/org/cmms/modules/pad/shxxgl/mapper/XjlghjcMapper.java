package org.cmms.modules.pad.shxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.shxxgl.entity.Xjlghjc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 现金流归行检测
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
public interface XjlghjcMapper extends BaseMapper<Xjlghjc> {

   public List<Xjlghjc> queryXjlGhjc(String hhbm);

}
