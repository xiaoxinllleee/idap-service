package org.cmms.modules.khgl.etckh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlVO;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlbVO;
import org.cmms.modules.khgl.etckh.entity.Dkjl;

import java.util.List;

/**
 * @Description: ETC贷记卡垫款记录表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@DS("sjxf")
public interface IDkjlService extends IService<Dkjl> {
    List<DjkdkjlbVO> getDkjlList(int start, int end, String namecn);

    List<DjkdkjlbVO> getSfcs(int start, int end, String namecn);

    List<DjkdkjlbVO> getAll(int start, int end, String namecn);

    IPage<DjkdkjlVO> getDjkDkjlList(Page page, String zh);
}
