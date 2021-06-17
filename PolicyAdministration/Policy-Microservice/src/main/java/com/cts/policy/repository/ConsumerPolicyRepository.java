package com.cts.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.policy.model.ConsumerPolicy;

@Repository
public interface ConsumerPolicyRepository extends JpaRepository<ConsumerPolicy,Long> {

}
