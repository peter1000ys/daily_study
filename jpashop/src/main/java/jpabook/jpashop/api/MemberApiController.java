package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }
    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDTO> collect = findMembers.stream()
                .map( m -> new MemberDTO(m.getName(), m.getAddress()))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }
    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDTO {
        private String name;
        private Address address;
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // DTO 적용, API 스펙을 안바꿔도 된다. 앤티티 외부 노출 방지
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberResquest request) {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @PutMapping("/api/v2/members/{id}")
    public UpdatedMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdatedMemberRequest request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdatedMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdatedMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdatedMemberResponse {
        private Long id;
        private String name;
    }


    // DTO API 스펙 자체가 이름만 받는 다는 것을 확인 가능
    @Data
    static class CreateMemberResquest {
        @NotEmpty
        private String name;
    }
    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
