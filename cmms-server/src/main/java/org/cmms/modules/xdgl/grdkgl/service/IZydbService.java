package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Zydb;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 质押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IZydbService extends IService<Zydb> {

	public List<Zydb> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取担保方式:质押担保信息
	 * @param id
	 * @return
	 */
	List<Zydb> queryPledgeGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:质押担保信息
	 * @param id
	 * @return
	 */
	List<Zydb> queryPledgeGuaranteeDataById(@Param("id") String id);
}
