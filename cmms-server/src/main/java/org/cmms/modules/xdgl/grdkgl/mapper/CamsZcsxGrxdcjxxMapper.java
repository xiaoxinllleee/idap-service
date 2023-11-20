package org.cmms.modules.xdgl.grdkgl.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.word.entity.GrdkGtDTO;
import org.cmms.modules.word.entity.GrdkMtDTO;
import org.cmms.modules.xdgl.grdkgl.entity.CamsZcsxGrxdcjxx;
import org.springframework.stereotype.Component;

/**
 * @Description: 个人信贷信息采集表
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Component
public interface CamsZcsxGrxdcjxxMapper extends BaseMapper<CamsZcsxGrxdcjxx> {

    int updateMt(@Param("dao") GrdkMtDTO grdkMtDTO);
    int updateGt(@Param("dao") GrdkGtDTO grdkGtDTO);
}
