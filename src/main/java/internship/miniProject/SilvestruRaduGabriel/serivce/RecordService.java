package internship.miniProject.SilvestruRaduGabriel.serivce;

import internship.miniProject.SilvestruRaduGabriel.execeptions.IdNotFoundException;
import internship.miniProject.SilvestruRaduGabriel.model.Record;
import internship.miniProject.SilvestruRaduGabriel.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public boolean deleteById(long id) throws IdNotFoundException {
        Record record = recordRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Record with Id " + id + " was not found!"));
        if (record != null) {
            recordRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
