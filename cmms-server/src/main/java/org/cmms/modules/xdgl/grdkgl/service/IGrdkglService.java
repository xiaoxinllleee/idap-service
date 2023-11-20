package org.cmms.modules.xdgl.grdkgl.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.nh.entity.KhglKhhmcxx;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 个人贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
public interface IGrdkglService extends IService<Grdkgl> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(Grdkcjxx cjxx, Jtcyxx hmcxx, List<Jtcyxx> jtcyxxList, List<Glqy> glqyList, List<Fwxx> fwxxList, List<Cfxx> cfxxList, List<Clxx> clxxList, List<Qtglzc> qtglzcList, List<Yhdk> yhdkList, List<Bzdb> bzdbList, List<Dydb> dydbList, List<Zydb> zydbList, List<Xydb> xydbList, JSONObject imgdate);

	/**
	 * Pad端新增保存
	 * @param cjxx
	 * @param grxd
	 * @param jtcyxxList
	 * @param glqyList
	 * @param fwxxList
	 * @param cfxxList
	 * @param clxxList
	 * @param qtglzcList
	 * @param yhdkList
	 * @param bzdbList
	 * @param dydbList
	 * @param zydbList
	 * @param xydbList
	 */
	public void saveMainPad(
			Grdkcjxx cjxx, KhglKhhmcxxGrxd grxd,
			List<Jtcyxx> jtcyxxList, List<Glqy> glqyList,
			List<Fwxx> fwxxList, List<Cfxx> cfxxList, List<Clxx> clxxList, List<Qtglzc> qtglzcList, List<Yhdk> yhdkList,
			List<Bzdb> bzdbList, List<Dydb> dydbList, List<Zydb> zydbList, List<Xydb> xydbList);

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(Grdkcjxx cjxx, Jtcyxx hmcxx, List<Jtcyxx> jtcyxxList, List<Glqy> glqyList, List<Fwxx> fwxxList, List<Cfxx> cfxxList, List<Clxx> clxxList, List<Qtglzc> qtglzcList, List<Yhdk> yhdkList, List<Bzdb> bzdbList, List<Dydb> dydbList, List<Zydb> zydbList, List<Xydb> xydbList, JSONObject imgdate);

	/**
	 * Pad端编辑保存
	 * @param cjxx
	 * @param grxd
	 * @param jtcyxxList
	 * @param glqyList
	 * @param fwxxList
	 * @param cfxxList
	 * @param clxxList
	 * @param qtgdzcList
	 * @param yhdkList
	 * @param bzdbList
	 * @param dydbList
	 * @param zydbList
	 * @param xydbList
	 */
	public void updateMainPad(
			Grdkcjxx cjxx, KhglKhhmcxxGrxd grxd,
			List<Jtcyxx> jtcyxxList, List<Glqy> glqyList,
			List<Fwxx> fwxxList, List<Cfxx> cfxxList, List<Clxx> clxxList, List<Qtglzc> qtgdzcList, List<Yhdk> yhdkList,
			List<Bzdb> bzdbList, List<Dydb> dydbList, List<Zydb> zydbList, List<Xydb> xydbList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	/**
	 * 根据"HHBM"提取"与我行业务往来信息"
	 * @param hhbm
	 */
	void CountYwhywxxDataByHhbm(@Param("hhbm") String hhbm);

}
