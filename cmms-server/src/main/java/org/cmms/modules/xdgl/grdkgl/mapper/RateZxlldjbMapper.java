package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.RateZxlldjb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 执行利率定价表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Component
public interface RateZxlldjbMapper extends BaseMapper<RateZxlldjb> {

    public  RateZxlldjb queryzxlldjb(String zjhm, Date djnf);

    int updateSpzt(String djid,String spzt,String note);
}
