package com.mission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.Fundings;

@Repository
public interface FundingsDao extends JpaRepository<Fundings, Integer> {

}
