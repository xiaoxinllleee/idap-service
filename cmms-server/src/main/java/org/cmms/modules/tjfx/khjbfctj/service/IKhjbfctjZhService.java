package org.cmms.modules.tjfx.khjbfctj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjZh;

import java.util.Map;

/**
 * @Description: 客户级别分层统计_支行
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface IKhjbfctjZhService extends IService<KhjbfctjZh> {

    public void extract(Map<String, String> sql);

}
