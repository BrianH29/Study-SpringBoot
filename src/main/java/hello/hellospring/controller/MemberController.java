package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    //DI 필드 주입
    //@Autowired private MemberService ms;

    private MemberService ms;
/*
    Setter 인젝션
    이미 set 한 상태를 수정하게 되면, 무언가 실수를 하게 되면 좋지 않을 수 있다.. 뭔말? ㅋㅋㅋㅋ
    @Autowired
    public void setMs(MemberService ms) {
        this.ms = ms;
    }
*/

    //의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장
    @Autowired
    public MemberController(MemberService ms) {
        this.ms = ms;
    }

}
