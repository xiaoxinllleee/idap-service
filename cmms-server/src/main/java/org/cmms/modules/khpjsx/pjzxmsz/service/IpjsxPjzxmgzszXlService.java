package org.cmms.modules.khpjsx.pjzxmsz.service;

import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 与我行往来情况
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IpjsxPjzxmgzszXlService extends IService<pjsxPjzxmgzszXl> {

	public List<pjsxPjzxmgzszXl> selectByMainId(String mainId,String khlx);
}
