package com.edu.hcmuaf.fit.webbanvemaybay.dao;

import com.edu.hcmuaf.fit.webbanvemaybay.models.DaiLy;
import org.jdbi.v3.core.Jdbi;

public class DaiLyDAO extends DBContext{
    public DaiLy getDaiLy() {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "select * from dai_ly";
                return h.createQuery(q).mapToBean(DaiLy.class).findFirst().orElse(null);
            });
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
