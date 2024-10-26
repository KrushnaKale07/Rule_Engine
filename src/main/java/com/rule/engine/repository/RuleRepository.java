package com.rule.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rule.engine.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

}
