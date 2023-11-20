package org.cmms.modules.xdgl.grdkgl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.service.*;
import org.cmms.modules.xdgl.jtspcy.service.impl.JtspcyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 个人贷款审批记录
 * @Author: jeecg-boot
 * @Date: 2020-08-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "个人贷款审批记录")
@RestController
@RequestMapping("/xdgl/grdkgl/grdkspjlpad")
public class GrdkspjlPadController extends JeecgController<Vgrdkspjl, IVgrdkspjlService> {
    @Autowired
    private IVgrdkspjlService iVGrdkSpjlService;
    @Autowired
    private IJtcyxxService iJtcyxxService;
    @Autowired
    private IGlqyService iGlqyService;
    @Autowired
    private IFwxxService iFwxxService;
    @Autowired
    private ICfxxService iCfxxService;
    @Autowired
    private IClxxService iClxxService;
    @Autowired
    private IQtglzcService iQtgdzcService;
    @Autowired
    private IYhdkService iYhdkService;
    @Autowired
    private IBzdbService iBzdbService;
    @Autowired
    private IDydbService iDydbService;
    @Autowired
    private IZydbService iZydbService;
    @Autowired
    private IXydbService iXydbService;
    @Autowired
    private IYwhyewlxxJtsjhzService iYwhyewlxxJtsjhzService;
    @Autowired
    private IGrxdzllbService iGrxdzllbService;
    @Autowired
    private JtspcyServiceImpl jtspcyService;

