package org.cmms.modules.khgl.grkhgl.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhxxgl;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.entity.ModelCalculationVO;
import org.cmms.modules.khgl.grkhgl.entity.vKhglKrkhgl;
import org.cmms.modules.khgl.grkhgl.service.ICamsZcsxGrpjsxxxService;
import org.cmms.modules.khgl.grkhgl.service.IvKhglKrkhglService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Date 2022/7/27
 * @Created by eran
 */
@RequestMapping("/grpjsx")
@Slf4j
@RestController
public class CamsZcsxGrpjsxxxController extends JeecgController<CamsZcsxGrpjsxxx, ICamsZcsxGrpjsxxxService> {
    @Autowired
    INhxqService nhxqService;

    @GetMapping(value = "/list")
    public Result<?> queryPageList(CamsZcsxGrpjsxxx camsZcsxGrpjsxxx,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CamsZcsxGrpjsxxx> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxGrpjsxxx, req.getParameterMap());
        Page<CamsZcsxGrpjsxxx> page = new Page<CamsZcsxGrpjsxxx>(pageNo, pageSize);
        IPage<CamsZcsxGrpjsxxx> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @RequestMapping("/add")
    public Result<?> add(CamsZcsxGrpjsxxx camsZcsxGrpjsxxx){
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getHmcId())){
            CamsZcsxGrpjsxxx byId = service.getById(camsZcsxGrpjsxxx.getHmcId());
            if (byId != null){
                camsZcsxGrpjsxxx.setUpdateBy(getWorkNo());
                camsZcsxGrpjsxxx.setUpdateTime(new Date());
                service.updateById(camsZcsxGrpjsxxx);
                return Result.ok("更新成功");
            }else {
                Nhxq nhxq = nhxqService.getById(camsZcsxGrpjsxxx.getHmcId());
                if (nhxq != null){
                    if (StringUtils.isNotBlank(nhxq.getKhmc()))
                        camsZcsxGrpjsxxx.setKhmc(nhxq.getKhmc());
                    if (StringUtils.isNotBlank(nhxq.getWgbh()))
                        camsZcsxGrpjsxxx.setQydm(nhxq.getWgbh());
                    if (StringUtils.isNotBlank(nhxq.getZjhm()))
                        camsZcsxGrpjsxxx.setZjhm(nhxq.getZjhm());
                }
                camsZcsxGrpjsxxx.setUpdateBy(getWorkNo());
                camsZcsxGrpjsxxx.setUpdateTime(new Date());
                boolean save = service.save(camsZcsxGrpjsxxx);
                if (save)
                    return Result.ok();
                return Result.error("保存失败");
            }
        }
        return Result.error("操作失败");
    }


    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//        LambdaQueryWrapper<CamsZcsxGrpjsxxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(CamsZcsxGrpjsxxx::getHmcId,id);
//        CamsZcsxGrpjsxxx byId = service.getOne(lambdaQueryWrapper, false);
        CamsZcsxGrpjsxxx byId = service.getById(id);
        if (byId == null){
            //准备默认值
            byId = new CamsZcsxGrpjsxxx();
            byId.setShsyBlsh("2");
            byId.setShsySfqk("1");
            byId.setShsySfygld("2");
            byId.setShsySfdjns("3");
            byId.setSfjdlkpkh("2");
            byId.setShsySfxsfz("2");
            byId.setShsySfss("2");
            byId.setDhzpjPxpj("1");
            byId.setDhzpjXypj("1");
            byId.setCpdj("A");
        }
        return Result.ok(byId);
    }

    @RequestMapping("/modelCalculation")
    public Result<?> modelCalculation(CamsZcsxGrpjsxxx camsZcsxGrpjsxxx){
        System.out.println("2022模型计算");
        System.out.println(camsZcsxGrpjsxxx);
          if (camsZcsxGrpjsxxx != null){
              ModelCalculationVO modelCalculationVO = service.calModel(camsZcsxGrpjsxxx);
              return Result.ok(modelCalculationVO);
          }
          return Result.ok();
    }



    @RequestMapping("/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        try {
            service.removeById(id);
        } catch (Exception e) {
            log.error("删除失败",e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }

    @GetMapping(value = "/queryByZjhm")
    public Result<?> queryByZjhm(@RequestParam(name="zjhm",required=true) String zjhm) {
        LambdaQueryWrapper<CamsZcsxGrpjsxxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CamsZcsxGrpjsxxx::getZjhm,zjhm);
        List<CamsZcsxGrpjsxxx> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)){
            return Result.ok(list.get(0));
        }
        return Result.ok();
    }

    @GetMapping(value = "/queryBySpid")
    public Result<?> queryBySpid(@RequestParam(name="spid",required=true) String spid,
                                 String type) {
        LambdaQueryWrapper<CamsZcsxGrpjsxxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CamsZcsxGrpjsxxx::getSpid,spid);
        List<CamsZcsxGrpjsxxx> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)){
            if (StringUtils.isNotBlank(type) && "1".equals(type)){
                CamsZcsxGrpjsxxx camsZcsxGrpjsxxx = list.get(0);
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getZjhm())){
                    LambdaQueryWrapper<Nhxq> nhxqLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    nhxqLambdaQueryWrapper.eq(Nhxq::getZjhm,camsZcsxGrpjsxxx.getZjhm());
                    List<Nhxq> nhxqs = nhxqService.list(nhxqLambdaQueryWrapper);
                    if (CollUtil.isNotEmpty(nhxqs))
                        return Result.ok(nhxqs.get(0));
                }
            }
            return Result.ok(list.get(0));
        }
        return Result.ok();
    }
}
