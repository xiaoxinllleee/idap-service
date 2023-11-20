package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Bzdb;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 保存担保
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
public interface IBzdbService extends IService<Bzdb> {

	public List<Bzdb> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取担保方式:保证担保信息
	 * @param id
	 * @return
	 */
	List<Bzdb> queryGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:保证担保信息
	 * @param id
	 * @return
	 */
	List<Bzdb> queryGuaranteeDataById(@Param("id") String id);
}
