package com.trackservice.repository.track;

import com.trackservice.entity.track.TrackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackLogRepository extends JpaRepository<TrackLog, Long> {

}
