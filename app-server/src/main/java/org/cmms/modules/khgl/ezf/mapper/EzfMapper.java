package org.cmms.modules.khgl.ezf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.khgl.ezf.entity.Ezf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: E支付
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
public interface EzfMapper extends BaseMapper<Ezf> {

    @Select("select max(t.tjrq) from erp_dzyhgl_ezf t")
    String getDate();

}
