
package com.igomall.controller;

import com.igomall.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller - 地区
 * 
 * @author IGOMALL Team
 * @version 5.0
 */
@Controller("adminAreaController")
@RequestMapping("/admin/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;

}