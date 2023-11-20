package org.cmms.modules.khdj.khdjpd.service;

import org.cmms.modules.khdj.khdjpd.entity.khdjpdSjxmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 客户等级评定数据项明细
 * @Author: cmms
 * @Date:   2019-11-08
 * @Version: V1.0
 */
public interface IkhdjpdSjxmxService extends IService<khdjpdSjxmx> {

	public List<khdjpdSjxmx> viewDetail(String pdzq, Date pdrq, String zjhm);
}
