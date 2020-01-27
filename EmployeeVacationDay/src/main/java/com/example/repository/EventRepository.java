package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Event;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

}
