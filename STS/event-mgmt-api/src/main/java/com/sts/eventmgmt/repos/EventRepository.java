package com.sts.eventmgmt.repos;

import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sts.eventmgmt.entities.Event;
import com.sts.eventmgmt.entities.Participant;

//public interface EventRepository extends CrudRepository<Event, Long> {
//
//}
public interface EventRepository extends PagingAndSortingRepository<Event, Long>{
//	List<Event> findByName(@Param("name") String name);
		Page<Event> findByName(@Param("name") String name, Pageable pageable);
		
		Page<Event> findByNameAndZoneId(@Param("name") String name, @Param("zoneId") ZoneId zoneId, Pageable pageable);
		
//		Page<Event> findByParticipants(@Param("participants") Set<Participant> participants, Pageable pageable); // it does not work
		
		Page<Event> findByStarted(@Param("started") Boolean started, Pageable pageable);
}