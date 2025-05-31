package com.example.card2card.config;

import com.example.card2card.entity.Bank;
import com.example.card2card.entity.Card;
import com.example.card2card.entity.User;
import com.example.card2card.repository.BankRepository;
import com.example.card2card.repository.CardRepository;
import com.example.card2card.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;
import java.util.List;

/**
 * Initializes sample data including users, banks, and cards at application startup.
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepo,
                                      BankRepository bankRepo,
                                      CardRepository cardRepo,
                                      PasswordEncoder encoder) {
        return args -> {
            User bahram;
            if (userRepo.findByUsername("bahram").isEmpty()) {
                bahram = new User();
                bahram.setUsername("bahram");
                bahram.setPassword(encoder.encode("123456"));
                userRepo.save(bahram);
                System.out.println(" کاربر bahram ساخته شد.");
            } else {
                bahram = userRepo.findByUsername("bahram").get();
            }

            User sara;
            if (userRepo.findByUsername("sara").isEmpty()) {
                sara = new User();
                sara.setUsername("sara");
                sara.setPassword(encoder.encode("123456"));
                userRepo.save(sara);
                System.out.println(" کاربر sara ساخته شد.");
            } else {
                sara = userRepo.findByUsername("sara").get();
            }


            Bank saman = bankRepo.findByName("Saman").orElseGet(() -> {
                Bank b = new Bank();
                b.setName("Saman");
                return bankRepo.save(b);
            });

            Bank mellat = bankRepo.findByName("Mellat").orElseGet(() -> {
                Bank b = new Bank();
                b.setName("Mellat");
                return bankRepo.save(b);
            });

            if (cardRepo.findByCardNumber("6037991234567890").isEmpty() &&
                    cardRepo.findByCardNumber("5022291122334455").isEmpty()) {

                Card c1 = new Card();
                c1.setCardNumber("6037991234567890");
                c1.setBalance(new BigDecimal("1000000"));
                c1.setTransferLimit(new BigDecimal("200000"));
                c1.setOwner(bahram);
                c1.setBank(saman);

                Card c2 = new Card();
                c2.setCardNumber("5022291122334455");
                c2.setBalance(new BigDecimal("500000"));
                c2.setTransferLimit(new BigDecimal("300000"));
                c2.setOwner(sara);
                c2.setBank(mellat);

                cardRepo.saveAll(List.of(c1, c2));
                System.out.println(" کارت‌های تستی برای bahram و sara ساخته شدند.");
            }

        };
    }
}
