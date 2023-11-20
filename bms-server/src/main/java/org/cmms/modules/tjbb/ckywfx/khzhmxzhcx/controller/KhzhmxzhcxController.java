package org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.controller;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.entity.Khzhmxzhcx;
import org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.service.IKhzhmxzhcxService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.entity.Whzhckxx;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 客户账户明细综合查询
 * @Author: jeecg-boot
 * @Date: 2021-10-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户账户明细综合查询")
@RestController
@RequestMapping("/tjbb/ckywfx/khzhmxzhcx")
public class KhzhmxzhcxController extends JeecgController<Khzhmxzhcx, IKhzhmxzhcxService> {
    @Autowired
    private IKhzhmxzhcxService khzhmxzhcxService;
	@Autowired
	private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param khzhmxzhcx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户账户明细综合查询-分页列表查询")
    @ApiOperation(value = "客户账户明细综合查询-分页列表查询", notes = "客户账户明细综合查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Khzhmxzhcx khzhmxzhcx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(khzhmxzhcx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IKhzhmxzhcxService.class,khzhmxzhcxService,pageNo,pageSize,queryWrapper,"lsh");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param khzhmxzhcx
     * @return
     */
    @AutoLog(value = "客户账户明细综合查询-添加")
    @ApiOperation(value = "客户账户明细综合查询-添加", notes = "客户账户明细综合查询-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Khzhmxzhcx khzhmxzhcx) {
        khzhmxzhcxService.save(khzhmxzhcx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khzhmxzhcx
     * @return
     */
    @AutoLog(value = "客户账户明细综合查询-编辑")
    @ApiOperation(value = "客户账户明细综合查询-编辑", notes = "客户账户明细综合查询-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Khzhmxzhcx khzhmxzhcx) {
        khzhmxzhcxService.updateById(khzhmxzhcx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户账户明细综合查询-通过id删除")
    @ApiOperation(value = "客户账户明细综合查询-通过id删除", notes = "客户账户明细综合查询-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khzhmxzhcxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户账户明细综合查询-批量删除")
    @ApiOperation(value = "客户账户明细综合查询-批量删除", notes = "客户账户明细综合查询-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khzhmxzhcxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户账户明细综合查询-通过id查询")
    @ApiOperation(value = "客户账户明细综合查询-通过id查询", notes = "客户账户明细综合查询-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Khzhmxzhcx khzhmxzhcx = khzhmxzhcxService.getById(id);
        return Result.ok(khzhmxzhcx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request,Khzhmxzhcx khzhmxzhcx) {
        return super.exportXls(request, khzhmxzhcx, Khzhmxzhcx.class, "客户账户明细综合查询");
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
        return super.importExcel(request, response, Khzhmxzhcx.class);
    }

	/**
	 * 客户账户名字综合查询-提取
	 * @param jsonObject
	 * @return
	 */
	@AutoLog(value = "客户账户明细综合查询-提取")
	@ApiOperation(value = "客户账户明细综合查询-提取", notes = "客户账户明细综合查询-提取")
	@RequestMapping("/init")
	public Result<?> init(@RequestBody JSONObject jsonObject) {
		Result result = new Result<>();
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
    	String mastAcct = jsonObject.getString("zh");
		if (mastAcct.length() == 21) {
		    String paramOne = mastAcct.substring(0, 17);
            String paramTwo = mastAcct.substring(17, 21);
            String AccNo = khzhmxzhcxService.getAccNoByMastAcctAndSubAcctNo(paramOne, paramTwo);
            if (AccNo != null) {
                mastAcct = AccNo;
            }
		}
		String acctType = jsonObject.getString("zhlx");
		String jyrqQ = jsonObject.getString("jyrqQ").replace("-", "");
		String jyrqZ = jsonObject.getString("jyrqZ").replace("-", "");
		HashMap<String, String> params = new HashMap<>();
		params.put("i_zh", mastAcct);
		params.put("p_ksrq", jyrqQ);
		params.put("p_jsrq", jyrqZ);
        params.put("i_zhlx", acctType);
		 boolean flag = false;
		 if ("1".equalsIgnoreCase(acctType)) {
		     params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_khzhmxzhcx_nbz");
		     // count_tjbb_ckyw_khzhmxzhcx_nbz
		 	flag = EtlUtil.callEtl("tjbb_common_init", params, 20);
		 } else if ("2".equalsIgnoreCase(acctType)) {
		     params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_khzhmxzhcx_ckzh");
		     // count_tjbb_ckyw_khzhmxzhcx_ckzh
		 	flag = EtlUtil.callEtl("tjbb_common_init", params, 20);
		 }
		 result.setSuccess(flag);
		return result;
	}

}
