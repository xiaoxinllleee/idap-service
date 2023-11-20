package org.cmms.modules.tjfx.khjbfctj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjQh;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 客户级别分层统计_全行
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface KhjbfctjQhMapper extends BaseMapper<KhjbfctjQh> {

    /** 全行级别分层统计 */
    public void extract(Map<String, String> sql);

    /** 查询全行最新数据日期：月 */
    public Date getMaxDateM();
    /** 查询全行最新数据日期：季 */
    public Date getMaxDateQ();
    /** 查询全行最新数据日期：年 */
    public Date getMaxDateY();

    /** 查询全行最新数据：月 */
    public List getQhDataM();
    /** 查询全行最新数据：季 */
    public List getQhDataQ();
    /** 查询全行最新数据：年 */
    public List getQhDataY();
}
