package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.99.86.97");
    assertEquals(ipLocation, "<GeoIP><Country>KZ</Country><State>02</State></GeoIP>");
  }
}
