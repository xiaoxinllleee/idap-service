package org.cmms.modules.system.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.system.entity.SysDictItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.VsysUserRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-28
 */
@Component
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {
    @Select("SELECT * FROM sys_dict_item WHERE DICT_ID = #{mainId} order by sort_order asc, item_value asc")
    public List<SysDictItem> selectItemsByMainId(String mainId);

    public List<SysDictItem> selectItemsByDictCode(@Param("code") String code);

    public VsysUserRole getByWorkNo(@Param("wordNo") String wordNo);

    public String getSeqRateZxlldjbDjidNextval(@Param("seq") String seq);

    String getCollateralScore(@Param("dictcode") String dictcode,@Param("itemvalue") String itemvalue);
}
