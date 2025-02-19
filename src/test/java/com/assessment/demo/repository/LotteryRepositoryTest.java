package com.assessment.demo.repository;

import com.assessment.demo.entity.Lottery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LotteryRepositoryTest {

    @Autowired
    LotteryRepository lotteryRepository;

    @Test
    @DisplayName("when query findAllLotteries then return lotteryList")
    void findAllLotteries() {

        String[] lotteryList1 = {"123123", "246824"};
        Lottery lottery1 = new Lottery("123123", 80, 1);
        Lottery lottery2 = new Lottery("246824", 80, 1);

        lotteryRepository.save(lottery1);
        lotteryRepository.save(lottery2);
        String[] lotteryList2 = lotteryRepository.findAllLotteries();

        assertNotNull(lotteryList2);
        Assertions.assertArrayEquals(lotteryList1, lotteryList2);
    }

    @Test
    @DisplayName("when query findPriceByTicketId then return price")
    void findPriceByTicketId() {

        Lottery lottery = new Lottery("123123", 80, 1);

        lotteryRepository.save(lottery);
        int price = lotteryRepository.findPriceByTicketId(lottery.getTicketId());

        assertNotNull(price);
        Assertions.assertEquals(lottery.getPrice(), price);

    }

    @Test
    @DisplayName("when query findPriceByTicketId then return price")
    void existsByTicketId() {

        Lottery lottery = new Lottery("123123", 80, 1);

        lotteryRepository.save(lottery);
        boolean exists = lotteryRepository.existsByTicketId(lottery.getTicketId());

        Assertions.assertTrue(exists);

    }
}
