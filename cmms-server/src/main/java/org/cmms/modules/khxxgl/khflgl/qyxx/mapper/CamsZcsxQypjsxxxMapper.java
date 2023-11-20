package org.cmms.modules.khxxgl.khflgl.qyxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsZcsxQypjsxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShpjsxxx;

/**
 * @Description: 企业评级授信信息
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
public interface CamsZcsxQypjsxxxMapper extends BaseMapper<CamsZcsxQypjsxxx> {
    public CamsZcsxQypjsxxx getByQyid(String qyid);
}
