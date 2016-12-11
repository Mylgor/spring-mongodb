package com.dvasyukov.dao;

import com.dvasyukov.model.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstrumentDao {
    @Autowired private MongoOperations mongoOperations;

    public void save(Instrument instrument) {
        mongoOperations.save(instrument);
    }

    public Instrument get(Long id) {
        return mongoOperations.findOne(Query.query(Criteria.where("_id").is(id)), Instrument.class);
    }

    public List<Instrument> getAll() {
        return mongoOperations.findAll(Instrument.class);
    }

    public void remove(Long id) {
        mongoOperations.remove(Query.query(Criteria.where("_id").is(id)), Instrument.class);
    }
}
