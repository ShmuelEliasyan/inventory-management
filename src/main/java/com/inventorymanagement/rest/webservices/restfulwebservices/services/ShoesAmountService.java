package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.ShoesAmount;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.ShoesAmountRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.ShoesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoesAmountService {

    @Autowired
    private ShoesAmountRepository shoesAmountRepository;

    @Autowired
    @Lazy
    private ShoesRepository shoesService;

    public void addShoesAmount(List<ShoesAmount> shoesAmountList, String barcode) {
        Optional<Shoes> shoesOptional = shoesService.findByBarcode(barcode);

        if (shoesOptional.isEmpty()) {
            throw new EntityNotFoundException("Shoes object with barcode = " + barcode + " is not found");
        }

        Shoes shoes = shoesOptional.get();

        shoesAmountList.stream().forEach(shoesAmount -> {
            shoesAmount.setShoes(shoes);
        });

        shoesAmountRepository.saveAll(shoesAmountList);
    }

    public void deleteShoesAmount(String barcode, String size) {
        if (size != null) {
            deleteSingleShoesAmount(barcode, size);
        } else {
            deleteMultipleShoesAmount(barcode);
        }
    }

    public void updateShoesAmount(ShoesAmount shoesAmount) {
        Optional<ShoesAmount> shoesAmountOptional = shoesAmountRepository.findByBarcodeAndSize(shoesAmount.getBarcode(), shoesAmount.getSize());

        if (shoesAmountOptional.isEmpty()) {
            throw new EntityNotFoundException("ShoesAmount object with barcode = " + shoesAmount.getBarcode() + " and size = " + shoesAmount.getSize() +
                    " is not found");
        }

        ShoesAmount shoesAmountEntity = shoesAmountOptional.get();
        shoesAmountEntity.setAmount(shoesAmount.getAmount());
        shoesAmountRepository.save(shoesAmountEntity);
    }

    public List<String> getSizesList() {
        return shoesAmountRepository.findAllSizes();
    }

    private void deleteMultipleShoesAmount(String barcode) {
        List<ShoesAmount> shoesAmountOptional = shoesAmountRepository.findByBarcode(barcode);
        shoesAmountRepository.deleteAll(shoesAmountOptional);
    }

    private void deleteSingleShoesAmount(String barcode, String size) {
        Optional<ShoesAmount> shoesAmountOptional = shoesAmountRepository.findByBarcodeAndSize(barcode, size);
        if (shoesAmountOptional.isEmpty()) {
            throw new EntityNotFoundException("ShoesAmount object with barcode = " + barcode + " and size = " + size +
                    " is not found");
        }
        shoesAmountRepository.delete(shoesAmountOptional.get());
    }
}
