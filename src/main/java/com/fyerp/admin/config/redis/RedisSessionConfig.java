/*
 * 作者：xuda
 * 创建时间：18-5-4 上午10:06
 * 模块名称：admin
 */

/*
 * 作者：xuda
 * 创建时间：18-5-4 上午9:46
 * 模块名称：admin
 */

package com.fyerp.admin.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//maxInactiveIntervalInSeconds session超时时间,单位秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
public class RedisSessionConfig {
}
