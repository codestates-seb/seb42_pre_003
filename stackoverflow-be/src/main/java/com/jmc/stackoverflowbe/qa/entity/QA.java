package com.jmc.stackoverflowbe.qa.entity;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QA extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qaId;

    @Column
    private String qaContent;

    @Column
    private QaGroup group;

    @Column
    private StateGroup state;

    @Column
    private Long votes;

    public static enum QaGroup{

        QUESTION("질문"),
        ANSWER("답변");

        @Getter
        private String state;

        QaGroup(String state) {
            this.state = state;
        }
    }
    public static enum StateGroup {
        ACTIVE("활성"),
        INACTIVE("휴면"),
        DELETED("탈퇴");

        @Getter
        private String state;

        StateGroup(String state) {
            this.state = state;
        }
    }

}
