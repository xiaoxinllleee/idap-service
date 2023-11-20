package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Jtcyxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;

/**
 * @Description: 家庭成员
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface JtcyxxMapper extends BaseMapper<Jtcyxx> {

	public boolean deleteByMainHhbmAzjhm(String hhbm,String zjhm);

	public boolean deleteByMainId(String hhbm);

	public List<Jtcyxx> selectByMainId(String hhbm ,String zjhm);

	/**
	 * 根据"户号编码"&"ID"获取家庭成员信息
	 * @param hhbm
	 * @param id
	 * @return
	 */
	List<Jtcyxx> queryFamilyListByHhbmAndId(@Param("hhbm") String hhbm, @Param("id") String id);

	/**
	 * 贷款审批详情：根据"户号编码"&"ID"获取家庭成员信息
	 * @param hhbm
	 * @param id
	 * @return
	 */
	List<Jtcyxx> queryFamilyDataByHhbmAndID(@Param("hhbm") String hhbm, @Param("id") String id);

	/**
	 * 根据"id"删除家庭成员信息
	 * @param id
	 */
	void deleteFamilyInfoById(@Param("id") String id);
}
