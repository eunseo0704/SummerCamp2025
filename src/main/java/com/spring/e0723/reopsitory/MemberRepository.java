package com.spring.e0723.reopsitory;

import com.spring.e0723.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

}
