package br.com.bookrestaurant.entity;

import br.com.bookrestaurant.entity.reserve.Client;
import br.com.bookrestaurant.entity.reserve.exception.ReserveInvalidException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public class EntityUtil {
    public static String isNullOrBlank (String value, String message) {
        return Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .orElseThrow(() -> new ReserveInvalidException(message));
    }
    public static Integer isNull (Integer value, String message) {
        return (Integer) execute(value, message);
    }

    public static UUID isNull (UUID value, String message) {
        return (UUID) execute(value, message);
    }

    public static LocalDateTime isNull (LocalDateTime value, String message) {
        return (LocalDateTime) execute(value, message);
    }

    public static Client isNull (Client value, String message) {
        return (Client) execute(value, message);
    }

    public static Object execute(Object value, String message) {
        return Optional.ofNullable(value)
                .orElseThrow(() -> new ReserveInvalidException(message));
    }
}
