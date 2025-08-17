package com.lot.repositories;

import com.lot.models.Gate;
import com.lot.models.ParkingLot;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class InMemoryParkingLotRepository implements  ParkingLotRepository{

    private final Map<Long,ParkingLot> parkingLotMap=new TreeMap<>();


    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {

        for(ParkingLot parkingLot: parkingLotMap.values()) {
            for(Gate gate:parkingLot.getGates()) {
                if(gate.getId()==gateId){
                    return Optional.of(parkingLot);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
        return Optional.of(parkingLotMap.get(id));
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {

        parkingLotMap.put(parkingLot.getId(), parkingLot);

        return parkingLot;
    }

}
