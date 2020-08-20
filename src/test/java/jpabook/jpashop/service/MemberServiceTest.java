package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    public void joinMember() throws Exception{
        //given
        Member member = Member.builder()
                .username("준성")
                .build();

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void duplicateMember() throws Exception {
        //given
        Member member1 = Member.builder()
                .username("준성")
                .build();
        Member member2 = Member.builder()
                .username("준성")
                .build();

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);

        } catch (IllegalStateException e) {
            return;
        }
        //then
        fail("예외가 발생해야 한다.");

    }
}