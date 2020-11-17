package com.koabs.web.config;

import com.koabs.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PropertyConfigurer extends PropertySourcesPlaceholderConfigurer implements ResourceLoaderAware, InitializingBean {

    private static Properties props;
    private static final Logger logger = LoggerFactory.getLogger(PropertyConfigurer.class);


    private ResourceLoader loader;
    private String[]       locationNames;

    public PropertyConfigurer(){

        setIgnoreUnresolvablePlaceholders(true);
        setIgnoreResourceNotFound(true);
        setLocalOverride(true);
    }

    @Override
    public void setResourceLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    public void setLocationNames(String[] locations) {
        this.locationNames = locations;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.notNull(loader, "no resourceLoader");

        if (locationNames != null) {
            List<Resource> resources = new ArrayList<Resource>(locationNames.length);
            String newlocation ="";
            for (String location : locationNames) {

                location = trimToNull(location);

                if (location != null) {
                    if(StrUtil.isEmpty(newlocation)){
                        // 从环境变量中加载
                        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
                        Matcher matcher = pattern.matcher(location);
                        while (matcher.find()) {
                            String param = matcher.group(1);
                            String path = System.getProperty(param);
                            if(StrUtil.isEmpty(path)){
                                path = System.getenv(param);
                            }
                            logger.info("加载参数{},替换{}", matcher.group(0), path);
                            // 替换未获取的值
                            location = path;
                        }
                        if(!StrUtil.isEmpty(location)){
                            newlocation = StrUtil.substring(location,0,location.lastIndexOf("."));
                        }
                    }
                    if(!StrUtil.isEmpty(location)){
                        resources.add(loader.getResource(location));
                    }
                }
            }
            //file:D:/workspace/dev/uhome/config/uhome-config-ecommerce.properties
            String prefix = getPrefix();
            if(!StrUtil.isEmpty(prefix) && !StrUtil.isEmpty(newlocation)){
                newlocation = newlocation +"-" + prefix + ".properties";
                logger.info("{}系统加载个性化配置文件：{}",prefix,newlocation);
                try {
                    Properties localProps = new Properties();
                    Resource resource=loader.getResource(newlocation);
                    if(resource.exists()){
                        PropertiesLoaderUtils.fillProperties(localProps, new EncodedResource(resource, "UTF-8"));
                        super.setProperties(localProps);
                    }

                } catch (IOException var7) {
                    if(logger.isWarnEnabled()) {
                        logger.warn("Could not load properties from " + newlocation + ": " + var7.getMessage());
                    }
                }
            }
            super.setLocations(resources.toArray(new Resource[resources.size()]));
        }
    }

    private String trimToNull(String str) {
        if (str == null) {
            return null;
        }

        String result = str.trim();

        if (result == null || result.length() == 0) {
            return null;
        }

        return result;
    }



    @Override
    public void setProperties(Properties pro){
        props = pro;
    }

    public static Properties getProperties(){
        return props;
    }


    /**
     从环境变量中获取，系统编码标识
      */
    protected String getPrefix(){
        String extPropertiesCode = "service.context.code";

        // 环境变量中获取
        String serviceCode = System.getProperty(extPropertiesCode);

        if(StrUtil.isEmpty(serviceCode)){
            serviceCode = System.getenv(extPropertiesCode);
        }

        return serviceCode;
    }

}