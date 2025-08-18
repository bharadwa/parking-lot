package com.lot.repositories;

import com.lot.models.Gate;

import java.util.Optional;

public interface GateRepository {

    Gate saveGate(Gate gate);

    Optional<Gate> getGateById(long gateId);
}
