package org.cmms.modules.dklldj.csszgl.gzbdssz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.csszgl.gzbdssz.entity.Gzbdssz;
import org.cmms.modules.dklldj.csszgl.gzbdssz.service.IGzbdsszService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 规则表达式设置
 * @Author: Penghr
 * @Date:   2020-03-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="规则表达式设置")
@RestController
@RequestMapping("/csszgl/gzbdssz")
public class GzbdsszController extends JeecgController<Gzbdssz, IGzbdsszService> {
	 @Autowired
	 private IGzbdsszService gzbdsszService;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Value(value = "${common.path.upload}")
	 private String uploadPath;
	 @Autowired
	 private RedisUtil redisUtil;

	/**
	 * 分页列表查询
	 * @param gzbdssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "规则表达式设置-分页列表查询")
	@ApiOperation(value="规则表达式设置-分页列表查询", notes="规则表达式设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Gzbdssz gzbdssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
		gzbdssz.setQydm(qydm);
		Result<IPage<Gzbdssz>> result = new Result<IPage<Gzbdssz>>();
		QueryWrapper<Gzbdssz> queryWrapper = QueryGenerator.initQueryWrapper(gzbdssz, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IGzbdsszService.class, gzbdsszService, pageNo, pageSize, queryWrapper, "zbgzid");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * @param gzbdssz
	 * @return
	 */
	@AutoLog(value = "规则表达式设置-添加")
	@ApiOperation(value="规则表达式设置-添加", notes="规则表达式设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Gzbdssz gzbdssz) {
		try {
			String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
		    gzbdssz.setQydm(qydm);
            gzbdsszService.save(gzbdssz);
        } catch (Exception e) {
            log.error("规则表达式添加失败",e.getMessage());
            return Result.error("规则表达式添加失败!");
        }
		return Result.ok("规则表达式添加成功！");
	}

	/**
	 * 编辑
	 * @param gzbdssz
	 * @return
	 */
	@AutoLog(value = "规则表达式设置-编辑")
	@ApiOperation(value="规则表达式设置-编辑", notes="规则表达式设置-编辑")
	@PutMapping(value = "/edit")
	public Result<Gzbdssz> edit(@RequestBody Gzbdssz gzbdssz) {
	    Result<Gzbdssz> result = new Result<>();
	    String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
	    QueryWrapper<Gzbdssz> updateWrapper = new QueryWrapper<>();
	    updateWrapper.eq("qydm", qydm);
	    updateWrapper.eq("zbgzid", gzbdssz.getZbgzid());
	    updateWrapper.eq("bdskey", gzbdssz.getBdskey());
	    boolean okFlag = gzbdsszService.update(gzbdssz, updateWrapper);
        if (okFlag) {
            result.success("规则表达式编辑成功!");
        } else {
            result.error500("规则表达式编辑失败");
        }
        return result;
	}

	/**
	 * 通过QYDM、ZBGZID、BDSKEY删除
	 * @param zbgzid,bdskey
	 * @return
	 */
	@AutoLog(value = "规则表达式设置-通过id删除")
	@ApiOperation(value="规则表达式设置-通过id删除", notes="规则表达式设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="zbgzid",required=true) String zbgzid,
                            @RequestParam(name="bdskey",required=true) String bdskey) {
	    try {
	    	String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
	        QueryWrapper<Gzbdssz> queryWrapper = new QueryWrapper<>();
	        queryWrapper.eq("zbgzid",zbgzid);
	        queryWrapper.eq("bdskey",bdskey);
	        queryWrapper.eq("qydm",qydm);
	    	gzbdsszService.remove(queryWrapper);
        } catch (Exception e) {
            log.error("规则表达式删除失败"+e.getMessage());
            return Result.error("规则表达式删除失败!");
        }
		return Result.ok("规则表达式删除成功!");
	}

    /**
     * 导出excel
     *
     * @param request
     * @param gzbdssz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Gzbdssz gzbdssz) {
      return super.exportXls(request, gzbdssz, Gzbdssz.class, "规则表达式设置");
    }

}
