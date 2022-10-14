package basic.board.controller;

import basic.board.domain.dto.BoardDto;
import basic.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @GetMapping("/list")
    public String boardList() {
        return "board/list.html";
    }

    @GetMapping("/post")
    public String boardWrite() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String boardWrite(BoardDto boardDto) {
        boardService.savePost(boardDto);
        log.info("info boardDto={}", boardDto);
        return "redirect:/board/list";
    }

}
