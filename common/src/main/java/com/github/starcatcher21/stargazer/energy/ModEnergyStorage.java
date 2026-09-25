package com.github.starcatcher21.stargazer.energy;

public interface ModEnergyStorage {
    long receiveEnergy(long maxReceive, boolean simulate);
    long extractEnergy(long maxExtract, boolean simulate);
    long getAmount();
    long getCapacity();
    void setAmount(long amount);
}