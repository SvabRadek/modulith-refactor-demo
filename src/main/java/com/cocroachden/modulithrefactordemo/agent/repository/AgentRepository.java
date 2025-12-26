package com.cocroachden.modulithrefactordemo.agent.repository;

import com.cocroachden.modulithrefactordemo.agent.domain.AgentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<AgentEntity, AgentId> {
}
