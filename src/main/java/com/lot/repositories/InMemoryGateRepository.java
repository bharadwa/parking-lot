package com.lot.repositories;

import com.lot.models.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class InMemoryGateRepository implements GateRepository {

    private final Map<Long,Gate> gateMap = new TreeMap<>();

    private long idCounter = 0;

    @Override
    public Gate saveGate(Gate gate) {
        idCounter=idCounter+1;
        gate.setId(idCounter);
        gateMap.put(gate.getId(), gate);
        return gate;
    }

    @Override
    public Optional<Gate> getGateById(long gateId) {
        if(gateMap==null || gateMap.isEmpty() || !gateMap.containsKey(gateId)) {
            return Optional.empty();
        }
        return Optional.of(gateMap.get(gateId));
    }
}
