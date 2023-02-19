package com.example.api;

import com.example.model.vehicle.Vehicle;
import com.example.model.vehicle.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VehicleController {
    private final VehicleService service;

    @PostMapping()
    public void index(@RequestBody final Vehicle vehicle) {
        service.save(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getId(@PathVariable String id) {
        return service.getId(id);
    }
}
