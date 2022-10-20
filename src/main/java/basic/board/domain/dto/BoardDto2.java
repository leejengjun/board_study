package basic.board.domain.dto;

import basic.board.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
public class BoardDto2 {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public BoardDto2(BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.writer = boardEntity.getWriter();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.createdDate = boardEntity.getCreatedDate();
        this.modifiedDate = boardEntity.getModifiedDate();
    }

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return boardEntity;
    }
    @Builder
    public BoardDto2(Long id, String writer, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
