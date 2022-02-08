package com.travelAppSpringBoot.controller;

import com.travelAppSpringBoot.model.PurchasedTour;
import com.travelAppSpringBoot.service.PurchasedTourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/purchasedTours")
public class PurchasedTourController {

    private final PurchasedTourService purchasedTourService;

    public PurchasedTourController(PurchasedTourService purchasedTourService) {
        this.purchasedTourService = purchasedTourService;
    }

    @PostMapping("/addPurchasedTour")
    public ResponseEntity<PurchasedTour> createNewPurchasedTour(@RequestBody PurchasedTour purchasedTour) {

            PurchasedTour newPurchasedTour = purchasedTourService.createNewPurchasedTour(purchasedTour);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(newPurchasedTour);
    }

    @PutMapping("/updatePurchasedTour")
    public ResponseEntity<String> updatePurchasedTour(@RequestBody PurchasedTour purchasedTour) {
        purchasedTourService.updatePurchasedTour(purchasedTour);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(purchasedTourService.updatePurchasedTour(purchasedTour));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchasedTour>> readAllPurchasedTour() {
        List<PurchasedTour> purchasedTourList = purchasedTourService.getAllPurchasedTour();
        return new ResponseEntity<>(purchasedTourList, HttpStatus.OK);
    }

    @GetMapping("/purchasedTours/{id}")
    public ResponseEntity<PurchasedTour> readCountryById(@PathVariable Long id) {
        PurchasedTour purchasedTour = purchasedTourService.findPurchasedTourById(id)
                .orElseThrow(() -> new RuntimeException("Purchased Tour not found"));
        return new ResponseEntity<>(purchasedTour, HttpStatus.OK);
    }

    @DeleteMapping("/deletePurchasedTours/{id}")
    public ResponseEntity<String> deletePurchasedTour(@PathVariable Long id) {
        Optional<PurchasedTour> tourPurchasedDeleted = purchasedTourService.findPurchasedTourById(id);
        if (tourPurchasedDeleted.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(purchasedTourService.deletePurchasedTourById(id));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(purchasedTourService.deletePurchasedTourById(id));
        }
    }

}
