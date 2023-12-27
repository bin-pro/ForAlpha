package com.example.legendfive.save.repository;

import com.example.legendfive.overall.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockApiRepository extends JpaRepository<Stock,Long> {

}
