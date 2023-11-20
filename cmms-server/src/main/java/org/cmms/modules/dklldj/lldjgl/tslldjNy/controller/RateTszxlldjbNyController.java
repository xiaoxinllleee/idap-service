package org.cmms.modules.dklldj.lldjgl.tslldjNy.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.dto.RateKhAndTsZxLlNyDto;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateKhjbxxb;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateTszxlldjb;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.IRateKhjbxxbService;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.IRateLsdjNycxService;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.IRateTszxlldjbNyService;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.IRateZxLlNycxService;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.impl.RateTszxlldjbNyServiceImpl;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import org.cmms.modules.xdgl.grdkgl.service.IRateNyZbgzxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: dwdw
 * @Author: jeecg-boot
 * @Date: 2022-09-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "dwdw")
@RestController
@RequestMapping("/rateTszxlldjb")
public class RateTszxlldjbNyController extends JeecgController<RateTszxlldjb, IRateTszxlldjbNyService> {
    @Autowired
    private IRateTszxlldjbNyService rateTszxlldjbService;

    @Autowired
    private IRateKhjbxxbService iRateKhjbxxbService;

    @Autowired
    private IRateNyZbgzxxbService iRateZbgzxxbService;

    @Autowired
    private IRateZxLlNycxService iRateZxLlNycxService;

    @Autowired
    private IRateLsdjNycxService iRateLsdjNycxService;

