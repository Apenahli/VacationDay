package com.example.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.model.VacationMonths;

public interface VacationMonthsRepository extends CrudRepository<VacationMonths, Integer> {

	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM vacations.vacation_months where vacation_id=:id", nativeQuery = true)
	List<VacationMonths> findVacationMonths(@Param("id") int id);

}
