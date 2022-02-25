package com.test;

import com.sample.service.RegionService;
import com.sample.service.WeatherService;
import com.sample.service.impl.RegionServiceImpl;
import com.sample.service.impl.WeatherSearchServiceImpl;
import com.sample.util.ExecutorUtil;
import com.sample.util.OkHttpClientUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月24日22:57:35
 **/
public class TestWeather {
    private static final Logger log = LoggerFactory.getLogger(OkHttpClientUtil.class);

    private String[][] samples;

    private Map<String, Map<String, Set<String>>> regionMap;

    private RegionService regionService;

    private WeatherService weatherService;

    @Before
    public void testInit() {
        regionService = new RegionServiceImpl();
        weatherService = new WeatherSearchServiceImpl();
        regionMap = new ConcurrentHashMap<>();
        samples = assembleSampleData();
    }

    private String[][] assembleSampleData() {
        String[][] samples = new String[10][3];
        int i = 0;
        samples[i++] = new String[]{"10105", "01", "01"};
        samples[i++] = new String[]{"10105", "01", "02"};
        samples[i++] = new String[]{"10105", "01", "03"};
        samples[i++] = new String[]{"10105", "99", "01"};
        samples[i++] = new String[]{"10105", "01", "99"};

        samples[i++] = new String[]{"10119", "01", "01"};
        samples[i++] = new String[]{"10120", "02", "03"};
        samples[i++] = new String[]{"10122", "03", "04"};

        samples[i++] = new String[]{"", "", ""};
        samples[i++] = new String[]{"9999", "99", "99"};
        return samples;
    }

    //@Before
    public void testInitRegionData() {
        regionService = new RegionServiceImpl();
        weatherService = new WeatherSearchServiceImpl();

        long begin = System.currentTimeMillis();
        Map<String, String> provinces = regionService.getProvinces();
        provinces.keySet().parallelStream().forEach(province -> {
            Map<String, String> cities = regionService.getCities(province);
            Map<String, Set<String>> map = new ConcurrentHashMap<>();
            cities.keySet().parallelStream().forEach(city -> {
                Map<String, String> counties = regionService.getCounties(province, city);
                if (counties != null) {
                    map.put(city, counties.keySet());
                }
            });
            regionMap.put(province, map);
        });
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    @Test
    public void testGetProvinces() {
        Map<String, String> provinces = regionService.getProvinces();
        Assert.assertNotNull(provinces);
    }

    @Test
    public void testGetCities() {
        String provinceCode = "10119";
        Map<String, String> cities = regionService.getCities(provinceCode);
        Assert.assertNotNull(cities);
    }

    @Test
    public void testGetCounties() {
        String province = "10119", city = "01";
        Map<String, String> counties = regionService.getCounties(province, city);
        Assert.assertNotNull(counties);
    }

    @Test
    public void testGetWeather() {
        String province = "10119", city = "04", county = "01";
        Optional<Double> accurateTemperature = weatherService.getAccurateTemperature(province, city, county);
        Assert.assertNotNull(accurateTemperature.orElse(null));
    }

    @Test
    public void testWrongProvince() {
        String province = null, city = "", county = " ";
        Optional<Double> accurateTemperature = weatherService.getAccurateTemperature(province, city, county);
        Assert.assertNotNull(accurateTemperature.orElse(null));
    }

    @Test
    public void testBatchQuery() {
        for (int i = 0; i < 100; i++) {
            String[] param = samples[new Random().nextInt(1000) % 10];
            ExecutorUtil.taskExecutor.execute(() -> {
                try {
                    weatherService.getAccurateTemperature(param[0], param[1], param[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Assert.assertNotNull(Boolean.TRUE);
    }
}