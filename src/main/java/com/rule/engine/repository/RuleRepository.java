package com.rule.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rule.engine.model.ASTNode;

public interface RuleRepository extends JpaRepository<ASTNode, Long> {

}
