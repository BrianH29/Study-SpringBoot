package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /*
    MemberService memberService = new MemberService();
    MemoryMemberRepository mmr = new MemoryMemberRepository();
     이 상태로 하게 되면 동일한 DB 를 사용하는게 아니기 때문에 정확한 테스트를 할 수 없다.
     MemoryMemberRepository에 static으로 되어져 있어서 괜찮지만 만약 static이 없다면 문제가 될 수 있다.
     그렇기 떄문에 BeforeEach로 memberService로 외부에서 입력하게 끔 한다. DI(dependency injection)
     */

    MemberService memberService;
    MemoryMemberRepository mmr;

    @BeforeEach
    public void beforeEach(){
        mmr = new MemoryMemberRepository();
        memberService = new MemberService(mmr);
    }

    @AfterEach
    public void afterEach(){
        mmr.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("Jerry");
        //when
        Long saveId = memberService.join(member);

        //then
       Member findMember = memberService.findOne(saveId).get();
       assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void DuplicateMember(){
        //given
        Member member1 = new Member();
        member1.setName("Jerry");

        Member member2 = new Member();
        member2.setName("Jerry");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}