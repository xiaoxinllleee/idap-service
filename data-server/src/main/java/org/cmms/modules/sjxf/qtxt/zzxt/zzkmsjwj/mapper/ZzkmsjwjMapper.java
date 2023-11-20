package org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.entity.Zzkmsjwj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 总帐科目数据文件
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
public interface ZzkmsjwjMapper extends BaseMapper<Zzkmsjwj> {

    String getMaxDataDateImpala();

    // String getMaxDataDateOracle();

}
