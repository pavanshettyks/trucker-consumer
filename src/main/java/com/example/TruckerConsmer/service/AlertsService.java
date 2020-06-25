package com.example.TruckerConsmer.service;

import com.example.TruckerConsmer.model.Alerts;

import java.util.List;

public interface AlertsService {

    public List<Alerts> findAll();

    public List<Alerts> findByVinAndPriority(String vin, String priority);

    public List<Alerts> findByVin(String vin);

    public List<Alerts> findByPriority(String priority);

    public Alerts save(Alerts alerts);

}
