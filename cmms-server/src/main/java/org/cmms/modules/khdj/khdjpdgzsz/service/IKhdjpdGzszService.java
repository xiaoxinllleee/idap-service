package org.cmms.modules.khdj.khdjpdgzsz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzszGzxx;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzsz;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 客户等级规则设置
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface IKhdjpdGzszService extends IService<KhdjpdGzsz> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(KhdjpdGzsz kHDJ_KHDJPDGZSZ_real, List<KhdjpdGzszGzxx> gzap_jhxf_khjl_gzxx_realList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(KhdjpdGzsz kHDJ_KHDJPDGZSZ_real, List<KhdjpdGzszGzxx> gzap_jhxf_khjl_gzxx_realList);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

}
