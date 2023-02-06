package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.criterias.ShoesCriteria;
import com.inventorymanagement.rest.webservices.restfulwebservices.dto.ShoesCatalogDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.dto.ShoesWithShoesAmountDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.ShoesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoesService {
    @Autowired
    private ShoesRepository shoesRepository;

    @Autowired
    private ShoesAmountService shoesAmountService;

    public void addShoes(Shoes shoes) {
        shoesRepository.save(shoes);
    }

    public void deleteShoes(String barcode) {
        Optional<Shoes> shoesOptional = shoesRepository.findByBarcode(barcode);
        if (shoesOptional.isEmpty()) {
            throw new EntityNotFoundException("Shoes object with barcode = " + barcode + " is not found");
        }
        shoesRepository.delete(shoesOptional.get());
    }

    public List<String> getBarcodesList() {
        return shoesRepository.findAllBarcodes();
    }

    public ShoesWithShoesAmountDTO getShoesWithShoesAmount(String barcode) {
        Optional<Shoes> shoesOptional = shoesRepository.findByBarcode(barcode);
        if (shoesOptional.isEmpty()) {
            throw new EntityNotFoundException("Shoes object with barcode = " + barcode + " is not found");
        }
        return convertShoesToDtoWithShoesAmount(shoesOptional.get());
    }

    public ShoesCatalogDTO getShoesCatalog(ShoesCriteria shoesCriteria) {
        List<Shoes> shoes = getShoes(shoesCriteria);

        int totalShoesAmount = getTotalShoesAmount(shoesCriteria);

        int pageSize = shoesCriteria.getPageSize();
        int totalPagesCount = PaginationUtils.calculatePagesCount(pageSize, totalShoesAmount);

        List<String> sizes = shoesAmountService.getSizesList();

        ShoesCatalogDTO shoesCatalogDTO = new ShoesCatalogDTO(shoes, totalShoesAmount, totalPagesCount, sizes);

        return shoesCatalogDTO;
    }

    public Optional<Shoes> findByBarcode(String barcode) {
        return shoesRepository.findByBarcode(barcode);
    }

    private int getTotalShoesAmount(ShoesCriteria shoesCriteria) {
        int totalShoesAmount;

        List<String> shoesSizes = shoesCriteria.getShoesSizes();

        if (shoesCriteria.getShoesSizes().isEmpty()) {
            totalShoesAmount = (int) shoesRepository.count();
        } else {
            totalShoesAmount = (int) shoesRepository.getShoesCountByShoesSizes(shoesSizes);
        }

        return totalShoesAmount;
    }

    private List<Shoes> getShoes(ShoesCriteria shoesCriteria) {
        PageRequest paging = PaginationUtils.getPageRequest(shoesCriteria.getSortDirection(), shoesCriteria.getSortBy(), shoesCriteria.getPage(), shoesCriteria.getPageSize());

        List<String> shoesSizes = shoesCriteria.getShoesSizes();

        List<Shoes> shoes;

        if (shoesSizes.isEmpty()) {
            shoes = shoesRepository.getShoesList(paging);
        } else {
            shoes = shoesRepository.getShoesListByShoesSizes(shoesSizes, paging);
        }

        return shoes;
    }

    private ShoesWithShoesAmountDTO convertShoesToDtoWithShoesAmount(Shoes shoes) {
        ShoesWithShoesAmountDTO shoesWithShoesAmountDTO = new ShoesWithShoesAmountDTO();
        shoesWithShoesAmountDTO.setShoes(shoes);
        shoesWithShoesAmountDTO.setShoesAmount(shoes.getShoesAmounts());
        return shoesWithShoesAmountDTO;
    }
}
