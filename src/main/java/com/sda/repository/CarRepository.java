package com.sda.repository;

import com.sda.model.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

  List<Car> findAllByUserId(Long id);

}
