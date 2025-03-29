package ru.yandex.practicum.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/cats")
public class CatsInteractionController {
    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        happiness++;
        return Map.of("talk", "Мяу");
    }

//    @GetMapping("/pet")
//    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
//        happiness += count;
//        return Map.of("talk", "Муррр. ".repeat(count));
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
        return Map.of("talk", "Муррр. ".repeat(count));
    }


    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

    @GetMapping("/feed")
    public Map<String, Integer> feed() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Метод /feed ещё не реализован.");
    }


//    @ExceptionHandler
//// отлавливаем исключение IllegalArgumentException
//    public Map<String, String> handleNegativeCount(final IllegalArgumentException e) {
//        // возвращаем сообщение об ошибке
//        return Map.of(
//                "error", "Передан отрицательный параметр count.",
//                "errorMessage", e.getMessage()
//
//        );
//    }
//
//    @ExceptionHandler
//// отлавливаем исключение NullPointerException
//    public Map<String, String> handleNullableCount(final NullPointerException e) {
//        // возвращаем сообщение об ошибке
//        return Map.of("error", "Параметр count не указан.");
//    }


//    // перечисление обрабатываемых исключений
//    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
//// в аргументах указывается родительское исключение
//    public Map<String, String> handleIncorrectCount(final RuntimeException e) {
//        return Map.of(
//                "error", "Ошибка с параметром count.",
//                "errorMessage", e.getMessage()
//        );
//    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> handle(final IncorrectCountException e) {
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }

//    @ExceptionHandler
//// отлавливаем исключение RuntimeException
//    public Map<String, String> handleError(final RuntimeException e) {
//        // возвращаем сообщение об ошибке
//        return Map.of("error", "Произошла ошибка!");
//    }






}