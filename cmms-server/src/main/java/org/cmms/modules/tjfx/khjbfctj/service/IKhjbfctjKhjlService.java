package org.cmms.modules.tjfx.khjbfctj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjKhjl;

import java.util.Map;

/**
 * @Description: 客户级别分层统计_客户经理
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface IKhjbfctjKhjlService extends IService<KhjbfctjKhjl> {

    public void extract(Map<String, String> sql);

}
