package com.dvasyukov.services;

import com.dvasyukov.dao.InstrumentDao;
import com.dvasyukov.dao.SequenceDao;
import com.dvasyukov.model.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService {
    @Autowired private SequenceDao sequenceDao;
    @Autowired private InstrumentDao instrumentDao;

    public void add(Instrument instrument) {
        instrument.setId(sequenceDao.getNextSequenceId(Instrument.COLLECTION_NAME));
        instrumentDao.save(instrument);
    }

    public void update(Instrument instrument) {
        instrumentDao.save(instrument);
    }

    public Instrument get(Long id) {
        return instrumentDao.get(id);
    }

    public List<Instrument> getAll() {
        return instrumentDao.getAll();
    }

    public void remove(Long id) {
        instrumentDao.remove(id);
    }
}
