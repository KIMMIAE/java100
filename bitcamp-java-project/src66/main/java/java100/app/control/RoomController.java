package java100.app.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java100.app.dao.RoomDao;
import java100.app.domain.Room;

//@Component 대신 @Controller를 붙여 페이지 컨트롤러임을 명시한다.
//
//스프링 IoC 컨테이너에서 애노테이션 중에서 객체 생성을 표시하는 애노테이션
//=> @Component
// - 일반 클래스에 대해 주로 붙인다.
//=> @Controller
// - 웹 애플리케이션에서 페이지 컨트롤러 역할을 수행하는 클래스에 주로 붙인다.
//=> @Service
// - MVC 아키텍처에서 Model 중에 비즈니스 로직을 담당하는 클래스에 붙인다.
//=> @Repository
// - MVC 아키텍처의 Model 중에서 데이터 처리를 담당하는 클래스에 붙인다.
// - 주로 DAO 클래스에 붙인다.
//
@Controller
public class RoomController {
    
    @Autowired RoomDao roomDao;
    
    @RequestMapping("/room/list")
    public String list(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        List<Room> list = roomDao.selectList();
        
        // 작업한 결과를 JSP에게 넘겨주기 위해 ServletRequest 보관소에 저장한다.
        request.setAttribute("list", list);
        
        return "/room/list.jsp";
        
    }
    
    @RequestMapping("/room/add")
    public String add(
            Room room,
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        roomDao.insert(room);
        return "redirect:list.do";
    }
    
    @RequestMapping("/room/delete")
    public String delete(
            @RequestParam("no") int no,
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        roomDao.delete(no);
        return "redirect:list.do";
    }
    
    @RequestMapping("/room/form")
    public String form(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return "/room/form.jsp";
        
    }
}








