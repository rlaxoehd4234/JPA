package jpabook.jpabook.api;

import jpabook.jpabook.domain.Member;
import jpabook.jpabook.domain.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Validated Member member){
    // 절대로 이렇게 쓰면 안된다.
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @PostMapping("/api/v2/members") // 별도의 dto 를 받는 것이 v2 이다.
    public CreateMemberResponse saveMemberV2(@RequestBody @Validated CreateMemberRequest request){
    // api 의 영향을 절대 받지 않는다.
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v1/members/{id}" ) // 멱등하다고 한다. 같은 것을 여러 번 호출해도 값이 같다.
    public UpdateMemberResponse updateMemberResponse(@PathVariable("id") Long id ,
                                                     @RequestBody @Validated UpdateMemberRequest request){
        memberService.update(id, request.getName());
        Member findMember = memberService.findById(id);
       return new UpdateMemberResponse(findMember.getId(), findMember.getName());
       //커멘드와 쿼리를 분리하는 것이 유지보수성이 높아진다. 
    }






    @Data
    static class CreateMemberRequest {
        private String name;
    }
    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id){
            this.id = id;
        }
    }
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }
    @Data
    static class UpdateMemberRequest {
        private String name;
    }
}
