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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardSer;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
    	List<Map<String, Object>> boardList = boardSer.getList();
        
    	model.addAttribute("boardList", boardList);
        
    	return "listPage";
    }
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String create() {
		return "create";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createPost(@RequestParam Map<String, Object> param) {
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
	
}
