package org.cmms.config.mybatis;

import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.upsert.Upsert;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.SQLParser;
import org.springframework.stereotype.Component;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Properties;

/**
 * mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 * @Author scott
 * @Date  2019-01-19
 *
 */
@Slf4j
@Component
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class SqlChangeInterceptor implements Interceptor {


	public static class CustomSqlSource implements SqlSource {

		private BoundSql boundSql;

		protected CustomSqlSource(BoundSql boundSql){
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object o) {
			return boundSql;
		}
	}
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Connection connection = (Connection)invocation.getArgs()[0];
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		String url = databaseMetaData.getURL();
		if(url.indexOf("impala")>=0||url.indexOf("hive")>=0) {
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
					SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
			MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
			mappedStatement.getStatementLog();
			String mapperMethod = mappedStatement.getId();
			BoundSql boundSql = statementHandler.getBoundSql();
			String sql = boundSql.getSql();
			String ds = "";
			if (url.indexOf("impala") >= 0) {
				ds = url.substring(url.lastIndexOf("/") + 1, url.indexOf(";"));
			} else {
				ds = url.substring(url.lastIndexOf("/") + 1, url.length());
			}
			String sqls = replace(sql, ds);

			//通过反射修改sql语句
			Field field = boundSql.getClass().getDeclaredField("sql");
			field.setAccessible(true);
			field.set(boundSql, sqls);
		}
		/*

			BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),
												 sql, //sql替换  唯一发生改变的地方
												 boundSql.getParameterMappings(),
				                                 boundSql.getParameterObject() );

			MappedStatement.Builder build = new MappedStatement.Builder(
					mappedStatement.getConfiguration(),
					mappedStatement.getId(),
					new CustomSqlSource(newBoundSql),
					mappedStatement.getSqlCommandType()
			);

			build.resource(mappedStatement.getResource());
			build.fetchSize(mappedStatement.getFetchSize());
			build.statementType(mappedStatement.getStatementType());
			build.keyGenerator(mappedStatement.getKeyGenerator());
			build.timeout(mappedStatement.getTimeout());
			build.parameterMap(mappedStatement.getParameterMap());
			build.resultMaps(mappedStatement.getResultMaps());
			build.cache(mappedStatement.getCache());
			MappedStatement newStmt = build.build();
			//替换原来的MappedStatement
			//这部分看怎么往里面塞值****************************************

			invocation.getArgs()[1] = newStmt;*//*
			//jdbc:impala://192.168.0.220:21050/ods_375;AuthMech=3;UID=superior;PWD=superior
			//jdbc:hive2://192.168.0.155:10000/rate
			//String sql = handleReplace(bSql);
		}*/
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

	private LoginUser getLoginUser() {
		LoginUser sysUser = null;
		try {
			sysUser = SecurityUtils.getSubject().getPrincipal() != null ? (LoginUser) SecurityUtils.getSubject().getPrincipal() : null;
		} catch (Exception e) {
			//e.printStackTrace();
			sysUser = null;
		}
		return sysUser;
	}

	private String replace(String sql,String scheme) throws JSQLParserException {
		Statement stmt = (Statement) CCJSqlParserUtil.parse(sql);
		if(stmt instanceof Insert){
			Insert insert = (Insert)stmt;
			return SQLParser.doInsert(insert, scheme);
		}else if(stmt instanceof Update){
			Update update = (Update) stmt;
			return SQLParser.doUpdate(update, scheme);
		}else if(stmt instanceof Delete){
			Delete delete = (Delete) stmt;
			return SQLParser.doDelete(delete, scheme);
		}else if(stmt instanceof Select){
			Select select = (Select)stmt;
			return SQLParser.doSelect(select, scheme);
		}else if(stmt instanceof Upsert){
			return sql;
		}
		throw new RuntimeException("非法SQL语句 不可能执行到这里");
	}

}
