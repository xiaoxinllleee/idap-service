package org.cmms.modules.gzap.gzjh.service;

import org.cmms.modules.gzap.gzjh.entity.GzapJhxfKhjl;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-26
 * @Version: V1.0
 */
public interface IGzapJhxfKhjlService extends IService<GzapJhxfKhjl> {

	public List<GzapJhxfKhjl> selectByMainId(String mainId);
}
