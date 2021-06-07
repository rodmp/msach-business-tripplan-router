package com.meep.tripplan.router.business;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author .
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisRepositoryImplTest {

  /**
   * 
   */
  @InjectMocks
  private RedisRepositoryImplTest redisRepositoryImplTest;
  
  /**
   * 
   */
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  
  
  
}
