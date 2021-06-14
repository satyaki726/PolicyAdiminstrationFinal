package com.cts.mfpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mfpe.model.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer,Long> {

}
