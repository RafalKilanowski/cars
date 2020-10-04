package com.sda.request;


import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCarRequest {

  @NotNull
  private Long userId;

  @NotNull
  private String company;

  @NotNull
  private String model;

  @NotNull
  private Integer year;
}
