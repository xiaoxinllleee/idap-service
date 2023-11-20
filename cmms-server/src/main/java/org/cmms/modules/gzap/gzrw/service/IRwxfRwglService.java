package org.cmms.modules.gzap.gzrw.service;

import org.cmms.modules.gzap.gzrw.entity.RwxfRwgl;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
public interface IRwxfRwglService extends IService<RwxfRwgl> {

	public List<RwxfRwgl> selectByMainId(String mainId);

}
