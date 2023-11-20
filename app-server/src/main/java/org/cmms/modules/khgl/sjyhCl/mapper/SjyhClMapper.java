package org.cmms.modules.khgl.sjyhCl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.khgl.sjyhCl.entity.SjyhCl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 手机银行_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
public interface SjyhClMapper extends BaseMapper<SjyhCl> {

    @Select("select max(t.tjrq) from erp_dzyhgl_sjyh t")
    String getDate();

}
