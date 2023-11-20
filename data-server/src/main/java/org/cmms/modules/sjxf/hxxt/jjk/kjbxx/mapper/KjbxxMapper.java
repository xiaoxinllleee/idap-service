package org.cmms.modules.sjxf.hxxt.jjk.kjbxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 卡基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
public interface KjbxxMapper extends BaseMapper<Kjbxx> {

    List<Kjbxx> getBmkxxHive(@Param("zjhm") String zjhm);

}
