package org.cmms.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Scott
 * @date: 2018/2/7
 * @description: shiro 配置类
 */


@Configuration
@Data
public class EtlConfig {

	@Value("${com.etl.loginURL:http://192.168.0.157:38029/api/weps_web/login}")
	private  String loginUrl;

	@Value("${com.etlold.loginURL:http://192.168.0.157:38029/api/weps_web/login}")
	private  String loginOldUrl;

	@Value("${com.etl.username:admin}")
	private  String username;

	@Value("${com.etl.password:admin123}")
	private  String password;

	@Value("${com.etl.callEtlUrl:http://192.168.0.157:38029/api/riverflow/riverflow/task/manual_run}")
	private  String callEtlUrl;

	@Value("${com.etlold.callEtlUrl:http://192.168.0.157:38029/api/riverflow/riverflow/task/manual_run}")
	private  String calloldEtlUrl;

	@Value("${com.etl.EtlStatusUrl:http://192.168.0.157:38029/api/riverflow/riverflow/task/query_task_status}")
	private  String etlStatusUrl;

	@Value("${com.etlold.EtlStatusUrl:http://192.168.0.157:38029/api/riverflow/riverflow/task/query_task_status}")
	private  String etlStatusOldUrl;

	@Value("${com.etlold.sshHost:192.168.0.222}")
	private  String sshHost;

	@Value("${com.etlold.sshUser:root}")
	private  String sshUser;
	@Value("${com.etlold.sshPWD:RaServer.222}")
	private  String sshPWD;

	@Value("${com.etl.etlName:数据下发ETL任务}")
	private  String etlName;

	@Value("${com.etl.dagName:etl_day调度}")
	private  String dagName;
}
