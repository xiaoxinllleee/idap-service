package org.cmms.modules.tjbb.ywltj.sbkcx.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.tjbb.ywltj.sbkcx.entity.Sbkcx;
import org.cmms.modules.tjbb.ywltj.sbkcx.mapper.SbkcxMapper;
import org.cmms.modules.tjbb.ywltj.sbkcx.service.ISbkcxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 社保卡查询
 * @Author: jeecg-boot
 * @Date: 2021-08-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "社保卡查询")
@RestController
@RequestMapping("/sbkcx/sbkcx")
public class SbkcxController extends JeecgController<Sbkcx, ISbkcxService> {
    @Autowired
    private ISbkcxService sbkcxService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param sbkcx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "社保卡查询-分页列表查询")
    @ApiOperation(value = "社保卡查询-分页列表查询", notes = "社保卡查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Sbkcx sbkcx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(sbkcx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ISbkcxService.class,sbkcxService,pageNo,pageSize,queryWrapper,"tjyf","card_no");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String tjyf = jsonObject.getString("tjyf");
        if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
			return Result.error("所选月份必须小于当前系统月份！");
		}
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjyf);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ywltj_sbkcx");
            // count_tjbb_ywltj_sbkcx
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                 tjyf = tjyf.replaceAll("-", "");
                sbkcxService.pSbkcx(tjyf);
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                log.error("提取失败", e.getMessage());
                result.setMessage(e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 添加
     *
     * @param sbkcx
     * @return
     */
    @AutoLog(value = "社保卡查询-添加")
    @ApiOperation(value = "社保卡查询-添加", notes = "社保卡查询-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Sbkcx sbkcx) {
        sbkcxService.save(sbkcx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sbkcx
     * @return
     */
    @AutoLog(value = "社保卡查询-编辑")
    @ApiOperation(value = "社保卡查询-编辑", notes = "社保卡查询-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Sbkcx sbkcx) {
        sbkcxService.updateById(sbkcx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "社保卡查询-通过id删除")
    @ApiOperation(value = "社保卡查询-通过id删除", notes = "社保卡查询-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        sbkcxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "社保卡查询-批量删除")
    @ApiOperation(value = "社保卡查询-批量删除", notes = "社保卡查询-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.sbkcxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "社保卡查询-通过id查询")
    @ApiOperation(value = "社保卡查询-通过id查询", notes = "社保卡查询-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Sbkcx sbkcx = sbkcxService.getById(id);
        return Result.ok(sbkcx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param sbkcx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Sbkcx sbkcx) {
        return super.exportXls(request, sbkcx, Sbkcx.class, "社保卡查询");
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
        return super.importExcel(request, response, Sbkcx.class);
    }

}
