package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Glqy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 关联企业
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface GlqyMapper extends BaseMapper<Glqy> {

	public boolean deleteByMainId(String mainId);

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
