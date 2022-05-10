package internship.miniProject.SilvestruRaduGabriel.controller;

import internship.miniProject.SilvestruRaduGabriel.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/dataset")
public class RestController {
    @Autowired
    RecordRepository recordRepository;
    @GetMapping("/stats/total" )
    public long totals(){
        return recordRepository.count();
    }

    @GetMapping("/stats/offenses")
    public String stats(){
        recordRepository.get
    }
}
