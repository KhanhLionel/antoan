package com.edu.hcmuaf.fit.webbanvemaybay.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class DBContext {
    private static Jdbi jdbi;

    public static Jdbi get() {
        if (jdbi == null) {
            // Cấu hình kết nối MySQL
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/db");
            dataSource.setUser("root");
            dataSource.setPassword("");

            jdbi = Jdbi.create(dataSource);
            jdbi.installPlugin(new SqlObjectPlugin());
        }
        return jdbi;
    }
}
