package basic.board.controller;

import basic.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

//    private BoardService boardService;

    @GetMapping("/list")
    public String boardList() {
        return "board/list.html";
    }
}
