package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Glqy;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkBzxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xdgl.grdkgl.entity.Xydb;

/**
 * @Description: 个人贷款保证信息
 * @Author: jeecg-boot
 * @Date:   2020-09-11
 * @Version: V1.0
 */
public interface GrdkBzxxMapper extends BaseMapper<GrdkBzxx> {
    public boolean deleteByMainId(String mainId);

    public List<GrdkBzxx> selectByMainId(String mainId);
}
