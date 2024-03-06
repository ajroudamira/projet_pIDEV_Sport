package tn.esprit.services;

import tn.esprit.entities.Equipement;

public interface OnChangeListener {
    void sendEquipementData(Equipement equipement);
}
