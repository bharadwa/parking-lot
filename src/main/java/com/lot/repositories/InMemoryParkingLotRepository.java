package com.lot.repositories;

import com.lot.models.Gate;
import com.lot.models.ParkingLot;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class InMemoryParkingLotRepository implements  ParkingLotRepository{

    private final Map<Long,ParkingLot> parkingLotMap=new TreeMap<>();

    private long idCounter = 0L;
    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {

        if(parkingLotMap.isEmpty()) {
            return Optional.empty();
        }

        for(ParkingLot parkingLot: parkingLotMap.values()) {
            for(Gate gate:parkingLot.getGates()) {
                if(gate.getId()==gateId){
                    return Optional.ofNullable(parkingLot);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {

        if(parkingLotMap.isEmpty() || !parkingLotMap.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(parkingLotMap.get(id));
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {

        if(parkingLot.getId() == 0) {
            idCounter=idCounter+1;
            parkingLot.setId(idCounter);
        } else if (parkingLotMap.containsKey(parkingLot.getId())) {
            // Update existing parking lot
            parkingLot.setUpdatedAt(new Date());
        } else {
            // New parking lot
            parkingLot.setCreatedAt(new Date());
        }
        parkingLotMap.put(parkingLot.getId(), parkingLot);

        return parkingLot;
    }

}
