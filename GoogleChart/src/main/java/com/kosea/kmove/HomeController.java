package com.kosea.kmove;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cart_money_list.do" ,method=RequestMethod.GET)
	public ResponseEntity<JSONObject> money_list(){
		
		ResponseEntity<JSONObject>  entity = null;
		
		// ChartProductPriceDTO -> ProductVO
		
		List<ProductVO> items = new ArrayList<>();
		items.add(new ProductVO("Americano", 3000));
		items.add(new ProductVO("CafeLatte", 4500));
		
		
		//리스트 형태를 json 형태로 만들어서 리턴
		JSONObject data =new JSONObject();
		
		//컬럼객체
		JSONObject col1 =new JSONObject();
		JSONObject col2 =new JSONObject();
		JSONArray title =new JSONArray();
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type" , "number");
		
		title.add(col1);
		title.add(col2);
		
		data.put("cols", title);

		/*   구글 차트 JSON 데이터의 형식		
		"rows": [
			        {"c":[{"v":"Mushrooms"},{"v":3}]},
			        {"c":[{"v":"Onions"},{"v":1}]},
			       ]
			       
		rows : [ 배열 (객체 :배열[객체])]
		

		 */ 	

		//들어갈 형태  ->  rows 객체 에 배열  <- 
		//  <- [  c 라는 객체에 배열 <- 객체
		//  data 객체 -> rows 배열 <-  c 객체  ->배열  <- v 객체 2개/
	
		JSONArray  body =new JSONArray();
		for(ProductVO  dto : items){
			
			JSONObject name =new JSONObject();
			name.put("v", dto.getName());    //상품이름 -> v 객체 
			JSONObject price =new JSONObject(); 
			price.put("v", dto.getPrice());  // 가격 ->v 객체

			JSONArray row =new JSONArray();  // v객체를 row 배열을 만든후 추가한다.
			row.add(name);
			row.add(price);   
 
			JSONObject c =new JSONObject();  // c 객체 를 만든후 row 배열을 담는다.
			c.put("c", row);		
			
			body.add(c);  // c 객체를 배열 형태의 body 에 담는다.		
		}
		
		data.put("rows", body);  // 배열 형태의 body 를 rows 키값으로 객체 data 에 담는다.
		
		try{
			 entity =new ResponseEntity<JSONObject>(data, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(" 에러            -- ");
			entity =new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
	
	
	@RequestMapping(value = "/doJSON", method = RequestMethod.GET)
	public @ResponseBody ProductVO doJSON() {
		
		ProductVO vo = new ProductVO("샘플상품", 30000);
		return vo;
	}
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		logger.info("날짜:{}.", formattedDate);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}


class ProductVO {
	
	private String name;
	private double price;
	
	public ProductVO(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}
	
}
