package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Xydb;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 信用担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IXydbService extends IService<Xydb> {

	public List<Xydb> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取担保方式:信用担保
	 * @param id
	 * @return
	 */
	List<Xydb> queryCreditGuaranteeInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取担保方式:信用担保
	 * @param id
	 * @return
	 */
	List<Xydb> queryCreditGuaranteeDataById(@Param("id") String id);
}
