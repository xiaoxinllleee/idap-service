package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Grxdzllb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 个人信贷资料列表
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Component
public interface GrxdzllbMapper extends BaseMapper<Grxdzllb> {

    public  boolean deleteimg(String zjhm,String zllx);

}
