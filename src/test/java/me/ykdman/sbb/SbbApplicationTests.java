package me.ykdman.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {
//        Question q1 = new Question();
//        q1.setSubject("Sbb 가 무엇인가요?");
//        q1.setContent("Sbb에 대해서 알고 싶습니다.");
//        q1.setCreatedDate(LocalDateTime.now());
//        this.questionRepository.save(q1);
//
//
//        Question q2 = new Question();
//        q2.setContent("id 는 자동으로 생성 되나요?");
//        q2.setSubject("스프링 부트 모델 질문 입니다.");
//        q2.setCreatedDate(LocalDateTime.now());
//        this.questionRepository.save(q2);

        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("Sbb 가 무엇인가요?", q.getSubject());

    }

}
