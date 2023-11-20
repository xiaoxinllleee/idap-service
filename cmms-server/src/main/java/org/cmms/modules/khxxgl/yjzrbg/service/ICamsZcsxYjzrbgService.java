package org.cmms.modules.khxxgl.yjzrbg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.yjzrbg.entity.CamsZcsxYjzrbg;
import org.cmms.modules.khxxgl.yjzrbg.entity.YjzrbgScore;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 一键准入报告
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
public interface ICamsZcsxYjzrbgService extends IService<CamsZcsxYjzrbg> {

    void updateScore(YjzrbgScore yjzrbgScore);

    void updateCsed(BigDecimal hzed,String zjhm);

    void mergeNhxx();
}
