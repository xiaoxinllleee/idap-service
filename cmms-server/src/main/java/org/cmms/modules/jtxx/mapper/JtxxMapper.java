package org.cmms.modules.jtxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.base.entity.Dict;
import org.cmms.modules.jtxx.entity.Jtxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 家庭信息
 * @Author: jeecg-boot
 * @Date:   2020-10-16
 * @Version: V1.0
 */
public interface JtxxMapper extends BaseMapper<Jtxx> {

    public List<Jtxx>  queryJtxx(@Param("hhbm")String hhbm,@Param("id")String id);

    public Dict queryDict(@Param("dictCode") String dictCode);

    public Integer deleteJtcyxx(@Param("hhbm")String hhbm,@Param("id")String id);

    public Integer updateJtcyxx(@Param("id")String id);

    public List<Jtxx> selectByHhbm(@Param("hhbm") String hhbm,@Param("id")String id);

    public Jtxx selectByZjhm(@Param("zjhm")String zjhm);

    public Jtxx getName(@Param("zjhm")String zjhm);

    public Integer deleteByzjhm(@Param("zjhm")String zjhm);

    public Integer myDeleteById(@Param("id")String id);
}
