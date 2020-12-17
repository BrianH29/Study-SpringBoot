package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

//public 생략가능 bcuz 다른데에서 쓸게 아니기 때문에
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게 되면 다음 테스트를 실행 했을때
    //이전 테스트 때문에 다음 테스트가 실패 할 수 있다. @AfterEach를 사용하면 각 테스트가 종료될 때 마다 이 기능을 실행한다.
    // 여기서 메모리 DB에 저장된 데이터를 삭제한다.
    //테스트 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.print("result = "+ (result == member));
        //member에 저장된 것과 result에 저장된 값이 똑같은지 --> 위와 같이 실행시 result = true 라고 뜬다.
        //그러나 항상 print로 확인 할 수 없기 때문에 assertion을 사용한다.

        //1.방법
        //Assertions.assertEquals(member,result);

        //2.방법 (요즘에 많이 쓰는 방법이라고 함)
        //Assertions.assertThat(member).isEqualTo(result); --> Assertions을 static으로 import.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Tom");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Jerry");
        repository.save(member2);

        Member result = repository.findByName("Tom").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member mem = new Member();
        mem.setName("Tom");
        repository.save(mem);

        Member mem2 = new Member();
        mem2.setName("Jerry");
        repository.save(mem2);

        List<Member> list = repository.findAll();
        assertThat(list.size()).isEqualTo(2);
    }
}