    /**
     * 分页列表查询
     *
     * @param rateTszxlldjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "dwdw-分页列表查询")
    @ApiOperation(value = "dwdw-分页列表查询", notes = "dwdw-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RateTszxlldjb rateTszxlldjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<RateTszxlldjb> queryWrapper = QueryGenerator.initQueryWrapper(rateTszxlldjb, req.getParameterMap());
        Page<RateTszxlldjb> page = new Page<RateTszxlldjb>(pageNo, pageSize);
        IPage<RateTszxlldjb> pageList = rateTszxlldjbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "dwdw-添加保存")
    @ApiOperation(value = "dwdw-添加保存", notes = "dwdw-添加保存")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody RateTszxlldjb rateTszxlldjb) {
        rateTszxlldjbService.AddRateTsNyLl(rateTszxlldjb);
        return Result.ok("添加成功！");
    }


    /**
     * 添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "dwdw-添加")
    @ApiOperation(value = "dwdw-添加", notes = "dwdw-添加")
    @GetMapping(value = "/addSave")
    public Result<?> add(@RequestParam(value = "djnf", required = true) String djnfstr,
                         @RequestParam(value = "zjhm", required = true) String zjhm, @RequestParam(value = "zzbz", required = true) String zzbz) {
        JSONObject view = new JSONObject();
        try {

            CompletableFuture<RateKhjbxxb> djsqxxSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateKhjbxxb> djsqxxQueryWrapper = new QueryWrapper<>();
                /* djsqxxQueryWrapper.eq("djnf", djnf);*/
                djsqxxQueryWrapper.eq("zjhm", zjhm);
                djsqxxQueryWrapper.eq("zzbz", zzbz);
                RateKhjbxxb djsqxx = iRateKhjbxxbService.getOne(djsqxxQueryWrapper, false);
                return djsqxx;
            });
            CompletableFuture<RateTszxlldjb> zxlldjbSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateTszxlldjb> zxlldjbQueryWrapper = new QueryWrapper<>();
                zxlldjbQueryWrapper.eq("djnf", djnfstr);
                zxlldjbQueryWrapper.eq("zjhm", zjhm);
                zxlldjbQueryWrapper.eq("spzt", "0");//确认状态 / 未确认
                RateTszxlldjb zxlldjb = rateTszxlldjbService.getOne(zxlldjbQueryWrapper, false);
                return zxlldjb;
            });
            RateKhjbxxb rateKhjbxxb = djsqxxSupplyAsync.get();
            if (rateKhjbxxb == null) {
                return Result.error("客户信息不存在,请在客户信息管理中进行维护！");
            }
            RateTszxlldjb zxlldjbCheck = zxlldjbSupplyAsync.get();
            if (zxlldjbCheck != null) {
                return Result.error("同一年度内该客户只允许有一条未确认的定价信息，请核实！");
            }
            //查询基准利率
            Double jzll1 = 0d;
            Double jzll2 = 0d;
            Double jzll3 = 0d;
            Double LPRLv1 = 0d;
            Double LPRLv2 = 0d;
            String LPRDate = "";
            QueryWrapper<RateZbgzxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zbid", "KH00011");
            List<RateZbgzxxb> rateZbgzxxbList = iRateZbgzxxbService.selectList(queryWrapper);
            for (RateZbgzxxb rateZbgzxxb : rateZbgzxxbList) {
                String zbgzid = rateZbgzxxb.getZbgzid();
                String zbjg = rateZbgzxxb.getZbjg();
                if ("GZ00050".equals(zbgzid)) {
                    jzll1 = Double.valueOf(zbjg) / 100;
                } else if ("GZ00051".equals(zbgzid)) {
                    jzll2 = Double.valueOf(zbjg) / 100;
                } else if ("GZ00052".equals(zbgzid)) {
                    jzll3 = Double.valueOf(zbjg) / 100;
                }
            }
            view.put("jzll1", jzll1);
            view.put("jzll2", jzll2);
            view.put("jzll3", jzll3);
            QueryWrapper<RateZbgzxxb> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("zbid", "KH00013");
            List<RateZbgzxxb> rateZbgzxxbLists = iRateZbgzxxbService.selectList(queryWrapper2);
            for (RateZbgzxxb rateZbgzxxb : rateZbgzxxbLists) {
                String zbgzid = rateZbgzxxb.getZbgzid();
                String zbjg = rateZbgzxxb.getZbjg();
                if ("GZ00063".equals(zbgzid)) {
                    LPRLv1 = Double.valueOf(zbjg);
                } else if ("GZ00064".equals(zbgzid)) {
                    LPRLv2 = Double.valueOf(zbjg);
                } else if ("GZ00062".equals(zbgzid)) {
                    LPRDate = zbjg;
                }
            }
            view.put("bjrq", LPRDate);
            view.put("LPRLv1", LPRLv1);
            view.put("LPRLv2", LPRLv2);
            view.put("djsqxx", rateKhjbxxb);
            return Result.ok(view);
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("添加失败！，请联系系统管理员！" + throwable.getMessage());
        }

    }

    /**
     * 重新计算
     *
     * @param rateTszxlldjb
     * @return
     */
    @AutoLog(value = "dwdw-重新计算")
    @ApiOperation(value = "dwdw-重新计算", notes = "dwdw-重新计算")
    @PutMapping(value = "/repeatCompute")
    public Result<?> repeatCompute(@RequestBody RateTszxlldjb rateTszxlldjb) {
        if (rateTszxlldjb.getDjid() == null) {
            return Result.error("参数错误");
        }
        JSONObject view = rateTszxlldjbService.getComputeResultById(rateTszxlldjb);
        if (view == null) {
            return Result.error("没有找到相关的定价信息！");
        }
        return Result.ok(view);
    }


    /**
     * 编辑
     *
     * @param rateTszxlldjb
     * @return
     */
    @AutoLog(value = "dwdw-编辑")
    @ApiOperation(value = "dwdw-编辑", notes = "dwdw-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody RateTszxlldjb rateTszxlldjb) {
        /**
         * 特殊利率定价 / 编辑
         *
         * @param rateTszxlldjb
         * @return
         */
            rateTszxlldjbService.updateRateTsNyLl(rateTszxlldjb);
            return Result.ok("更新成功!");


    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "dwdw-通过id删除")
    @ApiOperation(value = "dwdw-通过id删除", notes = "dwdw-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {

        if (id == null) {
            return Result.error("参数错误！");
        }
        Boolean flag = rateTszxlldjbService.selectSpStatusById(id);
        if (flag) {
            return Result.error("只能删除未确认或退回的定价信息！");
        }
        QueryWrapper<RateTszxlldjb> queryWrapper = new QueryWrapper();
        queryWrapper.eq("djid", id);
        rateTszxlldjbService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "dwdw-批量删除")
    @ApiOperation(value = "dwdw-批量删除", notes = "dwdw-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.rateTszxlldjbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "dwdw-通过id查询")
    @ApiOperation(value = "dwdw-通过id查询", notes = "dwdw-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        RateTszxlldjb rateTszxlldjb = rateTszxlldjbService.getById(id);
        return Result.ok(rateTszxlldjb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rateTszxlldjb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RateTszxlldjb rateTszxlldjb) {
        return super.exportXls(request, rateTszxlldjb, RateTszxlldjb.class, "dwdw");
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
        return super.importExcel(request, response, RateTszxlldjb.class);
    }

    @PostMapping(value = "/updateSpzt")
    public Result<?> confirmAndSave(@RequestBody RateTszxlldjb rateTszxlldjb) {
        if (rateTszxlldjb.getSpzt() == null) {
            return Result.error("请选择确认状态！");
        }
        if (rateTszxlldjb.getSpzt() == 0d) {
            return Result.error("该状态为初始状态，不能选择！");
        }
        RateTszxlldjb rateTszxlldjb1 = rateTszxlldjbService.selectRateRszxlldjbNyByDjId(rateTszxlldjb.getDjid());
        if (rateTszxlldjb1 == null) {
            return Result.error("未找到需要确认的记录！");
        }
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        rateTszxlldjb1.setSpr(sysUser.getUsername());
        rateTszxlldjb1.setSpsj(new Date());
        rateTszxlldjb1.setNote(rateTszxlldjb.getNote());
        rateTszxlldjb1.setSpzt(rateTszxlldjb.getSpzt());
        rateTszxlldjbService.updateById(rateTszxlldjb1);
        iRateZxLlNycxService.updateZxLi(rateTszxlldjb1);
        iRateLsdjNycxService.insertHistoryNy(rateTszxlldjb1);
        return Result.ok();
    }


    @PostMapping(value = "/confirm")
    public Result<?> confirm(@RequestBody RateTszxlldjb rateTszxlldjb) {
        JSONObject view = new JSONObject();
        if (rateTszxlldjb.getDjid() == null) {
            return Result.error("参数错误！");
        }
        RateTszxlldjb rateTszxlldjb1 = rateTszxlldjbService.selectRateRszxlldjbNyByDjId(rateTszxlldjb.getDjid());
        if (rateTszxlldjb1 == null) {
            return Result.error("没有找到需要确认的记录！");
        }
        if (rateTszxlldjb1.getSpzt() == 1d) {
            return Result.error("该记录不允许重复确认!");
        }
        Boolean flag = iRateZxLlNycxService.selectKeHuZxLiByZjHGmAndDjnf(rateTszxlldjb);
        view.put("confirmMsg", flag);
        return Result.ok(view);
    }
}
