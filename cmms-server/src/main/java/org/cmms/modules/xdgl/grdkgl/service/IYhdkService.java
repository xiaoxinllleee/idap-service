package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Yhdk;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 银行贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IYhdkService extends IService<Yhdk> {

	public List<Yhdk> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取负债信息:银行贷款信息
	 * @param id
	 * @return
	 */
	List<Yhdk> queryBankLoadInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取负债信息:银行贷款信息
	 * @param id
	 * @return
	 */
	List<Yhdk> queryBankLoadDataById(@Param("id") String id);
}
