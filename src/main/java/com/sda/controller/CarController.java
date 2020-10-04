package com.sda.controller;

import com.sda.model.Car;
import com.sda.request.CreateCarRequest;
import com.sda.service.CarService;
import com.sda.view.CarView;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cars")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  @PostMapping
  public ResponseEntity<Car> create(@RequestBody @Valid CreateCarRequest request) {
    Car car = carService.create(request);
    return new ResponseEntity<>(car, HttpStatus.CREATED);
  }


  @GetMapping
  public ResponseEntity<List<Car>> findAll() {
    List<Car> cars = carService.findAll();
    return ResponseEntity.ok(cars);
  }


  @GetMapping("/external/{userId}")
  public List<CarView> getCars(@PathVariable Long userId) {
    return carService.findForUser(userId);
  }
}
