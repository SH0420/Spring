package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
   @RequestMapping("")
   public void basic() {
      log.info("basic...........");
   }
   
   @GetMapping("ex01")
   public String ex01(SampleDTO dto) {  //커맨드 객체(Command Object):HttpServletRequest 를 통해 들어온 요청 파라미터들을
                                                          //setter메서드를 이용하여 객체에 정의되어있는 속성에 바인딩이 되는 객체
      log.info("" + dto);
      
      return "ex01";
   }
   
   
   @GetMapping("ex02")
   public String ex02(@RequestParam("name")String name, @RequestParam("age") int age) {
      log.info(name);
      log.info(age);
      
      return "ex02";
   }
   
   @GetMapping("ex02List")
   public String ex02List(@RequestParam("ids")ArrayList<String>ids) {
      log.info("ids : " + ids);
      
      return "ex02List";
   }
   
   @GetMapping("ex02Bean")
   public String ex02Bean(SampleDTOList list) {
      log.info("list : " + list);
      
      return "ex02Bean";
   }
   
   
   @GetMapping("/ex03")
   public String ex03(TodoDTO todo) {
      
      log.info("todo: " + todo);
      
      return "ex03";
   }
   
   @GetMapping("/ex04")
   public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {  //커맨드객체(Command Object) 
      //커맨드객체(Command Object) 
      //1. 파라메터 자동으로 받기
      //2. 뷰 페이지로 커맨드객체의 정보를 전달한다(클래스의 첫 글자를 소문자로 구성해서 전달)
      //  sampleDTO
	  //3. 기본형 매개변수에 대해 뷰페이지로 값 전달을 위해 @ModelAttribute를 적용한다.
	  //  MODEL객체에 담겨서 전달된다.
      
      log.info("dto: " + dto);
      log.info("page: " + page);
      
      return "/sample/ex04";
   }
   
   @GetMapping("/ex06")
   public @ResponseBody SampleDTO ex06() {  
     
	   SampleDTO dto = new SampleDTO();
	   dto.setName("eunha");
	   dto.setAge(20);
	   
	   return dto;
   }
   
   @GetMapping("/ex07")
   public ResponseEntity <String> ex07() {
      
      log.info("/ex07..........");
      
      //{"name" : "홍길동"}
      String msg = "{\"name\": \"홍길동\"}";
      
      HttpHeaders header = new HttpHeaders();
      header.add("Content-Type", "application/json;charset=UTF-8");
      
      return new ResponseEntity<>(msg, header, HttpStatus.OK);
   }
   
   
   @GetMapping("/exUpload")
   public void exUpload() {
      log.info("/exUpload.......");
   }
   
   @PostMapping("/exUploadPost")
   public void exUploadPost(ArrayList<MultipartFile> files) {
      
      files.forEach(file -> {
         log.info("------------------------");
         log.info("name : " + file.getOriginalFilename());
         log.info("size : " + file.getSize());
      });
   }
}