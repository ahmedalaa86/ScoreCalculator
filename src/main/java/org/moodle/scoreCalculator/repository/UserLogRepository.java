package org.moodle.scoreCalculator.repository;

import java.util.List;

import org.moodle.scoreCalculator.model.UserLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends CrudRepository<UserLog, Long> {
	List<UserLog> findByUseridAndActionAndTargetAndObjecttableAndRealuserid(Long userid,String action,String target,String objecttable,String realuserid);
}
