package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.PurchaseSoldPairs;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.ShoesPopularity;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.ShoesPopularityRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShoesPopularityService {

    @Autowired
    private ShoesPopularityRepository shoesPopularityRepository;

    public void addNewPurchase(List<PurchaseSoldPairs> purchaseSoldPairsList) {
        Date currentMonthDate = DateUtils.getCurrentMonthDate();

        for (PurchaseSoldPairs purchaseSoldPairs : purchaseSoldPairsList) {
            Shoes shoes = purchaseSoldPairs.getShoes();
            ShoesPopularity shoesPopularity = shoesPopularityRepository.findByDateAndShoes(currentMonthDate, shoes);

            if (shoesPopularity == null) {
                shoesPopularity = new ShoesPopularity(0, currentMonthDate, 0, shoes);
            }

            int oldSoldPairs = shoesPopularity.getSoldPairs();
            int newSoldPairs = oldSoldPairs + purchaseSoldPairs.getSoldPairs();
            shoesPopularity.setSoldPairs(newSoldPairs);

            shoesPopularityRepository.save(shoesPopularity);
        }
    }

    public GraphDTO getShoesPopularityGraph() {
        List<ShoesPopularity> shoesPopularityList = getShoesPopularityList();
        GraphDTO shoesPopularityGraph = new GraphDTO();
        List<String> labels = shoesPopularityGraph.getLabels();
        List<Number> data = shoesPopularityGraph.getData();
        shoesPopularityList.stream().forEach(shoesPopularity -> {
            data.add(0, shoesPopularity.getSoldPairs());
            labels.add(0, shoesPopularity.getShoes().getName());
        });
        return shoesPopularityGraph;
    }

    private List<ShoesPopularity> getShoesPopularityList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.DESC, "soldPairs", 0, 10);
        List<ShoesPopularity> shoesPopularityList = shoesPopularityRepository.getShoesPopularityList(DateUtils.getCurrentMonthDate(), paging);
        return shoesPopularityList;
    }
}
