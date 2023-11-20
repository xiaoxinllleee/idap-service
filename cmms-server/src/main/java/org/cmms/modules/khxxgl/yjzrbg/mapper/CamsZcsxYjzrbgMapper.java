package org.cmms.modules.khxxgl.yjzrbg.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.yjzrbg.entity.CamsZcsxYjzrbg;
import org.cmms.modules.khxxgl.yjzrbg.entity.YjzrbgScore;

/**
 * @Description: 一键准入报告
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
public interface CamsZcsxYjzrbgMapper extends BaseMapper<CamsZcsxYjzrbg> {

    void updateScore(@Param("dao") YjzrbgScore yjzrbgScore);

    void updateCsed(@Param("hzed") BigDecimal hzed,@Param("zjhm") String zjhm);

    void mergeNhxx();
}
