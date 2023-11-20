package org.cmms.modules.khlc.khfajg.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.khfajg.entity.FakhygInfo;
import org.cmms.modules.khlc.khfajg.entity.PmaFEngineSchemeLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 引擎加工详细日志表
 * @Author: jeecg-boot
 * @Date:   2021-03-02
 * @Version: V1.0
 */
public interface IPmaFEngineSchemeLogService extends IService<PmaFEngineSchemeLog> {
    public List<FakhygInfo> getBySchemeIdAndType(String schemeId,String type,String date);
}
