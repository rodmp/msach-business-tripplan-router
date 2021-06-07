package com.meep.tripplan.router.api;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.meep.tripplan.router.business.TripplanCacheManagment;
import com.meep.tripplan.router.model.TripplanResourceDiffResponse;

/**
 * 
 * @author clase test para el componente TripplanResourcesController.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TripplanResourcesControllerTest {

  /**
   * 
   */
  @InjectMocks
  private TripplanResourcesController tripplanResourcesController;
  
  /**
   * 
   */
  @Mock
  private TripplanCacheManagment tripplanCacheManagmentService;
  
  /**
   * Metodo de configuracion para las unit test.
   */
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
  
  /**
   * 
   */
  @Test
  public void getDifferencesResources() {
    TripplanResourceDiffResponse test = new TripplanResourceDiffResponse();
    when(tripplanCacheManagmentService.getDifferenceResorces()).thenReturn(test);
    
    tripplanResourcesController.getDifferencesResources();
    
    assertNotNull(test);
  }
}
