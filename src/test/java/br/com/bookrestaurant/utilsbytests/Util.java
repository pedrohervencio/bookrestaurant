package br.com.bookrestaurant.utilsbytests;

import br.com.bookrestaurant.entity.reserve.Client;
import br.com.bookrestaurant.entity.reserve.ReserveEntity;
import br.com.bookrestaurant.entity.reserve.ReserveEntityBuilder;
import br.com.bookrestaurant.external.dto.ClientDto;
import br.com.bookrestaurant.external.dto.ReserveDto;
import br.com.bookrestaurant.infraestructure.presenter.reserve.ClientRecord;
import br.com.bookrestaurant.infraestructure.presenter.reserve.ReserveRecord;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Util {
    public static Client buildClient() {
        return new Client("João", 998648471);
    }

    public static ReserveEntity buildReserve() {
        return new ReserveEntity(UUID.fromString("ba94f4d5-b0a6-4745-adac-1456619ecca8"),
                LocalDateTime.now(), 4, "A");
    }

    public static ReserveEntity buildReserveEntity() {
        return new ReserveEntity(UUID.fromString("ba94f4d5-b0a6-4745-adac-1456619ecca8"),
                LocalDateTime.now(), 4, "A");
    }

    public static UUID getUUID() {
        return UUID.fromString("ba94f4d5-b0a6-4745-adac-1456619ecca8");
    }

    public static ReserveEntity buildReserveEntitySaved() {
        ReserveEntity reserveEntity = new ReserveEntityBuilder()
                .addInfos(UUID.fromString("ba94f4d5-b0a6-4745-adac-1456619ecca8"),
                        LocalDateTime.now(), 4, "A")
                .addClient(buildClient())
                .build();
        reserveEntity.setId(getUUID());
        return reserveEntity;
    }

    public static ReserveRecord buildReserveRecord() {
        return new ReserveRecord(UUID.fromString("ba94f4d5-b0a6-4745-adac-1456619ecca8"),
                LocalDateTime.now(), 4, "A");

    }

    public static ClientRecord buildClientRecord() {
        return new ClientRecord("João", 984488778);
    }

    public static ReserveDto buildReserveDtoRequest() {
        ClientDto clientDto = new ClientDto(null, "João", 912341234);
        ReserveDto dto = new ReserveDto(UUID.fromString("ba94f4d5-b0a6-4745-adac-1456619ecca8"),
                UUID.fromString("ba94f4d5-b0a6-4745-adac-1456619ecca8"),
                LocalDateTime.now(), 4, "A",
                clientDto);
        return dto;
    }


    public static String toJson(Object obj) {
        try {
            return new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }

}
