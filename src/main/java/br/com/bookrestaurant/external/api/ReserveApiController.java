package br.com.bookrestaurant.external.api;


import br.com.bookrestaurant.entity.reserve.ReserveEntity;
import br.com.bookrestaurant.external.db.DataBaseJpa;
import br.com.bookrestaurant.external.dto.ReserveDto;
import br.com.bookrestaurant.infraestructure.controller.ReserveController;
import com.sun.net.httpserver.HttpsServer;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/reserve")
public class ReserveApiController {
    private DataBaseJpa database;
    private ReserveController controller;

    @Autowired
    public ReserveApiController(DataBaseJpa database,
                              ReserveController controller) {
        this.database = database;
        this.controller = controller;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ReserveDto> register(@RequestBody @Valid ReserveDto reserveDto) {
        ReserveEntity reserveEntity = controller.register(reserveDto.toRecord(),
                reserveDto.getClient().toRecord(), database);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReserveDto(reserveEntity));
    }
}
