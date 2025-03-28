package ru.yandex.practicum.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dogs")
public class DogsInteractionController {
    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        happiness += 2;

        if (happiness > 10) {
            throw new HappinessOverflowException(happiness);
        }
        return Map.of("talk", "Гав!");
    }

//    @GetMapping("/pet")
//    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
//        happiness += count;
//        return Map.of("action", "Вильнул хвостом. ".repeat(count));
//    }

    @GetMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
        if (count == null) {
            throw new IncorrectCountException("Параметр count равен null.");
        }
        if (count <= 0) {
            throw new IncorrectCountException("Параметр count имеет отрицательное значение.");
        }

        happiness += count;

        if (happiness > 10) {
            throw new HappinessOverflowException(happiness);
        }
        return Map.of("action", "Вильнул хвостом. ".repeat(count));
    }

    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

    @ExceptionHandler
    public Map<String, String> handle(final IncorrectCountException e) {
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }

    @ExceptionHandler
// отлавливаем исключение RuntimeException
    public Map<String, String> handleError(final RuntimeException e) {
        // возвращаем сообщение об ошибке
        return Map.of("error", "Произошла ошибка!");
    }

    @ExceptionHandler
    public Map<String, String> handleHappinessOverflow(final HappinessOverflowException e) {
        return Map.of(
                "error", "Осторожно, вы так избалуете пёсика!",
                "happinessLevel", e.getHappinessLevel().toString()
        );
    }


}
