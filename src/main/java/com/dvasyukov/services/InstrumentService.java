package com.dvasyukov.services;

import com.dvasyukov.dao.InstrumentDao;
import com.dvasyukov.dao.SequenceDao;
import com.dvasyukov.model.Instrument;
import com.dvasyukov.web.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InstrumentService {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private static final int MAX_ELEMENT_IN_FILED = 20;

    @Autowired private SequenceDao sequenceDao;
    @Autowired private InstrumentDao instrumentDao;

    private void add(Instrument instrument) {
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


    public void addRecordInDb(HttpServletRequest request) {
        String name = request.getParameter("nameInst");
        String type = request.getParameter("typeInst");
        String price = request.getParameter("priceInst");
        String dateManuf = request.getParameter("dateAddedInst");

        String messageBox = "isAdd";
        String errMessage = "Can't add record: ";
        try {
            Instrument instrument = null;
            if (checkFields(new String[]{name, type, price, dateManuf}, MAX_ELEMENT_IN_FILED)) {

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date date = format.parse(dateManuf);

                int mPrice = Integer.parseInt(price);
                if (mPrice <= 0) {
                    throw new NumberFormatException("price less 0");
                }

                instrument = new Instrument();
                instrument.setName(name);
                instrument.setType(type);
                instrument.setPrice(mPrice);
                instrument.setDateAdded(date);
                this.add(instrument);

                request.setAttribute(messageBox, "Success addition");
            }

        } catch (NumberFormatException ex) {
            request.setAttribute(messageBox, "Incorrect field 'Price'. Number must be more that 0");
            log.info(errMessage + ex.getMessage());
        } catch (InstantiationException ex) {
            request.setAttribute(messageBox, "Error addition: " + ex.getMessage());
            log.info(errMessage + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute(messageBox, "Error addition");
            log.info(errMessage + ex.getMessage());
        }
    }

    private boolean isStringNotEmpty(String str) {
        return (str != null && str.length() != 0) ? true : false;
    }

    private boolean checkFields(String[] fields, int len) throws InstantiationException {
        for (String str : fields) {
            if (str.length() > len) {
                throw new InstantiationException("Field contain more than " + len + " characters");
            }
        }

        int indexes = 0;
        if (!isStringNotEmpty(fields[indexes++]) || !isStringNotEmpty(fields[indexes++]) ||
                !isStringNotEmpty(fields[indexes++]) || !isStringNotEmpty(fields[indexes])) {
            throw new InstantiationException("Empty fields");
        }
        return true;
    }
}
