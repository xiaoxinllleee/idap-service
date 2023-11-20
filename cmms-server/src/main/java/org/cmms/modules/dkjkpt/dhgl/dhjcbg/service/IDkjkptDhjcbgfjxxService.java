package org.cmms.modules.dkjkpt.dhgl.dhjcbg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 附件信息
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptDhjcbgfjxxService extends IService<DkjkptDhjcbgfjxx> {

	public List<DkjkptDhjcbgfjxx> selectByMainId(String mainId);
}
