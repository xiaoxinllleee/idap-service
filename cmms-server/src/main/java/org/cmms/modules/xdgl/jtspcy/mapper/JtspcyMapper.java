package org.cmms.modules.xdgl.jtspcy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.vo.SysUserDepVo;
import org.cmms.modules.xdgl.jtspcy.entity.Jtspcy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 集体审批成员
 * @Author: jeecg-boot
 * @Date:   2020-09-14
 * @Version: V1.0
 */
@Component
public interface JtspcyMapper extends BaseMapper<Jtspcy> {
    public void deleteJtspcy(String id ,String yggh);

    public Integer updatespjl(@Param("jtspcy") Jtspcy jtspcy);

    public void  deleteSpcy(@Param("id") String id,@Param("zrrids") List<String> zrrids);


    public Jtspcy queryById(@Param("id")String id,@Param("zrrid")String zrrid);
}
