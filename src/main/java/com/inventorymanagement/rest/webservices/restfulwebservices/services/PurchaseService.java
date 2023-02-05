package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.PurchaseDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.dto.PurchaseSoldPairsDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Purchase;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.PurchaseSoldPairs;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Worker;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.PurchaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private DaySalesService daySalesService;

    @Autowired
    private MonthSalesService monthSalesService;

    @Autowired
    private ShoesPopularityService shoesPopularityService;

    @Autowired
    private WorkerSalesService workerSalesService;

    @Autowired
    private PurchaseSoldPairsService purchaseSoldPairsService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ShoesService shoesService;

    public void submitPurchase(PurchaseDTO purchaseDTO) {
        Worker worker = getWorker(purchaseDTO);

        Purchase purchase = savePurchase(purchaseDTO, worker);

        List<PurchaseSoldPairs> purchaseSoldPairsList = savePurchaseSoldPairs(purchaseDTO, purchase);

        CompletableFuture.runAsync(() -> generateSalesData(worker, purchase, purchaseSoldPairsList));
    }

    private Worker getWorker(PurchaseDTO purchaseDTO) {
        int workerId = purchaseDTO.getWorkerId();
        Optional<Worker> workerOptional = workerService.findById(workerId);
        if (workerOptional.isEmpty()) {
            throw new EntityNotFoundException("Worker object with id = " + workerId + " is not found");
        }
        return workerOptional.get();
    }

    private List<PurchaseSoldPairs> savePurchaseSoldPairs(PurchaseDTO purchaseDTO, Purchase purchase) {
        List<PurchaseSoldPairsDTO> purchaseSoldPairsDTOList = purchaseDTO.getPurchaseSoldPairsDTOList();
        List<PurchaseSoldPairs> purchaseSoldPairsList = purchaseSoldPairsDTOList.stream().map(purchaseSoldPairsDTO -> getPurchaseSoldPairs(purchase, purchaseSoldPairsDTO)).collect(Collectors.toList());
        return purchaseSoldPairsService.saveAll(purchaseSoldPairsList);
    }

    private PurchaseSoldPairs getPurchaseSoldPairs(Purchase purchase, PurchaseSoldPairsDTO purchaseSoldPairsDTO) {
        PurchaseSoldPairs purchaseSoldPairs = new PurchaseSoldPairs();
        purchaseSoldPairs.setPurchase(purchase);
        purchaseSoldPairs.setSoldPairs(purchaseSoldPairsDTO.getSoldPairs());
        String barcode = purchaseSoldPairsDTO.getBarcode();
        Optional<Shoes> shoes = shoesService.findByBarcode(barcode);
        if (shoes.isEmpty()) {
            throw new EntityNotFoundException("Shoes object with barcode = " + barcode + " is not found");
        }
        purchaseSoldPairs.setShoes(shoes.get());
        return purchaseSoldPairs;
    }

    private Purchase savePurchase(PurchaseDTO purchaseDTO, Worker worker) {
        Purchase purchase = new Purchase();
        purchase.setWorker(worker);
        purchase.setSum(purchaseDTO.getSum());
        return purchaseRepository.save(purchase);
    }

    private void generateSalesData(Worker worker, Purchase purchase, List<PurchaseSoldPairs> purchaseSoldPairsList) {
        daySalesService.addNewPurchase(purchase.getSum());
        monthSalesService.addNewPurchase(purchase.getSum());
        shoesPopularityService.addNewPurchase(purchaseSoldPairsList);
        workerSalesService.addNewPurchase(purchase.getSum(), worker);
    }
}
