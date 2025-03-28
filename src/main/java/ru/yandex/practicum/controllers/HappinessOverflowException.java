package ru.yandex.practicum.controllers;

public class HappinessOverflowException extends RuntimeException {
  int happinessLevel;
    public HappinessOverflowException(int happinessLevel) {
                this.happinessLevel = happinessLevel;
    }

  public Integer getHappinessLevel() {
    return happinessLevel;
  }

}
