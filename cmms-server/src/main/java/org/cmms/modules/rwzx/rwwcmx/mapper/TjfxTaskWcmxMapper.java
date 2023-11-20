package org.cmms.modules.rwzx.rwwcmx.mapper;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.rwzx.rwwcmx.entity.TjfxTaskWcmx;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmx;

import javax.xml.crypto.Data;

/**
 * @Description: 统计分析-任务完成明细
 * @Author: jeecg-boot
 * @Date:   2023-08-09
 * @Version: V1.0
 */
public interface TjfxTaskWcmxMapper extends BaseMapper<TjfxTaskWcmx> {
    void initRwwcqk(String tjrq);
    Date getMaxDate();
    IPage<TaskZfyxmx> getRwwcmx(Page page,String lx,String yggh,String sszh,String rwlx,String tjrq,String tjwd);
}
