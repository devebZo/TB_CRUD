package com.JoAri.CRUD.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardSer;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam Map<String, Object> param) {
        BoardPager pager1 = new BoardPager();

		int totalCount = boardSer.boardsNum(param); // �� �Խñ� ��
		pager1.setTotPage(totalCount); // �� ������ �� setter
		int totalPage = pager1.getTotPage(); // �� ������ �� getter
		int pageNum = 0;
		// Default ������ ����
		if (param.get("pageNum") == null || Integer.parseInt(param.get("pageNum").toString()) == 1) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(param.get("pageNum").toString());
		}

		if (pageNum > totalPage)
			pageNum = totalPage;
		if (pageNum < 1)
			pageNum = 1;

		BoardPager pager2 = new BoardPager(totalCount, pageNum);
		int startRow = pager2.getPageBegin();
		int endRow = pager2.getPageEnd();

		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		List<Map<String, Object>> boardList = boardSer.getList(param);
		
		model.addAttribute("pager", pager2);
    	model.addAttribute("boardList", boardList);
        
    	return "listPage";
    }
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String create() {
		return "create";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createPost(@RequestParam Map<String, Object> param, @RequestPart("imgs") List<MultipartFile> imgs) {
		param.put("imgs", imgs);
		boardSer.createBoard(param);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="/read/{seq}", method = RequestMethod.GET)
	public ModelAndView readBoard(@PathVariable("seq") int seq, ModelAndView mav) {
		Map<String, Object> boardMap = boardSer.showBoard(seq);
		
		if(boardMap != null) {
			boardSer.incViewCnt(seq);
		}
		
		mav.addObject("board", boardMap);
		mav.setViewName("read");
		
		return mav;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updateBoard(@RequestParam Map<String, Object> param) {
		boardSer.updateBoard(param);
		
		int seq = Integer.parseInt(param.get("seq").toString());
		
		return "redirect:/read/"+seq;
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deletePost(Integer[] chkBox) {
		boardSer.deleteBoard(chkBox);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value = "ajaxList")
	public String ajaxList(Model model, @RequestParam Map<String, Object> param) {
		BoardPager pager1 = new BoardPager();

		int totalCount = boardSer.boardsNum(param); // �� �Խñ� ��
		pager1.setTotPage(totalCount); // �� ������ �� setter
		int totalPage = pager1.getTotPage(); // �� ������ �� getter
		int pageNum = 0;
		// Default ������ ����
		if (param.get("pageNum") == null || Integer.parseInt(param.get("pageNum").toString()) == 1) {
			pageNum = 1;
		} else {
			pageNum = Integer.parseInt(param.get("pageNum").toString());
		}

		if (pageNum > totalPage)
			pageNum = totalPage;
		if (pageNum < 1)
			pageNum = 1;

		BoardPager pager2 = new BoardPager(totalCount, pageNum);
		int startRow = pager2.getPageBegin();
		int endRow = pager2.getPageEnd();

		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		List<Map<String, Object>> boardList = boardSer.getList(param);
		
		model.addAttribute("pager", pager2);
    	model.addAttribute("boardList", boardList);

		return "ajaxList";
	}
	
}
