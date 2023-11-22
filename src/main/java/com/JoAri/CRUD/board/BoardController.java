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
	
}
