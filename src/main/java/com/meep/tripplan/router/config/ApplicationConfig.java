package com.meep.tripplan.router.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;

/**
 * Beans generales de configuración.
 * 
 * @author .
 */
@Configuration
public class ApplicationConfig {

  /**
   * Bean para validar query params, request params y body params.
   * 
   * @return postprocessor del methodvalidation.
   */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }

  /**
   * Bean para configurar RestTemplate al carga contexto de Spring
   * 
   * @return new RestTempalte.
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /**
   * Bean para la configuracion de Redis.
   * @return JedisConnectionFactory.
   */
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  /**
   * Configuracion de conexion de redis.
   * @return RedisTemplate.
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setDefaultSerializer(new StringRedisSerializer());
    return template;
  }
}
