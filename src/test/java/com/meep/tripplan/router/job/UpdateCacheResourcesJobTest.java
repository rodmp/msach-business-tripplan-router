package com.meep.tripplan.router.job;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meep.tripplan.router.business.TripplanRouterConsume;
import com.meep.tripplan.router.constant.FeignConstants;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.service.TripplanCacheManagmentService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 
 * @author .
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UpdateCacheResourcesJobTest {

  /**
   * 
   */
  @InjectMocks
  private UpdateCacheResourcesJob updateCacheResourcesJob;

  /**
   * 
   */
  @Mock
  private FeignConstants feignConstants;

  /**
   * 
   */
  @Mock
  private TripplanRouterConsume tripplanRouterClient;

  /**
   * 
   */
  @Mock
  private TripplanCacheManagmentService tripplanCacheManagmentService;

  /**
   * Metodo de configuracion para las unit test.
   */
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void updateTripplanRoutersCacheInfoTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String objects =
        "[{\"id\":\"PT-LIS-A00327\",\"name\":\"86ZB13\",\"x\":-9.147293,\"y\":38.716129,\"licencePlate\":\"86ZB13\",\"range\":32,\"batteryLevel\":42,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00211\",\"name\":\"27VO14\",\"x\":-9.147915,\"y\":38.716156,\"licencePlate\":\"27VO14\",\"range\":19,\"batteryLevel\":25,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00446\",\"name\":\"15ZC91\",\"x\":-9.158262,\"y\":38.712963,\"licencePlate\":\"15ZC91\",\"range\":13,\"batteryLevel\":17,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00314\",\"name\":\"87ZB93\",\"x\":-9.156505,\"y\":38.718723,\"licencePlate\":\"87ZB93\",\"range\":52,\"batteryLevel\":69,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00249\",\"name\":\"69VP34\",\"x\":-9.146637,\"y\":38.724163,\"licencePlate\":\"69VP34\",\"range\":0,\"batteryLevel\":0,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00179\",\"name\":\"18VL68\",\"x\":-9.148697,\"y\":38.713818,\"licencePlate\":\"18VL68\",\"range\":45,\"batteryLevel\":60,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00116\",\"name\":\"11VJ89\",\"x\":-9.156228,\"y\":38.731293,\"licencePlate\":\"11VJ89\",\"range\":24,\"batteryLevel\":32,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00274\",\"name\":\"66VP20\",\"x\":-9.145382,\"y\":38.732357,\"licencePlate\":\"66VP20\",\"range\":13,\"batteryLevel\":17,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00155\",\"name\":\"80VJ06\",\"x\":-9.147803,\"y\":38.716366,\"licencePlate\":\"80VJ06\",\"range\":35,\"batteryLevel\":46,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00378\",\"name\":\"01ZB83\",\"x\":-9.145255,\"y\":38.715508,\"licencePlate\":\"01ZB83\",\"range\":37,\"batteryLevel\":49,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00237\",\"name\":\"52VO82\",\"x\":-9.156649,\"y\":38.718472,\"licencePlate\":\"52VO82\",\"range\":52,\"batteryLevel\":69,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00062\",\"name\":\"82VI58\",\"x\":-9.151002,\"y\":38.738564,\"licencePlate\":\"82VI58\",\"range\":41,\"batteryLevel\":54,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00096\",\"name\":\"10VJ89\",\"x\":-9.150962,\"y\":38.717426,\"licencePlate\":\"10VJ89\",\"range\":16,\"batteryLevel\":21,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00448\",\"name\":\"16ZC35\",\"x\":-9.156087,\"y\":38.723347,\"licencePlate\":\"16ZC35\",\"range\":60,\"batteryLevel\":80,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00166\",\"name\":\"03VL47\",\"x\":-9.147905,\"y\":38.716362,\"licencePlate\":\"03VL47\",\"range\":42,\"batteryLevel\":56,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00356\",\"name\":\"02ZB24\",\"x\":-9.14732,\"y\":38.712227,\"licencePlate\":\"02ZB24\",\"range\":17,\"batteryLevel\":22,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00057\",\"name\":\"83VI76\",\"x\":-9.144695,\"y\":38.715347,\"licencePlate\":\"83VI76\",\"range\":90,\"batteryLevel\":100,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00464\",\"name\":\"15ZC49\",\"x\":-9.147694,\"y\":38.712479,\"licencePlate\":\"15ZC49\",\"range\":20,\"batteryLevel\":26,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00230\",\"name\":\"52VO15\",\"x\":-9.148995,\"y\":38.719921,\"licencePlate\":\"52VO15\",\"range\":9,\"batteryLevel\":12,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00181\",\"name\":\"17VL90\",\"x\":-9.146287,\"y\":38.711426,\"licencePlate\":\"17VL90\",\"range\":27,\"batteryLevel\":36,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00064\",\"name\":\"84VI90\",\"x\":-9.148731,\"y\":38.715469,\"licencePlate\":\"84VI90\",\"range\":12,\"batteryLevel\":16,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00168\",\"name\":\"01VL92\",\"x\":-9.144585,\"y\":38.713474,\"licencePlate\":\"01VL92\",\"range\":48,\"batteryLevel\":64,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00393\",\"name\":\"02ZB58\",\"x\":-9.147678,\"y\":38.71138,\"licencePlate\":\"02ZB58\",\"range\":33,\"batteryLevel\":44,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00475\",\"name\":\"16ZC17\",\"x\":-9.15948,\"y\":38.724628,\"licencePlate\":\"16ZC17\",\"range\":22,\"batteryLevel\":29,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00103\",\"name\":\"11VJ34\",\"x\":-9.156642,\"y\":38.718616,\"licencePlate\":\"11VJ34\",\"range\":35,\"batteryLevel\":46,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00412\",\"name\":\"04ZB43\",\"x\":-9.145095,\"y\":38.717571,\"licencePlate\":\"04ZB43\",\"range\":28,\"batteryLevel\":37,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00174\",\"name\":\"18VL32\",\"x\":-9.139869,\"y\":38.733467,\"licencePlate\":\"18VL32\",\"range\":39,\"batteryLevel\":52,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00347\",\"name\":\"05ZC38\",\"x\":-9.147827,\"y\":38.716328,\"licencePlate\":\"05ZC38\",\"range\":21,\"batteryLevel\":28,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00145\",\"name\":\"13VJ91\",\"x\":-9.154475,\"y\":38.723373,\"licencePlate\":\"13VJ91\",\"range\":40,\"batteryLevel\":53,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00081\",\"name\":\"81VI84\",\"x\":-9.152204,\"y\":38.718647,\"licencePlate\":\"81VI84\",\"range\":48,\"batteryLevel\":64,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00471\",\"name\":\"14ZC18\",\"x\":-9.139049,\"y\":38.716026,\"licencePlate\":\"14ZC18\",\"range\":25,\"batteryLevel\":33,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00376\",\"name\":\"03ZB42\",\"x\":-9.141525,\"y\":38.726749,\"licencePlate\":\"03ZB42\",\"range\":25,\"batteryLevel\":33,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00045\",\"name\":\"81VI55\",\"x\":-9.151011,\"y\":38.738613,\"licencePlate\":\"81VI55\",\"range\":32,\"batteryLevel\":42,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00436\",\"name\":\"01ZB68\",\"x\":-9.158855,\"y\":38.72715,\"licencePlate\":\"01ZB68\",\"range\":22,\"batteryLevel\":29,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00036\",\"name\":\"25VG27\",\"x\":-9.14897,\"y\":38.719997,\"licencePlate\":\"25VG27\",\"range\":27,\"batteryLevel\":36,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00075\",\"name\":\"81VI79\",\"x\":-9.1465,\"y\":38.717598,\"licencePlate\":\"81VI79\",\"range\":23,\"batteryLevel\":30,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00033\",\"name\":\"46VF91\",\"x\":-9.146297,\"y\":38.730469,\"licencePlate\":\"46VF91\",\"range\":45,\"batteryLevel\":60,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00450\",\"name\":\"02ZB84\",\"x\":-9.152212,\"y\":38.738281,\"licencePlate\":\"02ZB84\",\"range\":17,\"batteryLevel\":22,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473},{\"id\":\"PT-LIS-A00468\",\"name\":\"15ZC90\",\"x\":-9.159882,\"y\":38.718258,\"licencePlate\":\"15ZC90\",\"range\":19,\"batteryLevel\":25,\"helmets\":2,\"model\":\"Askoll\",\"resourceImageId\":\"vehicle_gen_ecooltra\",\"realTimeData\":true,\"resourceType\":\"MOPED\",\"companyZoneId\":473}]";

    List<Resource> resources = mapper.readValue(objects, new TypeReference<List<Resource>>() {});

    when(tripplanRouterClient.getTripplanResources()).thenReturn(resources);
    when(feignConstants.getTripplanName()).thenReturn("Metodo");
    doNothing().when(tripplanCacheManagmentService).managementCacheTripplanResource(resources);
    
    updateCacheResourcesJob.updateTripplanRoutersCacheInfo();
    
    assertNotNull(resources);
  }
}
