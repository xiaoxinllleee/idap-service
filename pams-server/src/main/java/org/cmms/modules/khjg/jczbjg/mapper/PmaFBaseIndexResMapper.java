package org.cmms.modules.khjg.jczbjg.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khjg.jczbjg.entity.PmaFBaseIndexRes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 基础指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-10
 * @Version: V1.0
 */
@Component
public interface PmaFBaseIndexResMapper extends BaseMapper<PmaFBaseIndexRes> {

    List<PmaFBaseIndexRes> getFormulaList(@Param("statDate") String statDate,@Param("indexList") List<String> indexList);
}
