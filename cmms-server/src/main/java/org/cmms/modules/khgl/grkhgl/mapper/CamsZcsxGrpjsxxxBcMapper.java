package org.cmms.modules.khgl.grkhgl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxxBc;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

/**
 * @Description: 个人客户评级授信补充
 * @Author: jeecg-boot
 * @Date:   2020-07-13
 * @Version: V1.0
 */
@Component
public interface CamsZcsxGrpjsxxxBcMapper extends BaseMapper<CamsZcsxGrpjsxxxBc> {
    int updateAiResult(@Param("xtpddf")int xtpddf,@Param("xtpddj")String xtpddj,@Param("xtpded")int xtpded,@Param("xtpdyy")String yy,@Param("zjhm")String zjhm);
}
