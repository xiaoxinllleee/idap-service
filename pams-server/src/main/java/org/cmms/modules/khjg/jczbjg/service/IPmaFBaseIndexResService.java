package org.cmms.modules.khjg.jczbjg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khjg.jczbjg.entity.PmaFBaseIndexRes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 基础指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-10
 * @Version: V1.0
 */
public interface IPmaFBaseIndexResService extends IService<PmaFBaseIndexRes> {
    List<PmaFBaseIndexRes> getFormulaList(String statDate, List<String> indexList);
}
