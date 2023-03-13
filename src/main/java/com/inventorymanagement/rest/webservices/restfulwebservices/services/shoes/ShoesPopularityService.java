package com.inventorymanagement.rest.webservices.restfulwebservices.services.shoes;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.purchase.PurchaseSoldPairs;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes.ShoesPopularity;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.shoes.ShoesPopularityRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShoesPopularityService {

    private static final Logger log = LoggerFactory.getLogger(ShoesPopularityService.class);
    @Autowired
    private ShoesPopularityRepository shoesPopularityRepository;

    @Resource
    @Lazy
    private ShoesPopularityService self;

    @CacheEvict(value = "shoesPopularity", allEntries = true)
    public void addNewPurchase(List<PurchaseSoldPairs> purchaseSoldPairsList) {
        Date currentMonthDate = DateUtils.getCurrentMonthDate();

        for (PurchaseSoldPairs purchaseSoldPairs : purchaseSoldPairsList) {
            Shoes shoes = purchaseSoldPairs.getShoes();
            List<ShoesPopularity> tempShoesPopularityList = shoesPopularityRepository.findByDateAndShoes(currentMonthDate, shoes);
            ShoesPopularity shoesPopularity = tempShoesPopularityList.isEmpty() ? new ShoesPopularity(0, currentMonthDate, 0, shoes) : tempShoesPopularityList.get(0);

            int oldSoldPairs = shoesPopularity.getSoldPairs();
            int newSoldPairs = oldSoldPairs + purchaseSoldPairs.getSoldPairs();
            shoesPopularity.setSoldPairs(newSoldPairs);

            shoesPopularityRepository.save(shoesPopularity);
        }
    }

    public GraphDTO getShoesPopularityGraph() {
        List<ShoesPopularity> shoesPopularityList = self.getShoesPopularityList();
        GraphDTO shoesPopularityGraph = new GraphDTO();
        List<String> labels = shoesPopularityGraph.getLabels();
        List<Number> data = shoesPopularityGraph.getData();
        shoesPopularityList.stream().forEach(shoesPopularity -> {
            data.add(0, shoesPopularity.getSoldPairs());
            labels.add(0, shoesPopularity.getShoes().getName());
        });
        return shoesPopularityGraph;
    }

    @Cacheable("shoesPopularity")
    public List<ShoesPopularity> getShoesPopularityList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.DESC, "soldPairs", 0, 10);
        List<ShoesPopularity> shoesPopularityList = shoesPopularityRepository.getShoesPopularityList(DateUtils.getCurrentMonthDate(), paging);
        return shoesPopularityList;
    }
}
