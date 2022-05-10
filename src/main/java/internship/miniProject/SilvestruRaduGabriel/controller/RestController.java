package internship.miniProject.SilvestruRaduGabriel.controller;

import internship.miniProject.SilvestruRaduGabriel.execeptions.IdNotFoundException;
import internship.miniProject.SilvestruRaduGabriel.model.Offence;
import internship.miniProject.SilvestruRaduGabriel.repository.RecordRepository;
import internship.miniProject.SilvestruRaduGabriel.serivce.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/dataset")
public class RestController {

    @Autowired
    private RecordService recordService;

    @Autowired
    RecordRepository recordRepository;

    @GetMapping("/stats/total")
    public long totals() {
        return recordRepository.count();
    }

    @GetMapping("/stats/offenses")
    public List<Offence> stats() {
        List<Offence> recordList = recordRepository.selectAndCountBy();
        return recordList;
    }

    ///dataset/${id} – sterge din fisierul CSV evenimentul cu ID-ul specificat
    // (fie ca request param, fie ca path variable) si raspunde cu true/false la succes.
    @DeleteMapping("/{cmplnt_num}")
    public boolean deleteById(@PathVariable("cmplnt_num") long id) throws IdNotFoundException {
        return recordService.deleteById(id);
    }
}
