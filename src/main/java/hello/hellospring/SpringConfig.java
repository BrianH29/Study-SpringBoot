package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //@Autowired DataSource dataSource;
/*
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/
/*
    //jpa 활용
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/
    private final MemberRepository mr;

    public SpringConfig(MemberRepository mr) {
        this.mr = mr;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(mr);
    }
/*
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository()
        return new JpaMemberRepository(em);
    }
*/

}
