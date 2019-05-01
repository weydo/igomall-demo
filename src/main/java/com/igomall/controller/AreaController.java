
package com.igomall.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.igomall.common.Page;
import com.igomall.common.Pageable;
import com.igomall.entity.Area;
import com.igomall.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller - 地区
 * 
 * @author IGOMALL Team
 * @version 5.0
 */
@RestController
@RequestMapping("/api/area")
@CrossOrigin
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;

	@GetMapping("/list")
	@JsonView(Area.ListView.class)
	public List<Area> list(){
		return areaService.findRoots();
	}

	@GetMapping("/page")
	@JsonView(Area.ListView.class)
	public Page<Area> page(Pageable pageable){
		return areaService.findPage(pageable);
	}

}