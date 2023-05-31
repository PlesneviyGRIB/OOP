package com.example.lab_56.repositories;

import com.example.lab_56.models.PriceInfo;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;

public interface PriceInfoRepository extends QuerydslJdbcRepository<PriceInfo, String> {
}
