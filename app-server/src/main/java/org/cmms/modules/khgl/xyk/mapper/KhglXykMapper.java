package org.cmms.modules.khgl.xyk.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.khgl.xyk.entity.KhglXyk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.SysDict;

/**
 * @Description: 信用卡_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
public interface KhglXykMapper extends BaseMapper<KhglXyk> {
    //获取本机构下未催收合计
    @Select("select count(*)ycshj from Erp_dzyhgl_xyk t where jgdm=#{jgdm} and tjrq=#{tjrq} and sfcs='2'")
    String getYcshj(String jgdm, Date tjrq);

    String getDate(String jgdm,String yggh);

}
