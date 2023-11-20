package org.cmms.modules.gzap.gzrz.service;

import org.cmms.modules.gzap.gzrz.entity.GzapRzglKhgh;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
public interface IGzapRzglKhghService extends IService<GzapRzglKhgh> {

	public List<GzapRzglKhgh> selectByMainId(String mainId);
}
