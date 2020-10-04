package com.sda.service;

import com.sda.model.Car;
import com.sda.repository.CarRepository;
import com.sda.request.CreateCarRequest;
import com.sda.view.CarView;
import com.sda.view.UserView;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CarService {

  private static String USERS_URL = "http://localhost:8066/user/external/";


  private final RestTemplate restTemplate;

  private final CarRepository carRepository;


  public Car create(CreateCarRequest request) {
    Long userId = request.getUserId();
    UserView userView = getUserFromExternalService(userId);

    Car car = Car.builder()
        .company(request.getCompany())
        .model(request.getModel())
        .userAge(userView.getAge())
        .userName(userView.getName())
        .userSurname(userView.getSurname())
        .userId(request.getUserId())
        .year(request.getYear())
        .build();

    carRepository.save(car);
    return car;
  }

  private UserView getUserFromExternalService(Long userId) {
    return restTemplate.getForObject(USERS_URL + userId, UserView.class);
  }

  public List<Car> findAll() {
    return carRepository.findAll();
  }

  public List<CarView> findForUser(Long userId) {
    return carRepository.findAllByUserId(userId)
        .stream()
        .map(c -> new CarView(c.getCompany(), c.getModel(), c.getYear()))
        .collect(Collectors.toList());
  }
}
