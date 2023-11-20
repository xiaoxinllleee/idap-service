package org.cmms.modules.xdgl.grkhpjsx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xdgl.grkhpjsx.entity.EcifPerson;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 个人客户表 Mapper 接口
 */
@Component
public interface EcifPersonMapper extends BaseMapper<EcifPerson> {
    EcifPerson getByCustId(String custId);
    List<EcifPerson> getByZjhm(String zjhm);

}
