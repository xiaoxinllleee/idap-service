package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.RateDjsqxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 等级申请信息
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
public interface RateDjsqxxMapper extends BaseMapper<RateDjsqxx> {

    public RateDjsqxx querydjsqxx(String zjhm ,Date djnf);
}
