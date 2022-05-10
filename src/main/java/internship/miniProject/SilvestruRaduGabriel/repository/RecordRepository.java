package internship.miniProject.SilvestruRaduGabriel.repository;

import internship.miniProject.SilvestruRaduGabriel.model.Offence;
import internship.miniProject.SilvestruRaduGabriel.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query(value ="select new internship.miniProject.SilvestruRaduGabriel.model.Offence(r.ky_cd, count(r.ky_cd)) from Record r group by r.ky_cd", nativeQuery = false)
    public List<Offence> selectAndCountBy();
}
