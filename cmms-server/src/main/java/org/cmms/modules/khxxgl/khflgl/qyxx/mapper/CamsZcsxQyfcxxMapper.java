package org.cmms.modules.khxxgl.khflgl.qyxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsZcsxQyfcxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShfcxx;

/**
 * @Description: 企业房产信息
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
public interface CamsZcsxQyfcxxMapper extends BaseMapper<CamsZcsxQyfcxx> {
    public List<CamsZcsxQyfcxx> getByQyid(String qyid);
}
