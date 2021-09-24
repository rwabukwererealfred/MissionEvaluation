package com.mission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.Mission;
import com.mission.domain.TraineeComment;

@Repository
public interface TraineeCommentDao extends JpaRepository<TraineeComment, Long> {

	List<TraineeComment>findByMission(Mission mission);
}
