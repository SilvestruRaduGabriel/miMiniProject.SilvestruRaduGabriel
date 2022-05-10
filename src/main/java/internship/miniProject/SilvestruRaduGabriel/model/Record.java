package internship.miniProject.SilvestruRaduGabriel.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Record {
    @Id
    Long cmplnt_num;
    String cmplnt_fr_dt;
    String cmplnt_fr_tm;
    String cmplnt_to_dt;
    String cmplnt_to_tm;
    String addr_pct_cd;
    String rpt_dt;
    String ky_cd;
    String ofns_desc;
    String pd_cd;
    String pd_desc;
    String crm_atpt_cptd_cd;
    String law_cat_cd;
    String boro_nm;
    String loc_of_occur_desc;
    String prem_typ_desc;
    String juris_desc;
    String jurisdiction_code;
    String parks_nm;
    String hadevelopt;
    String housing_psa;
    String x_coord_cd;
    String y_coord_cd;
    String susp_age_group;
    String susp_race;
    String susp_sex;
    String transit_district;
    String latitude;
    String longitude;
    String lat_lon;
    String patrol_boro;
    String station_name;
    String vic_age_group;
    String vic_race;
    String vic_sex;

}
