package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.xdgl.grdkgl.entity.YwhyewlxxJtsjhz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 与我行业务往来信息-家庭数据汇总
 * @Author: Penghr
 * @Date:   2020-10-22
 * @Version: V1.0
 */
public interface YwhyewlxxJtsjhzMapper extends BaseMapper<YwhyewlxxJtsjhz> {

    /**
     * 根据"HHBM"获取"与我行业务往来信息'家庭数据汇总'"
     * @param hhbm
     * @return
     */
    List<YwhyewlxxJtsjhz> GetYwhywwlxxJtsjhzByHhbm(@Param("hhbm") String hhbm);

    /**
     * 贷款审批：根据"HHBM"查询客户家庭成员与我行业务往来数据
     * @param hhbm
     * @return
     */
    List<Ywhywwlxx> queryJtcyYwhywwlDataByHhbm(@Param("hhbm") String hhbm);

}
