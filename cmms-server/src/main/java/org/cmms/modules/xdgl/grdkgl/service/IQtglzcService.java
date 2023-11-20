package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Qtglzc;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 其他固定资产
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IQtglzcService extends IService<Qtglzc> {

	public List<Qtglzc> selectByMainId(String mainId);

	/**
	 * 根据"ID"获取固定资产信息:其它固定资产
	 * @param id
	 * @return
	 */
	List<Qtglzc> queryOtherFixedAssetsInfoById(@Param("id") String id);

	/**
	 * 根据"ID"获取固定资产信息:其它固定资产
	 * @param id
	 * @return
	 */
	List<Qtglzc> queryOtherFixedAssetsDataById(@Param("id") String id);
}
