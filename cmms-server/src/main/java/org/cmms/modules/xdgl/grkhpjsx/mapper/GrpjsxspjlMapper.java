package org.cmms.modules.xdgl.grkhpjsx.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;


/**
 * @Description: 个人客户评级授信记录
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
@Component
public interface GrpjsxspjlMapper extends BaseMapper<Grpjsxspjl> {
    public void deleteByspid(@Param("spid") String spid);

    public void deleteGrdkByspid(@Param("spid") String spid);

    public List<Grpjsxspjl> getDzdkzData();
}
