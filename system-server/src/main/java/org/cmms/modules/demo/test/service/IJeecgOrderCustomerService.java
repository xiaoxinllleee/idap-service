package org.cmms.modules.demo.test.service;

import java.util.List;

import org.cmms.modules.demo.test.entity.JeecgOrderCustomer;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单客户
 * @Author: cmms
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface IJeecgOrderCustomerService extends IService<JeecgOrderCustomer> {
	
	public List<JeecgOrderCustomer> selectCustomersByMainId(String mainId);
}
