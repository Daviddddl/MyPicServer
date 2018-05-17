package com.kevin.imageuploadserver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: davidddl
 * @Date: 2018/5/16 22:12
 * @Description:
 */
@WebServlet(name = "FunctionServlet")
public class FunctionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String function = request.getParameter("function");
        String args1 = request.getParameter("args1");
        String args2 = request.getParameter("args2");
        String args3 = request.getParameter("args3");
        String args4 = request.getParameter("args4");

        String result = "nothing!!";
        
        switch (function){
            case "repair":
                result = repair(args1, args2, args3);  // 风格，选择的字, 手动输入
                break;
            case "identify":
                identify();
                break;
            case "imitate":
                imitate();
                break;
            case "manufacture":
                manufacture();
                break;
        }

        response.getWriter().print(result);

    }

    private String repair(String style, String resId, String input) {
        // 下面对这些参数进行操作
        System.out.println(style + "===" + resId + "===" + input);
        return style + "===" + resId + "===" + input;
    }
    
    private void identify(){
        
    }
    
    private void imitate(){
        
    }
    
    private void manufacture(){
        
    }
}
