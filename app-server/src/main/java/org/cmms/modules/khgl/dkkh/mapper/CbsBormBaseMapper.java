package org.cmms.modules.khgl.dkkh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2022-04-11
 * @Version: V1.0
 */
public interface CbsBormBaseMapper extends BaseMapper<CbsBormBase> {

    List<CbsBormBase> getCardNoIsNotNull(String zjhm);
}
