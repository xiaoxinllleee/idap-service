package org.cmms.modules.khpjsx.pjzxmsz.service;

import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszQj;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 金融业务信息
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IpjsxPjzxmgzszQjService extends IService<pjsxPjzxmgzszQj> {

	public List<pjsxPjzxmgzszQj> selectByMainId(String mainId,String khlx);
}
