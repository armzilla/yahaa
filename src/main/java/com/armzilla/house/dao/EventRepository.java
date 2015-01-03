package com.armzilla.house.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by arm on 1/2/15.
 */
public interface EventRepository extends ElasticsearchRepository<Event, String> {

    public Event findById(String id);
    public List<Event> findByDeviceId(String id);
    public List<Event> findByDeviceIdOrderByTimeDesc(String id);
}
