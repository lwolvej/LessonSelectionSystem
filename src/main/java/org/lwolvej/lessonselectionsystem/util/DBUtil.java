package org.lwolvej.lessonselectionsystem.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 *
 * @author LwolveJ
 */
public class DBUtil {

    //数据库连接地址
    private static final String URL = "jdbc:mysql://localhost:3306/lesson_system?useUnicode=true&characterEncoding=utf8&useSSL=true";

    //数据库JDBC
    private static final String JDBC = "com.mysql.cj.jdbc.Driver";

    //数据库用户名
    private static final String USER = "root";

    //数据库密码
    private static final String PWD = "1234";

    //连接池资源
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setDriverClassName(JDBC);
        config.setUsername(USER);
        config.setPassword(PWD);
        // 设置开启SQL缓存
        config.addDataSourceProperty("cachePrepStmts", "true");
        // 设置SQL的缓存大小
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        // 设置SQL的缓存限制
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        // 设置每个连接的存活时长
        config.addDataSourceProperty("maxLifetime", 60000);
        // 设置多长时间就判定未连接失败
        config.addDataSourceProperty("connectionTimeout", 30000);
        // 设置连接池中最大连接数量
        config.addDataSourceProperty("maximumPoolSize", 30);
        //开启mysql服务端准备
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");

        setDataSource(new HikariDataSource(config));
    }

    public static Connection getConn() {
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void freeConn(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static HikariDataSource getDataSource() {
        return dataSource;
    }

    private static void setDataSource(HikariDataSource dataSource) {
        DBUtil.dataSource = dataSource;
    }
}
