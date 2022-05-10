package internship.miniProject.SilvestruRaduGabriel.config;

import internship.miniProject.SilvestruRaduGabriel.model.Record;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;     //Un Job are Reader , Processor si Writer

    @Autowired
    EntityManagerFactory emf; //Il folosim la Writerul de JPA sa scriem baza de date

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Record, Record>chunk(5)
                .reader(reader()) // Aici citeste din CSV
                .writer(writer()) // Aici il scrie in SQL
                .build(); //
    }



    @Bean
    public FlatFileItemReader<Record> reader() {
        return new FlatFileItemReaderBuilder<Record>()
                .name("recordItemReader")
                .resource(new ClassPathResource("input.csv"))
                .delimited()
                .names(new String[]{"cmplnt_num", "cmplnt_fr_dt", "cmplnt_fr_tm", "cmplnt_to_dt", "cmplnt_to_tm", "addr_pct_cd", "rpt_dt", "ky_cd", "ofns_desc", "pd_cd", "pd_desc", "crm_atpt_cptd_cd", "law_cat_cd", "boro_nm", "loc_of_occur_desc", "prem_typ_desc", "juris_desc", "jurisdiction_code", "parks_nm", "hadevelopt", "housing_psa", "x_coord_cd", "y_coord_cd", "susp_age_group", "susp_race", "susp_sex", "transit_district", "latitude", "longitude", "lat_lon", "patrol_boro", "station_name", "vic_age_group", "vic_race", "vic_sex"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Record>() {{
                    setTargetType(Record.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public JpaItemWriter<Record> writer(){
        JpaItemWriterBuilder<Record> builder = new JpaItemWriterBuilder();
        builder.entityManagerFactory(emf);
       return builder.build();
    }
}
