package org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.controller;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.config.MybatisPlusConfig;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkjkptCkzhglxx;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service.ICkjkptCkzhglxxService;
import org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.entity.CkjkptDfpckzhVo;
import org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.service.ICkjkptDfpckzhVoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.system.entity.VsysUserRole;
import org.cmms.modules.system.service.ISysUserRoleService;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 待分配存款帐号管理
 * @Author: jeecg-boot
 * @Date: 2021-07-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "待分配存款帐号管理")
@RestController
@RequestMapping("/dfpckzhgl/ckjkptDfpckzh")
public class CkjkptDfpckzhVoController extends JeecgController<CkjkptDfpckzhVo, ICkjkptDfpckzhVoService> {
    @Autowired
    private ICkjkptDfpckzhVoService ckjkptDfpckzhService;

    @Autowired
    private ICkjkptCkzhglxxService ckjkptCkzhglxxService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private IHrBasPostService hrBasPostService;
    /**
     * 分页列表查询
     *BeanUtils
     * @param ckjkptDfpckzhVo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "待分配存款帐号管理-分页列表查询")
    @ApiOperation(value = "待分配存款帐号管理-分页列表查询", notes = "待分配存款帐号管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptDfpckzhVo ckjkptDfpckzhVo,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   String startTime, String endTime,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDfpckzhVo, req.getParameterMap());
        try {
            if (startTime != null && endTime != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                queryWrapper.between("khrq", sdf.parse(startTime), sdf.parse(endTime));
            }
        } catch (ParseException p) {
            p.printStackTrace();
        }
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptDfpckzhVoService.class,ckjkptDfpckzhService,pageNo,pageSize,queryWrapper,"khzh","zjhm");
        return Result.ok(pageList);
    }

    @AutoLog(value = "分配存款账号")
    @ApiOperation(value = "分配存款账号", notes = "待分配存款帐号管理")
    @PostMapping(value = "/preservation")
    public Result<?> preservation(@RequestBody JSONObject jsonObject) {
        CkjkptCkzhglxx ckzhglxx=new CkjkptCkzhglxx();
        List<CkjkptDfpckzhVo> jsonList =(List<CkjkptDfpckzhVo>) jsonObject.get("array");
        ObjectMapper objectMapper=  new ObjectMapper();
        List<CkjkptDfpckzhVo> list = objectMapper.convertValue(jsonList, new TypeReference<List<CkjkptDfpckzhVo>>() {  });
        String yggh = jsonObject.getString("yggh");
        String gwbz = jsonObject.getString("gwbz");
        String zzbz = jsonObject.getString("zzbz");
        String gyh = jsonObject.getString("gyh");
        String khjlbz = jsonObject.getString("khjlbz");
        String fpyf = jsonObject.getString("fpyf");
        String jgdm = jsonObject.getString("jgdm");
        Date date =  new Date();
        if (-1 == date.compareTo(DateUtil.parseDateFormat(fpyf,"yyyy-MM-dd"))){
            return Result.error("选择的月份必须小于当前月");
        }
        Date nowDate = DateUtil.string2Date(org.cmms.modules.util.DateUtil.currentTimestamp2String("yyyy-MM") + "-01", "yyyy-MM-dd");
        Date fpDate = DateUtil.parseDateFormat(fpyf, "yyyy-MM-dd");
        while(fpDate.compareTo(nowDate) <= 0) {
            String tjyfStr = org.cmms.modules.util.DateUtil.date2String(fpDate, "yyyy-MM-dd");
            String ckzhTableName = "Ckjkpt_ckzhglxx_his";
            MybatisPlusConfig.myTableNameCkzhglxx.set(ckzhTableName);
            if (fpDate.compareTo(nowDate) == 0) {
                ckzhTableName = "Ckjkpt_ckzhglxx";
                MybatisPlusConfig.myTableNameCkzhglxx.set(ckzhTableName);
            }
            try {
                fpDate = DateUtil.getFirstday_Month(fpDate, 1);
            } catch (Throwable t) {
                t.printStackTrace();
                break;
            }
            for (int i=0;i<list.size();i++){
                CkjkptDfpckzhVo ckjkptDfpckzhVo = list.get(i);
                if (ckjkptDfpckzhVo.getZhxz()==2){   //如果是对公账号，则判断是否允许分配对公账号
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    List<VsysUserRole> VsysUserRole = sysUserRoleService.findByUserIdAndRoleCode(loginUser.getId(),"khjl");
                        if (VsysUserRole==null){
                            return Result.error("账号【" + ckjkptDfpckzhVo.getKhzh() + "】为对公存款账号，只允许管理员进行分配！");
                        }
                }
                ckjkptDfpckzhService.delBykhzh(ckjkptDfpckzhVo.getKhzh());//删除待分配的
                Integer nub = ckjkptCkzhglxxService.getCkzhglxxByckzh(ckjkptDfpckzhVo.getKhzh());
                if (nub>0){
                    //已经分配的账号不做处理
                    continue;
                }
                ckzhglxx.setFiscal_date(tjyfStr);
                ckzhglxx.setGwbz(gwbz);
                ckzhglxx.setZzbz(zzbz);
                ckzhglxx.setYggh(yggh);
                ckzhglxx.setCkzh(ckjkptDfpckzhVo.getKhzh());
                ckzhglxx.setZhmc(ckjkptDfpckzhVo.getHm());
                ckzhglxx.setZhxz(ckjkptDfpckzhVo.getZhxz());
                ckzhglxx.setGyh(gyh);
                ckzhglxx.setKhjlbz(khjlbz);
                ckzhglxx.setYwjgdm(jgdm);
                ckzhglxx.setCkrpye(ckjkptDfpckzhVo.getYzhrpye());
                ckzhglxx.setNckrpye(ckjkptDfpckzhVo.getNzhrpye());
                //获取最新的id
                String maxId = ckjkptCkzhglxxService.getMaxId();
                ckzhglxx.setGlid(Long.valueOf(maxId));
                ckzhglxx.setGlbz(1);
                ckzhglxx.setYxbz(1);
                ckzhglxx.setGlbl(BigDecimal.valueOf(100));
                ckzhglxx.setLrczy(getLoginUser().getRealname());
                ckzhglxx.setLrbz(1);
                ckzhglxx.setLrsj(new Timestamp(System.currentTimeMillis()));
                ckjkptCkzhglxxService.save(ckzhglxx);
            }
        }
        return Result.ok("分配成功");
    }

    @AutoLog(value = "待分配存款帐号管理查找带回-分页列表查询")
    @ApiOperation(value = "待分配存款帐号管理查找带回-分页列表查询", notes = "待分配存款帐号管理-分页列表查询")
    @GetMapping(value = "/getListFindBack")
    public Result<?> getListFindBack(@Param("jgdm") String jgdm) {
        //一页5条数据
        List<HrBasStaffPostVo> list = hrBasPostService.getListFindBack(jgdm);
        //IPage pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), 1, 5);
        return Result.ok(list);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param ckjkptDfpckzhVo
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CkjkptDfpckzhVo ckjkptDfpckzhVo,
                                  String startTime, String endTime) {
        // Step.1 组装查询条件
        QueryWrapper<CkjkptDfpckzhVo> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptDfpckzhVo, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        try {
            if (startTime != null && endTime != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                queryWrapper.between("khrq", sdf.parse(startTime), sdf.parse(endTime));
            }
        } catch (ParseException p) {
            p.printStackTrace();
        }
        // Step.2 获取导出数据
        List<CkjkptDfpckzhVo> list = service.list(queryWrapper);
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "待分配存款帐号管理"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, CkjkptDfpckzhVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("待分配存款帐号管理" + "报表", "导出人:" + sysUser.getRealname(), "待分配存款帐号管理"));
        mv.addObject(NormalExcelConstants.DATA_LIST, list);
        return mv;
        // return super.exportXls(request, ckjkptDfpckzhVo, CkjkptDfpckzhVo.class, "待分配存款帐号管理");
    }


    /**
     * 添加
     *
     * @param ckjkptDfpckzhVo
     * @return
     */
    @AutoLog(value = "待分配存款帐号管理-添加")
    @ApiOperation(value = "待分配存款帐号管理-添加", notes = "待分配存款帐号管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CkjkptDfpckzhVo ckjkptDfpckzhVo) {
        ckjkptDfpckzhService.save(ckjkptDfpckzhVo);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param ckjkptDfpckzhVo
     * @return
     */
    @AutoLog(value = "待分配存款帐号管理-编辑")
    @ApiOperation(value = "待分配存款帐号管理-编辑", notes = "待分配存款帐号管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CkjkptDfpckzhVo ckjkptDfpckzhVo) {
        ckjkptDfpckzhService.updateById(ckjkptDfpckzhVo);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "待分配存款帐号管理-通过id删除")
    @ApiOperation(value = "待分配存款帐号管理-通过id删除", notes = "待分配存款帐号管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        ckjkptDfpckzhService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "待分配存款帐号管理-批量删除")
    @ApiOperation(value = "待分配存款帐号管理-批量删除", notes = "待分配存款帐号管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ckjkptDfpckzhService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "待分配存款帐号管理-通过id查询")
    @ApiOperation(value = "待分配存款帐号管理-通过id查询", notes = "待分配存款帐号管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CkjkptDfpckzhVo ckjkptDfpckzhVo = ckjkptDfpckzhService.getById(id);
        return Result.ok(ckjkptDfpckzhVo);
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
        return super.importExcel(request, response, CkjkptDfpckzhVo.class);
    }

}
