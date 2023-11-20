package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhxqHmd;

import java.util.List;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
public interface NhxqHmdMapper extends BaseMapper<NhxqHmd> {
    public void init();
    //除了本人外的家庭关系情况
    List<NhxqHmd> getHByZjhm(@Param("zjhm") String zjhm);
    List<NhxqHmd> getByHnkd(@Param("khxxglHnkd") KhxxglHnkd khxxglHnkd,@Param("workNo")String workNo);
}
