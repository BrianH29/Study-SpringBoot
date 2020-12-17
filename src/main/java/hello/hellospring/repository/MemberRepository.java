package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //회원이 저장소에 저장 되는것
    Member save(Member member);

    //아이디로 회원 찾기
    Optional<Member> findById(Long id);

    //이름으로 회원 찾기
    Optional<Member> findByName(String name);

    // 다 찾아오기
    List<Member> findAll();
}
