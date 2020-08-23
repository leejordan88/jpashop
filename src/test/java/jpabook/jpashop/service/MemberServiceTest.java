package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepositoryOld memberRepositoryOld;

    @Test
    @DisplayName("회원가입")
    public void joinMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("준성");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepositoryOld.findOne(saveId));
    }

    @Test
    public void duplicateMember() throws Exception {
        //given
        Member member1 = new Member();
        member1.setUsername("준성");
        Member member2 = new Member();
        member2.setUsername("준성");

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