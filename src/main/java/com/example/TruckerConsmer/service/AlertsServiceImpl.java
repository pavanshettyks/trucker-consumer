package com.example.TruckerConsmer.service;

import com.example.TruckerConsmer.model.Alerts;
import com.example.TruckerConsmer.repository.AlertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertsServiceImpl implements  AlertsService{


    AlertsRepository alertsRepository;

    @Autowired
    AlertsServiceImpl(AlertsRepository alertsRepository){
        this.alertsRepository = alertsRepository;
    }

    @Override
    public List<Alerts> findAll() {
        System.out.println("FInd All");
        return alertsRepository.findAll();
    }

    @Override
    public List<Alerts> findByVinAndPriority(String vin, String priority) {
        System.out.println("Find by vin and prio "+vin+" "+priority);
        return alertsRepository.findAllByVinAndPriority(vin, priority);
    }

    @Override
    public List<Alerts> findByVin(String vin) {
        System.out.println("Find by vin "+vin);
        return alertsRepository.findAllByVin(vin);
    }

    @Override
    public List<Alerts> findByPriority(String priority) {
        System.out.println("Find by prio "+priority);
        return alertsRepository.findAllByPriority( priority);
    }

    @Override
    public Alerts save(Alerts alerts) {
        System.out.println("alerts alerts alerts");
        return alertsRepository.save(alerts);
    }

}
