package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Glqy;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 关联企业
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IGlqyService extends IService<Glqy> {

	public List<Glqy> selectByMainId(String mainId);

	/**
	 * 根据"ID"查询关联企业信息
	 * @param id
	 * @return
	 */
	List<Glqy> queryCompanyListById(@Param("id") String id);

	/**
	 * 根据"ID"查询关联企业信息
	 * @param id
	 * @return
	 */
	List<Glqy> queryCompanyDataById(@Param("id") String id);
}
