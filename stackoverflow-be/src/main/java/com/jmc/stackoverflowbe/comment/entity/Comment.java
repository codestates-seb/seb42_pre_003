package com.jmc.stackoverflowbe.comment.entity;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 100)
    private String commentContent;

    @Column
    private CommentState commentState = CommentState.ACTIVE;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
    @Column
    private Long memberId; // Temporary column

    @Column
    private String memberName; // Temporary column

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinColumn(name = "QA_ID")
//    private QA qa;
    @Column
    private Long qaId; // Temporary column

//    public void setQA(QA qa) {
//        this.qa = qa;
//    }

    @Builder // Temporary constructor
    public Comment(Long commentId, String commentContent, CommentState commentState, Long memberId,
        String memberName, Long qaId) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentState = commentState;
        this.memberId = memberId;
        this.memberName = memberName;
        this.qaId = qaId;
    }

    public enum CommentState {
        ACTIVE("활성"),
        INACTIVE("비활성"),
        DELETED("탈퇴");

        @Getter
        private String state;

        CommentState(String state) {
            this.state = state;
        }
    }
}
