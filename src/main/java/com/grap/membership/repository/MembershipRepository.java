package com.grap.membership.repository;

import com.grap.membership.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Optional<Membership> findByPrice(Integer price);
}
