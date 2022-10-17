package basic.board.controller;

import basic.board.domain.dto.BoardDto;
import basic.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    /**
     * 게시물 리스트 보기
     */
    @GetMapping("/list")
    public String boardList(Model model) {
        List<BoardDto> boardList = boardService.getBoardList();

        model.addAttribute("boardList", boardList);
        return "board/list.html";
    }

    /**
     * 글쓰기 
     */
    @GetMapping("/post")
    public String boardWrite() {
        return "board/write.html";
    }

    /**
     * 글쓰기 완료
     */
    @PostMapping("/post")
    public String boardWrite(BoardDto boardDto) {
        boardService.savePost(boardDto);
        log.info("info boardDto={}", boardDto);
        return "redirect:/board/list";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 게시글 상세조회 페이지
     */
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getPost(no);
        
        model.addAttribute("boardDto", boardDto);
        return "board/detail.html";
    }

    /**
     * 게시글 수정 페이지
     */
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getPost(no);

        model.addAttribute("boardDto", boardDto);
        return "board/update.html";
    }

    /**
     * 게시글 수정완료
     */
    @PatchMapping("/post/edit/{no}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/board/list";
    }

    /**
     * 게시글 삭제
     */


}
