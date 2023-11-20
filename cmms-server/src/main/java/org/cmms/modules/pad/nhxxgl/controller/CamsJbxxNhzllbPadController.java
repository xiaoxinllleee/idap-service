package org.cmms.modules.pad.nhxxgl.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khgl.nh.entity.CamsPlpyYsfj;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhxxgl.entity.CamsJbxxNhzllbPad;
import org.cmms.modules.pad.nhxxgl.entity.CamsJbxxNhzllbPadRecive;
import org.cmms.modules.pad.nhxxgl.service.ICamsJbxxNhzllbPadService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date: 2020-08-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "1")
@RestController
@RequestMapping("/CamsJbxxNhzllbPad/camsJbxxNhzllbPad")
public class CamsJbxxNhzllbPadController extends JeecgController<CamsJbxxNhzllbPad, ICamsJbxxNhzllbPadService> {
    @Autowired
    private ICamsJbxxNhzllbPadService camsJbxxNhzllbPadService;


    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Autowired
    private INhxqService nhxqService;

    /**
     * 分页列表查询
     *
     * @param camsJbxxNhzllbPad
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CamsJbxxNhzllbPad camsJbxxNhzllbPad,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CamsJbxxNhzllbPad> queryWrapper = QueryGenerator.initQueryWrapper(camsJbxxNhzllbPad, req.getParameterMap());
        Page<CamsJbxxNhzllbPad> page = new Page<CamsJbxxNhzllbPad>(pageNo, pageSize);
        IPage<CamsJbxxNhzllbPad> pageList = camsJbxxNhzllbPadService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    @RequestMapping(value = "/queryNhGrFjxx", method = RequestMethod.GET)
    public Result<?> queryNhGrFjxx(@RequestParam(name = "hhbm") String hhbm, @RequestParam(name = "id") String id) {
        try {
            QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.eq("id", id);
            Nhxq khhmcxx = nhxqService.getOne(khhmcQueryWrapper);
            QueryWrapper<CamsJbxxNhzllbPad> fjxxQueryWrapper = new QueryWrapper<>();
            fjxxQueryWrapper.eq("hhbm", hhbm);
            fjxxQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
            List<CamsJbxxNhzllbPad> list = camsJbxxNhzllbPadService.list(fjxxQueryWrapper);
            if (list != null && list.size() > 0) {
                return Result.ok(list);
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("查询成功");
    }


    @RequestMapping(value = "/saveNhGrfjImage", method = RequestMethod.POST)
    public Result<?> saveNhGrfjImage(@RequestBody List<CamsJbxxNhzllbPadRecive> jsonObject1) {
        try {
            if (jsonObject1 != null && jsonObject1.size() > 0) {
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                for (int i = 0; i < jsonObject1.size(); i++) {
                    if (StringUtils.isEmpty(jsonObject1.get(i).getZlbh())) {
                        QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("id", jsonObject1.get(i).getHmcId());
                        Nhxq khhmcxx = nhxqService.getOne(queryWrapper);

                        CamsJbxxNhzllbPad fjgl = new CamsJbxxNhzllbPad();
                        fjgl.setHhbm(jsonObject1.get(i).getHhbm());
                        fjgl.setZlbh(UUID.randomUUID().toString().substring(0, 32));
                        fjgl.setQydm(jsonObject1.get(i).getQydm());
                        fjgl.setHmcId(jsonObject1.get(i).getHmcId());
                        fjgl.setZjhm(khhmcxx.getZjhm());
                        fjgl.setZllx(jsonObject1.get(i).getZllx());
                        fjgl.setZldx(jsonObject1.get(i).getZldx());
                        fjgl.setFwlj(jsonObject1.get(i).getFwlj());
                        fjgl.setZlmc(jsonObject1.get(i).getZlmc());
                        fjgl.setZllj(uploadpath + "/" + jsonObject1.get(i).getFwlj());
                        fjgl.setLrr(sysUser.getUsername());
                        fjgl.setScr(sysUser.getUsername());
                        fjgl.setScsj(new Date());
                        fjgl.setLrbz("1");
                        camsJbxxNhzllbPadService.save(fjgl);
                    }
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "/deleteNhGrfjImage", method = RequestMethod.POST)
    public Result<?> deleteNhGrfjImage(@RequestBody List<CamsJbxxNhzllbPadRecive> jsonObject) {
        try {
            if (jsonObject != null && jsonObject.size() > 0) {
                for (int i = 0; i < jsonObject.size(); i++) {
                    if (!StringUtils.isEmpty(jsonObject.get(i).getZlmc())) {
                        UpdateWrapper<CamsJbxxNhzllbPad> khglNhzllbPadUpdateWrapper = new UpdateWrapper<>();
                        khglNhzllbPadUpdateWrapper.eq("zlmc", jsonObject.get(i).getZlmc());
                        camsJbxxNhzllbPadService.remove(khglNhzllbPadUpdateWrapper);
                    }
                }
            }

        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }


    /**
     * 添加
     *
     * @param camsJbxxNhzllbPad
     * @return
     */
    @AutoLog(value = "1-添加")
    @ApiOperation(value = "1-添加", notes = "1-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CamsJbxxNhzllbPad camsJbxxNhzllbPad) {
        camsJbxxNhzllbPadService.save(camsJbxxNhzllbPad);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param camsJbxxNhzllbPad
     * @return
     */
    @AutoLog(value = "1-编辑")
    @ApiOperation(value = "1-编辑", notes = "1-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CamsJbxxNhzllbPad camsJbxxNhzllbPad) {
        camsJbxxNhzllbPadService.updateById(camsJbxxNhzllbPad);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id删除")
    @ApiOperation(value = "1-通过id删除", notes = "1-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        camsJbxxNhzllbPadService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "1-批量删除")
    @ApiOperation(value = "1-批量删除", notes = "1-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.camsJbxxNhzllbPadService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id查询")
    @ApiOperation(value = "1-通过id查询", notes = "1-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CamsJbxxNhzllbPad camsJbxxNhzllbPad = camsJbxxNhzllbPadService.getById(id);
        return Result.ok(camsJbxxNhzllbPad);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param camsJbxxNhzllbPad
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CamsJbxxNhzllbPad camsJbxxNhzllbPad) {
        return super.exportXls(request, camsJbxxNhzllbPad, CamsJbxxNhzllbPad.class, "1");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CamsJbxxNhzllbPad.class);
    }

    @PostMapping(value = "/addJzyxfj")
    public Result<?> addJzyxfj(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);

        String fwlj = jsonObject.getString("fwlj");
        String zllx = jsonObject.getString("zllx");
        String hmcId = jsonObject.getString("hmcId");
        String zjhm = jsonObject.getString("zjhm");
        String hhbm = jsonObject.getString("hhbm");
        //先清空旧的
        if (zllx.contains(",")) {
            String[] split = zllx.split(",");
            LambdaQueryWrapper<CamsJbxxNhzllbPad> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(CamsJbxxNhzllbPad::getZllx, split);
            lambdaQueryWrapper.eq(CamsJbxxNhzllbPad::getHmcId, hmcId);
            //lambdaQueryWrapper.like(CamsJbxxNhzllbPad::getZllx, "jzyx");
            service.remove(lambdaQueryWrapper);
        } else {
            LambdaQueryWrapper<CamsJbxxNhzllbPad> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(CamsJbxxNhzllbPad::getZllx, zllx);
            lambdaQueryWrapper.eq(CamsJbxxNhzllbPad::getHmcId, hmcId);
            service.remove(lambdaQueryWrapper);
        }

        if (fwlj.contains(",")) {
            String[] split = fwlj.split(",");
            String[] split1 = zllx.split(",");
            for (int i = 0; i < split.length; i++) {
                if (org.apache.commons.lang3.StringUtils.isBlank(split1[i]))
                    continue;
                CamsJbxxNhzllbPad camsJbxxNhzllbPad = new CamsJbxxNhzllbPad();
                camsJbxxNhzllbPad.setHmcId(hmcId);
                camsJbxxNhzllbPad.setZlbh(IdUtil.fastSimpleUUID());
                camsJbxxNhzllbPad.setZjhm(zjhm);
                camsJbxxNhzllbPad.setHhbm(hhbm);
                camsJbxxNhzllbPad.setScr(getWorkNo());
                camsJbxxNhzllbPad.setScsj(new Date());
                camsJbxxNhzllbPad.setFwlj(split[i]);
                camsJbxxNhzllbPad.setZllx(split1[i]);
                service.save(camsJbxxNhzllbPad);
            }
        } else if (org.apache.commons.lang3.StringUtils.isNotBlank(fwlj)) {
            CamsJbxxNhzllbPad camsJbxxNhzllbPad = new CamsJbxxNhzllbPad();
            camsJbxxNhzllbPad.setHmcId(hmcId);
            camsJbxxNhzllbPad.setZlbh(IdUtil.fastSimpleUUID());
            camsJbxxNhzllbPad.setZjhm(zjhm);
            camsJbxxNhzllbPad.setHhbm(hhbm);
            camsJbxxNhzllbPad.setScr(getWorkNo());
            camsJbxxNhzllbPad.setScsj(new Date());
            camsJbxxNhzllbPad.setFwlj(fwlj);
            camsJbxxNhzllbPad.setZllx(zllx);
            service.save(camsJbxxNhzllbPad);
        }
        return Result.ok("添加成功！");
    }

    @GetMapping("/getJzyxList")
    public Result<?> getJzyxList(String hmcId,String hhbm){
        LambdaQueryWrapper<CamsJbxxNhzllbPad> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(hmcId))
            lambdaQueryWrapper.eq(CamsJbxxNhzllbPad::getHmcId,hmcId);
        if (StringUtils.isNotBlank(hhbm))
            lambdaQueryWrapper.eq(CamsJbxxNhzllbPad::getHhbm,hhbm);

        lambdaQueryWrapper.like(CamsJbxxNhzllbPad::getZllx,"jzyx");
        return Result
                .ok(service.list(lambdaQueryWrapper));
    }
}
