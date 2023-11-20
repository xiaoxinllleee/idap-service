package org.cmms.modules.report.zbgl.zbsjx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmZcfzqk;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 指标数据项管理
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
public interface ZbsjxglMapper extends BaseMapper<Zbsjxgl> {
    List<Zbsjxgl> getListByQydm(String qydm,String zblx, String zbwd, String zbid);
}
