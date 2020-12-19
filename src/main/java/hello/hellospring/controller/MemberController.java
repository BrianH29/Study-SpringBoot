package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        ms.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = ms.findMembers();
        model.addAttribute("members",members);

        return "/members/membersList";
    }
}
