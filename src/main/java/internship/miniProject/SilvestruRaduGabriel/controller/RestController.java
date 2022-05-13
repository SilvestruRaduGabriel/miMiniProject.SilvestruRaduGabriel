package internship.miniProject.SilvestruRaduGabriel.controller;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import internship.miniProject.SilvestruRaduGabriel.execeptions.IdNotFoundException;
import internship.miniProject.SilvestruRaduGabriel.model.NewData;
import internship.miniProject.SilvestruRaduGabriel.model.Offence;
import internship.miniProject.SilvestruRaduGabriel.model.Record;
import internship.miniProject.SilvestruRaduGabriel.repository.RecordRepository;
import internship.miniProject.SilvestruRaduGabriel.serivce.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @DeleteMapping("/{cmplnt_num}")
    public boolean deleteById(@PathVariable("cmplnt_num") long id) throws IdNotFoundException {
        return recordService.deleteById(id);
        // Am folosit recordService pentru ca "deleteById"
        // implementat de spring este void, iar noi avem nevoie de boolean
    }

        @PostMapping("/")
    public ResponseEntity postMethod(@RequestBody NewData newData) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Record rec = new Record();
        rec.setCmplnt_num(newData.getCmplnt_num()); // Aici iau campurile cerute
        rec.setKy_cd(newData.getKy_cd());
        recordRepository.save(rec);  // Prin aceasta salvam in baza de date.

        Writer writer = Files.newBufferedWriter(Paths.get("F:\\Spring\\miMiniProject.SilvestruRaduGabriel\\src\\main\\resources\\input.csv"));
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .build();
        List<Record> recordList = new ArrayList<>(recordRepository.findAll());
        recordList.add(rec);

        sbc.write(recordList);
        writer.close();

        return new ResponseEntity<Record>(rec , HttpStatus.OK);
    }
}
