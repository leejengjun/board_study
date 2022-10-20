package basic.board.controller;

import basic.board.domain.dto.BoardDto;
import basic.board.domain.dto.BoardDto2;
import basic.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    /**
     * 게시물 리스트 보기(복잡함)
     */
    @GetMapping("/list")
    public String boardList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);


        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list.html";
    }

    /**
     * 게시물 리스트 보기 Ver2
     */
    @GetMapping("/list-ver2")
    public String boardList(Model model
            , @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoardDto2> boardDtoList = boardService.pageList(pageable);

        int totalPages = boardDtoList.getTotalPages(); //총 페이지 수
        boardDtoList.previousOrFirstPageable().getPageNumber();

        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("totalPages", totalPages);

        return "board/list2.html";
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
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board/list";
    }

    /**
     * 검색
     */
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list.html";
    }


}
