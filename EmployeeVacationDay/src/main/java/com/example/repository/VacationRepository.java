package com.example.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.model.Vacation;

public interface VacationRepository extends CrudRepository<Vacation, Integer> {

	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM vacations.vacation where year=:year", nativeQuery = true)
	List<Vacation> findVacatioByYear(@Param("year") String year);

	@Transactional
	@Modifying
	@Query(value = "UPDATE vacations.vacation SET remainder=:remainderId WHERE id=:id ", nativeQuery = true)
	void updateRemainder(@Param("remainderId") int remainderId, @Param("id") int id);

	// select remainder from vacations.vacation where year='2019-2020' and emp_id=1


	@Query(value = "Select * from vacations.vacation where year=:year and emp_id=:emp_id", nativeQuery = true)
	Vacation getOldRemander(@Param("year") String year, @Param("emp_id") int emp_id);

}
