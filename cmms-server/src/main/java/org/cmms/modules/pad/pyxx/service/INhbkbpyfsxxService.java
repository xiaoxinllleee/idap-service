package org.cmms.modules.pad.pyxx.service;

import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 背靠背附属信息
 * @Author: jeecg-boot
 * @Date:   2020-10-20
 * @Version: V1.0
 */
public interface INhbkbpyfsxxService extends IService<Nhbkbpyfsxx> {
    //房产价值
    public int fcjz(String fcjz);
    //大宗耐用消费品情况
    public int dznyxfpqk(String dznyxfpqk);
    //家庭纯收入情况
    public int jtcsrqk(String jtcsrqk);
    //农机具情况
    public int njjqk(String njjqk);
    //交通运输工具情况
    public int jtysgjqk(String jtysgjqk);
}
