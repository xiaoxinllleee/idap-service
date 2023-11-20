package org.cmms.modules.khpjsx.pjzxmsz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khpjsx.pjzxmsz.entity.PJSX_PJZXMGZSZ_GS;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszQj;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmsz;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 评级子项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IpjsxPjzxmszService extends IService<pjsxPjzxmsz> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(pjsxPjzxmsz pjsxPjzxmsz, List<pjsxPjzxmgzszXl> pjsxPjzxmgzszXlList, List<pjsxPjzxmgzszQj> pjsxPjzxmgzszQjList, List<PJSX_PJZXMGZSZ_GS> pjsxPjzxmgzszGsList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(pjsxPjzxmsz pjsxPjzxmsz, List<pjsxPjzxmgzszXl> pjsxPjzxmgzszXlList, List<pjsxPjzxmgzszQj> pjsxPjzxmgzszQjList, List<PJSX_PJZXMGZSZ_GS> pjsxPjzxmgzszGsList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	public boolean insertXLInfoBatch(List<pjsxPjzxmgzszXl> pjsxPjzxmszs);
}
