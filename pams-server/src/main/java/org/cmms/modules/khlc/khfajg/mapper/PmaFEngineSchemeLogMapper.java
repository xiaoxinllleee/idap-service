package org.cmms.modules.khlc.khfajg.mapper;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.khfajg.entity.FakhygInfo;
import org.cmms.modules.khlc.khfajg.entity.PmaFEngineSchemeLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 引擎加工详细日志表
 * @Author: jeecg-boot
 * @Date:   2021-03-02
 * @Version: V1.0
 */
@Component
public interface PmaFEngineSchemeLogMapper extends BaseMapper<PmaFEngineSchemeLog> {
    public List<FakhygInfo> getBySchemeIdAndType(@Param("schemeId") String schemeId,@Param("type") String type
    ,@Param("oneDate")String oneDate);
}
