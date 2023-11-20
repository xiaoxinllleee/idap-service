package org.cmms.modules.khgl.ckkh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.ckkh.entity.KhgxglCkzhghxx;
import org.cmms.modules.khgl.ckkh.service.IKhgxglCkzhghxxService;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 存款账号管户信息
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款账号管户信息")
@RestController
@RequestMapping("/app/khgxglCkzhghxx")
public class KhgxglCkzhghxxController extends JeecgController<KhgxglCkzhghxx, IKhgxglCkzhghxxService> {

	/**
	 * 分页列表查询
	 *
	 * @param khgxglCkzhghxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款账号管户信息-分页列表查询")
	@ApiOperation(value="存款账号管户信息-分页列表查询", notes="存款账号管户信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhgxglCkzhghxx khgxglCkzhghxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhgxglCkzhghxx> queryWrapper = QueryGenerator.initQueryWrapper(khgxglCkzhghxx, req.getParameterMap());
		Page<KhgxglCkzhghxx> page = new Page<KhgxglCkzhghxx>(pageNo, pageSize);
		IPage<KhgxglCkzhghxx> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

}
