package br.com.bookrestaurant.entity;

import br.com.bookrestaurant.entity.reserve.exception.ReserveInvalidException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EntityUtilTest {
    @Test
    void testUUIDisNotNull() {
        UUID id = UUID.randomUUID();
        assertThat(EntityUtil.isNull(id, "")).isEqualTo(id);
    }

    @Test
    void testUUIDisNull() {
        UUID id = null;
        assertThatThrownBy(() -> EntityUtil.isNull(id, ""))
                .isInstanceOf(ReserveInvalidException.class);
    }

    @Test
    void testLocalDateTimeIsNull() {
        LocalDateTime date = null;
        assertThatThrownBy(() -> EntityUtil.isNull(date, ""))
                .isInstanceOf(ReserveInvalidException.class);
    }

    @Test
    void testClientIsNull() {
        LocalDateTime date = null;
        assertThatThrownBy(() -> EntityUtil.isNull(date, ""))
                .isInstanceOf(ReserveInvalidException.class);
    }
}
