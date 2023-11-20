package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Dydb;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 抵押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IDydbService extends IService<Dydb> {

	public List<Dydb> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取担保方式:抵押担保信息
	 * @param id
	 * @return
	 */
	List<Dydb> queryMortgageGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:抵押担保信息
	 * @param id
	 * @return
	 */
	List<Dydb> queryMortgageGuaranteeDataById(@Param("id") String id);
}
