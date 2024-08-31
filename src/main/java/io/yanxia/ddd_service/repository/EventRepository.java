package io.yanxia.ddd_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.yanxia.ddd_service.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}
