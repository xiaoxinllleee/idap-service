package org.cmms.modules.khgl.grkhgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 个人客户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
@Component
public interface CamsZcsxGrpjsxxxMapper extends BaseMapper<CamsZcsxGrpjsxxx> {

    public CamsZcsxGrpjsxxx selectByMainId(String hhbm);
    public boolean deleteByMainId(@Param("zjhm")String zjhm);
    public void getYwgywxx(@Param("hhbm")String hhbm);
    
}


