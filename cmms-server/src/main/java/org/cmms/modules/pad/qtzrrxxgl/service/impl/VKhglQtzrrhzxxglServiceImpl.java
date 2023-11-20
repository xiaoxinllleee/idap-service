package org.cmms.modules.pad.qtzrrxxgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.pad.qtzrrxxgl.entity.KhglQtzrrhmcxx;
import org.cmms.modules.pad.qtzrrxxgl.entity.VKhglQtzrrhzxxgl;
import org.cmms.modules.pad.qtzrrxxgl.entity.VKhglQtzrrhzxxglQuery;
import org.cmms.modules.pad.qtzrrxxgl.mapper.KhglQtzrrhmcxxPadMapper;
import org.cmms.modules.pad.qtzrrxxgl.mapper.VKhglQtzrrhzxxglMapper;
import org.cmms.modules.pad.qtzrrxxgl.service.IVKhglQtzrrhzxxglService;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-29
 * @Version: V1.0
 */
@Service
public class VKhglQtzrrhzxxglServiceImpl extends ServiceImpl<VKhglQtzrrhzxxglMapper, VKhglQtzrrhzxxgl> implements IVKhglQtzrrhzxxglService {

    @Autowired
    private VKhglQtzrrhzxxglMapper khglQtzrrhzxxglMapper;

    @Autowired
    private KhglQtzrrhmcxxPadMapper khglQtzrrhmcxxPadMapper;

    @Autowired
    private ITjfxcsszService tjfxcsszService;

    @Override
    public List<String> selectHzByCy(VKhglQtzrrhzxxglQuery hzxx, HttpServletRequest req){
        QueryWrapper<KhglQtzrrhmcxx> khglQtzrrhmcxxQueryWrapper =new QueryWrapper<>();
        if (!StringUtils.isEmpty(hzxx.getHzxm())) {
            hzxx.setHzxm(hzxx.getHzxm().replaceAll("\\*", ""));
        }
        if (StringUtils.isEmpty(hzxx.getHzxm()) && StringUtils.isEmpty(hzxx.getHzzjhm())) {
            return null;
        }
        if (!StringUtils.isEmpty(hzxx.getHzxm())) {
            khglQtzrrhmcxxQueryWrapper.like("khmc", hzxx.getHzxm());
        }
        if(!StringUtils.isEmpty(hzxx.getHzzjhm())){
            khglQtzrrhmcxxQueryWrapper.eq("zjhm", hzxx.getHzzjhm());
        }
        String sql = "";
        String tableName = "yxdygl_ejyxdygl";
        String dybh = "dybh";
        //查询是否开通三级营销单元
        String sfkt = tjfxcsszService.selectcsz(); //1是 2否
        if ("1".equalsIgnoreCase(sfkt)) {
            tableName = "yxdygl_sjyxdygl";
            dybh = "ejyxdybh";
        }
        if (!StringUtils.isEmpty(hzxx.getSsxz())) {
            sql = " select 1 from " + tableName + " t2 where ssyxdy=t2.dybh and t2.yjyxdybh='" + hzxx.getSsxz() + "' ";
        }
        if (!StringUtils.isEmpty(hzxx.getXzc())) {
            sql = " select 1 from " + tableName + " t2 where ssyxdy=t2.dybh and t2.yjyxdybh='" + hzxx.getSsxz() + "' and t2." + dybh + "='" + hzxx.getXzc() + "' ";
        }
        if (!StringUtils.isEmpty(sql)) {
            khglQtzrrhmcxxQueryWrapper.exists(sql);
        }

        List<KhglQtzrrhmcxx> list = khglQtzrrhmcxxPadMapper.selectList(khglQtzrrhmcxxQueryWrapper);
        List<String> hhbmList = new ArrayList<>();
        for (KhglQtzrrhmcxx khhmcxx : list) {
            if (hhbmList.size() >= 1000) {
                break;
            }
            if (!StringUtils.isEmpty(khhmcxx.getHhbm())) {
                hhbmList.add(khhmcxx.getHhbm());
            }
        }
        return hhbmList;
    }

    @Override
    public void init(String hhbm){
        khglQtzrrhzxxglMapper.init(hhbm);
    }
}
