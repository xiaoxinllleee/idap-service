package org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.controller;

import java.util.Arrays;
import java.util.Date;
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
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.entity.Ckkhcdknrptj;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.mapper.CkkhcdknrptjMapper;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.service.ICkkhcdknrptjService;
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
 * @Description: 存款客户存贷款年日平统计
 * @Author: jeecg-boot
 * @Date: 2021-08-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存款客户存贷款年日平统计")
@RestController
@RequestMapping("/ckkhcdknrptj/ckkhcdknrptj")
public class CkkhcdknrptjController extends JeecgController<Ckkhcdknrptj, ICkkhcdknrptjService> {
    @Autowired
    private ICkkhcdknrptjService ckkhcdknrptjService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckkhcdknrptj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存款客户存贷款年日平统计-分页列表查询")
    @ApiOperation(value = "存款客户存贷款年日平统计-分页列表查询", notes = "存款客户存贷款年日平统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Ckkhcdknrptj ckkhcdknrptj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckkhcdknrptj, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkkhcdknrptjService.class,ckkhcdknrptjService,pageNo,pageSize,queryWrapper,"jgdm","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String tjyf = jsonObject.getString("tjyf");
        if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
            return Result.error("统计日期必须小于当前系统日期！");
        }
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date cksjrq = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            tjyf = org.cmms.modules.util.DateUtil.getSjQmrq(tjyf, cksjrq, "yyyy-MM-dd");
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjyf);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_qhckkhcdknrptj");
            // count_tjbb_ckyw_qhckkhcdknrptj
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                tjyf = tjyf.replace("-", "");
                ckkhcdknrptjService.pCkkhcdknrptj(tjyf);
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败", e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 添加
     *
     * @param ckkhcdknrptj
     * @return
     */
    @AutoLog(value = "存款客户存贷款年日平统计-添加")
    @ApiOperation(value = "存款客户存贷款年日平统计-添加", notes = "存款客户存贷款年日平统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Ckkhcdknrptj ckkhcdknrptj) {
        ckkhcdknrptjService.save(ckkhcdknrptj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param ckkhcdknrptj
     * @return
     */
    @AutoLog(value = "存款客户存贷款年日平统计-编辑")
    @ApiOperation(value = "存款客户存贷款年日平统计-编辑", notes = "存款客户存贷款年日平统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Ckkhcdknrptj ckkhcdknrptj) {
        ckkhcdknrptjService.updateById(ckkhcdknrptj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存款客户存贷款年日平统计-通过id删除")
    @ApiOperation(value = "存款客户存贷款年日平统计-通过id删除", notes = "存款客户存贷款年日平统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        ckkhcdknrptjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
	/*@AutoLog(value = "存款客户存贷款年日平统计-批量删除")
	@ApiOperation(value="存款客户存贷款年日平统计-批量删除", notes="存款客户存贷款年日平统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckkhcdknrptjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}*/

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存款客户存贷款年日平统计-通过id查询")
    @ApiOperation(value = "存款客户存贷款年日平统计-通过id查询", notes = "存款客户存贷款年日平统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Ckkhcdknrptj ckkhcdknrptj = ckkhcdknrptjService.getById(id);
        return Result.ok(ckkhcdknrptj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param ckkhcdknrptj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Ckkhcdknrptj ckkhcdknrptj) {
        return super.exportXls(request, ckkhcdknrptj, Ckkhcdknrptj.class, "存款客户存贷款年日平统计");
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
        return super.importExcel(request, response, Ckkhcdknrptj.class);
    }

}
