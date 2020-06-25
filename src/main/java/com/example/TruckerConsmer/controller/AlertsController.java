package com.example.TruckerConsmer.controller;

import com.example.TruckerConsmer.model.Alerts;
import com.example.TruckerConsmer.service.AlertsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertsController {

    AlertsService alertsService;
    @Autowired
    AlertsController(AlertsService alertsService){
        this.alertsService = alertsService;
    }

    @GetMapping
    @ApiOperation(value = "Get all alerts",
            notes = "Optional Parameters priority and vin"
                )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alerts> findAll(@RequestParam(value = "priority", required = false) String priority,
                                @RequestParam(value = "vin", required = false) String vin){
        if(vin == null && priority == null){
            return alertsService.findAll();
        }
        else if(vin == null){
            return alertsService.findByPriority(priority);
        }
        else if( priority == null){
            return alertsService.findByVin(vin);
        }
        return alertsService.findByVinAndPriority(vin,priority);
    }

    @PostMapping
    @ApiOperation(value = "Post alerts"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Alerts postAlerts(@RequestBody Alerts alerts){
        return alertsService.save(alerts);
    }

}
