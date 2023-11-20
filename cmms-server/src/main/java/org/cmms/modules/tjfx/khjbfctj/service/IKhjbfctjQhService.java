package org.cmms.modules.tjfx.khjbfctj.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IKhjbfctjQhService extends IService<KhjbfctjQh> {

    public void extract(Map<String, String> sql);

    /** 查询全行最新数据日期 */
    Date getMaxDateM();
    Date getMaxDateQ();
    Date getMaxDateY();

    /** 查询全行最新数据 */
    List getQhDataM();
    List getQhDataQ();
    List getQhDataY();
}
