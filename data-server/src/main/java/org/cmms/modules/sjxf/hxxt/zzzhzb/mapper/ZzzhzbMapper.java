package org.cmms.modules.sjxf.hxxt.zzzhzb.mapper;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.hxxt.zzzhzb.entity.Zzzhzb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 总账账户主表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
public interface ZzzhzbMapper extends BaseMapper<Zzzhzb> {

    Zzzhzb queryZzzhzbOracle(@Param("ckzh") String ckzh);

    Zzzhzb queryZzzhzbHive(@Param("ckzh") String ckzh);

}
