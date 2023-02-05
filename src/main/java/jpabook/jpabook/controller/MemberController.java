package jpabook.jpabook.controller;

import jpabook.jpabook.domain.Address;
import jpabook.jpabook.domain.Member;
import jpabook.jpabook.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm memberForm , BindingResult result){

      Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
      Member member = new Member();
      member.setName(memberForm.getName());
      member.setAddress(address);

      memberService.join(member);
      return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
