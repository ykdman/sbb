package me.ykdman.sbb;

import jakarta.transaction.Transactional;
import me.ykdman.sbb.answer.Answer;
import me.ykdman.sbb.answer.AnswerRepository;
import me.ykdman.sbb.question.Question;
import me.ykdman.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

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

//        List<Question> all = this.questionRepository.findAll();
//        assertEquals(2, all.size());
//
//        Question q = all.get(0);
//        assertEquals("Sbb 가 무엇인가요?", q.getSubject());

//        Optional<Question> oq = this.questionRepository.findById(1);
//        if(oq.isPresent()) {
//            Question q= oq.get();
//            assertEquals("Sbb 가 무엇인가요?", q.getSubject());
//        }

        Question q = this.questionRepository.findBySubjectAndContent("Sbb 가 무엇인가요?","Sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());

    }

    @Test
    void testFindBySubjectLike() {
        Question q= this.questionRepository.findBySubjectLike("Sbb%");
        assertEquals(1, q.getId());

    }

    @Test
    void testUpdateQuestion() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q= oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    void testDeleteQuestion() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    @Test
    void testSaveAnswerData() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q= oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성 됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Test
    void testFindByIdAnswer() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

    @Test
    @Transactional
    void testAnswerListGet() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();
        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성 됩니다.", answerList.get(0).getContent());
    }
}