    /**
     * 分页列表查询
     *
     * @param vgrdkspjl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "个人贷款审批记录-分页列表查询")
    @ApiOperation(value = "个人贷款审批记录-分页列表查询", notes = "个人贷款审批记录-分页列表查询")
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "xdgl/grdkgl/GrdkSpJgList")
    public Result<?> queryPageList(Vgrdkspjl vgrdkspjl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        vgrdkspjl.setStatus(2);
        QueryWrapper<Vgrdkspjl> queryWrapper = QueryGenerator.initQueryWrapper(vgrdkspjl, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        Page<Vgrdkspjl> page = new Page<Vgrdkspjl>(pageNo, pageSize);
        IPage<Vgrdkspjl> pageList = iVGrdkSpjlService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 贷款审批分页列表查询
     *
     * @param
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "个人贷款审批记录-分页列表查询")
    @ApiOperation(value = "个人贷款审批记录-分页列表查询", notes = "个人贷款审批记录-分页列表查询")
    @GetMapping(value = "/dkspList")
    public Result<?> queryDksp(Vgrdkspjl vgrdkspjl,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                               HttpServletRequest req) {
        QueryWrapper<Vgrdkspjl> queryWrapper = new QueryWrapper<Vgrdkspjl>();//QueryGenerator.initQueryWrapper(vgrdkspjl, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        queryWrapper.eq("status", 2);
        queryWrapper.eq("jtspzzzt", 2);
        queryWrapper.and(wrapper -> wrapper.eq("sskhjl", vgrdkspjl.getSskhjl()).or().inSql("id", "select id from CAMS_ZCSX_GRXDJTSPXZ where zrrid = " + vgrdkspjl.getSskhjl() + "" ));
       /* queryWrapper.eq("sskhjl", vgrdkspjl.getSskhjl())
                .or()
                .inSql("id", "select id from CAMS_ZCSX_GRXDJTSPXZ where zrrid = " + vgrdkspjl.getSskhjl() + "");*/
        if (vgrdkspjl.getZjhm() == null && vgrdkspjl.getKhmc() != null) {
            queryWrapper.eq("khmc", vgrdkspjl.getKhmc());
        }
        if (vgrdkspjl.getZjhm() != null && vgrdkspjl.getKhmc() == null) {
            queryWrapper.eq("zjhm", vgrdkspjl.getZjhm());
        }
        if (vgrdkspjl.getZjhm() != null && vgrdkspjl.getKhmc() != null) {
            queryWrapper.eq("zjhm", vgrdkspjl.getZjhm());
            queryWrapper.eq("khmc", vgrdkspjl.getKhmc());
        }
        Page<Vgrdkspjl> page = new Page<Vgrdkspjl>(pageNo, pageSize);
        IPage<Vgrdkspjl> pageList = iVGrdkSpjlService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    /**
     * 添加
     *
     * @param vgrdkspjl
     * @return
     */
    @AutoLog(value = "个人贷款审批记录-添加")
    @ApiOperation(value = "个人贷款审批记录-添加", notes = "个人贷款审批记录-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Vgrdkspjl vgrdkspjl) {
        iVGrdkSpjlService.save(vgrdkspjl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param vgrdkspjl
     * @return
     */
    @AutoLog(value = "个人贷款审批记录-编辑")
    @ApiOperation(value = "个人贷款审批记录-编辑", notes = "个人贷款审批记录-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Vgrdkspjl vgrdkspjl) {
        iVGrdkSpjlService.updateById(vgrdkspjl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "个人贷款审批记录-通过id删除")
    @ApiOperation(value = "个人贷款审批记录-通过id删除", notes = "个人贷款审批记录-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        iVGrdkSpjlService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "个人贷款审批记录-批量删除")
    @ApiOperation(value = "个人贷款审批记录-批量删除", notes = "个人贷款审批记录-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.iVGrdkSpjlService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     * 贷款审批 - 点击客户名称 - 查看详情
     * @param id
     * @return
     */
    @AutoLog(value = "个人贷款审批记录-通过id查询")
    @ApiOperation(value = "个人贷款审批记录-通过id查询", notes = "个人贷款审批记录-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Vgrdkspjl grdkSpjl = iVGrdkSpjlService.getById(id);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(grdkSpjl));
        return Result.ok(jsonObject);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vgrdkspjl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Vgrdkspjl vgrdkspjl) {
        return super.exportXls(request, vgrdkspjl, Vgrdkspjl.class, "个人贷款-已审批");
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
        return super.importExcel(request, response, Vgrdkspjl.class);
    }

    /**
     * 贷款审批详情：根据"户号编码"&"ID"获取家庭成员信息
     *
     * @param hhbm
     * @param id
     * @return
     */
    @GetMapping(value = "/queryFamilyDataByHhbmAndID")
    public Result<?> queryFamilyDataByHhbmAndID(@RequestParam(name = "hhbm", required = true) String hhbm,
                                                @RequestParam(name = "id", required = true) String id) {
        List<Jtcyxx> jtcyxxList = iJtcyxxService.queryFamilyDataByHhbmAndID(hhbm, id);
        return Result.ok(jtcyxxList);
    }

    /**
     * 根据"ID"查询关联企业信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryCompanyDataById")
    public Result<?> queryCompanyDataById(@RequestParam(name = "id", required = true) String id) {
        List<Glqy> glqyList = iGlqyService.queryCompanyDataById(id);
        return Result.ok(glqyList);
    }

    /**
     * 根据"ID"获取固定资产信息:房屋信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryHouseDataById")
    public Result<?> queryHouseDataById(@RequestParam(name = "id", required = true) String id) {
        List<Fwxx> fwxxList = iFwxxService.queryHouseDataById(id);
        return Result.ok(fwxxList);
    }

    /**
     * 根据"ID"获取固定资产信息:厂房信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryFactoryDataById")
    public Result<?> queryFactoryDataById(@RequestParam(name = "id", required = true) String id) {
        List<Cfxx> cfxxList = iCfxxService.queryFactoryDataById(id);
        return Result.ok(cfxxList);
    }

    /**
     * 根据"ID"获取固定资产信息:车辆信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryVehicleDataById")
    public Result<?> queryVehicleDataById(@RequestParam(name = "id", required = true) String id) {
        List<Clxx> clxxList = iClxxService.queryVehicleDataById(id);
        return Result.ok(clxxList);
    }

    /**
     * 根据"ID"获取固定资产信息:其它固定资产
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryOtherFixedAssetsDataById")
    public Result<?> queryOtherFixedAssetsDataById(@RequestParam(name = "id", required = true) String id) {
        List<Qtglzc> qtgdzcList = iQtgdzcService.queryOtherFixedAssetsDataById(id);
        return Result.ok(qtgdzcList);
    }

    /**
     * 根据"ID"获取负债信息:银行贷款信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryBankLoadDataById")
    public Result<?> queryBankLoadDataById(@RequestParam(name = "id", required = true) String id) {
        List<Yhdk> yhdkList = iYhdkService.queryBankLoadDataById(id);
        return Result.ok(yhdkList);
    }

    /**
     * 贷款审批：根据"HHBM"查询客户家庭成员与我行业务往来数据
     *
     * @param hhbm
     * @return
     */
    @GetMapping(value = "/queryJtcyYwhywwlDataByHhbm")
    public Result<?> queryJtcyYwhywwlDataByHhbm(@RequestParam(name = "hhbm", required = true) String hhbm) {
        List<Ywhywwlxx> ywhywwlxxList = iYwhyewlxxJtsjhzService.queryJtcyYwhywwlDataByHhbm(hhbm);
        return Result.ok(ywhywwlxxList);
    }

    /**
     * 根据"ID"获取担保方式:保证担保信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryGuaranteeDataById")
    public Result<?> queryGuaranteeDataById(@RequestParam(name = "id", required = true) String id) {
        List<Bzdb> bzdbList = iBzdbService.queryGuaranteeDataById(id);
        return Result.ok(bzdbList);
    }

    /**
     * 根据"ID"获取担保方式:抵押担保信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryMortgageGuaranteeDataById")
    public Result<?> queryMortgageGuaranteeDataById(@RequestParam(name = "id", required = true) String id) {
        List<Dydb> dydbList = iDydbService.queryMortgageGuaranteeDataById(id);
        return Result.ok(dydbList);
    }

    /**
     * 根据"ID"获取担保方式:质押担保信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryPledgeGuaranteeDataById")
    public Result<?> queryPledgeGuaranteeDataById(@RequestParam(name = "id", required = true) String id) {
        List<Zydb> zydbList = iZydbService.queryPledgeGuaranteeDataById(id);
        return Result.ok(zydbList);
    }

    /**
     * 根据"ID"获取担保方式:信用担保
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryCreditGuaranteeDataById")
    public Result<?> queryCreditGuaranteeDataById(@RequestParam(name = "id", required = true) String id) {
        List<Xydb> xydbList = iXydbService.queryCreditGuaranteeDataById(id);
        return Result.ok(xydbList);
    }

    /**
     * 根据"ID"获取附件信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryFjxxDataListById", method = RequestMethod.GET)
    public Result<?> queryFjxxDataListById(@RequestParam(name = "id") String id) {
        try {
            QueryWrapper<Vgrdkspjl> grdkSpjlQueryWrapper = new QueryWrapper<>();
            grdkSpjlQueryWrapper.eq("id", id);
            Vgrdkspjl grdkSpjl = iVGrdkSpjlService.getOne(grdkSpjlQueryWrapper);
            QueryWrapper<Grxdzllb> grxdzllbQueryWrapper = new QueryWrapper<>();
            grxdzllbQueryWrapper.eq("zjhm", grdkSpjl.getZjhm());
            List<Grxdzllb> grxdzllbList = iGrxdzllbService.list(grxdzllbQueryWrapper);
            if (grxdzllbList != null && grxdzllbList.size() > 0) {
                return Result.ok(grxdzllbList);
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("查询成功!");
    }
}
