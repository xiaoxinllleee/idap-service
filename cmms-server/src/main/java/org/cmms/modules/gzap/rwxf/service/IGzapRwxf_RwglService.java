package org.cmms.modules.gzap.rwxf.service;

import org.cmms.modules.gzap.rwxf.entity.GzapRwxf_Rwgl;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface IGzapRwxf_RwglService extends IService<GzapRwxf_Rwgl> {

	public List<GzapRwxf_Rwgl> selectByMainId(String mainId);
}
