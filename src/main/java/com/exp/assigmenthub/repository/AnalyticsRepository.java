package com.exp.assigmenthub.repository;

import com.exp.assigmenthub.model.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnalyticsRepository extends JpaRepository<Analytics, UUID> {

}
