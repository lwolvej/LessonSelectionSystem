package org.lwolvej.lessonselectionsystem.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 工厂类
 *
 * @author LwolveJ
 */
public class Factory {

    private static Factory instance = new Factory();

    private Properties properties = new Properties();

    private Factory() {
        InputStream is = Factory.class
                .getClassLoader()
                .getResourceAsStream("factory.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Factory getInstance() {
        return instance;
    }

    public <T> T create(Class<T> clazz) {
        String interfaceName = clazz.getSimpleName();
        String className = properties.getProperty(interfaceName);
        try {
            T bean = (T) Class.forName(className).newInstance();
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
