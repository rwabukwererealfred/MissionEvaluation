package com.mission.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mission.domain.Mission;

@Repository
public interface MissionDao extends JpaRepository<Mission, Integer> {

	@Modifying
	@Query("update Mission u set u.status = false, u.mStatus = 'Closed', u.evaluationPercentage = :evaluationPercentage where u.id = :id")
	void updateMission(@Param(value = "id") Integer id, @Param(value="evaluationPercentage")double evaluationPercentage);
	
	@Modifying
	@Query("update Mission u set u.mStatus = 'Start' where u.id = :id")
	void startMission(@Param(value="id")Integer id);
	
	
}
