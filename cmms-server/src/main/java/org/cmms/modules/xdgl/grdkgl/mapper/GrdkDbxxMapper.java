package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkBzxx;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人贷款担保信息
 * @Author: jeecg-boot
 * @Date:   2020-09-11
 * @Version: V1.0
 */
public interface GrdkDbxxMapper extends BaseMapper<GrdkDbxx> {
    public boolean deleteByMainId(String mainId);

    public List<GrdkDbxx> selectByMainId(String mainId);

}
