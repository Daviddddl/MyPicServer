package com.kevin.imageuploadserver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Auther: davidddl
 * @Date: 2018/5/16 22:12
 * @Description:
 */
@WebServlet(name = "FunctionServlet")
public class FunctionServlet extends HttpServlet {

    public static final String prefixControlPath = "/home/suheng/caffe/examples/HWDB_AD/control";
    public static final String prefixPath = "/home/suheng/caffe/examples/HWDB_AD";

    static String repairBash1 = prefixControlPath + "/repair_step1.sh";
    static String repairBash2 = prefixControlPath + "/repair_step2.sh";
    static String identityBash = prefixControlPath + "/identity.sh";
    static String imitateBash = prefixControlPath + "/imitate.sh";
    static String recoBashSingle = prefixControlPath + "/reco_single.sh";
    static String recoBashMore = prefixControlPath + "/reco_more.sh";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String storeDirectory = getServletContext().getRealPath("/files/images");

        String function = request.getParameter("function");
        String args1 = request.getParameter("args1");
        String args2 = request.getParameter("args2");
        String args3 = request.getParameter("args3");
        String args4 = request.getParameter("args4");
//
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/kewenkang/Downloads/1.txt")), "utf-8"));
//        bw.write(args2);
//        bw.flush();
//        System.out.println("written");

//        System.out.println(new String(args1.getBytes("iso8859-1"), "UTF-8"));
//        System.out.println(new String("哈哈哈".getBytes("ISO-8859-1"), "utf-8"));
        String result = "error";
        
        switch (function){
            case "repair_step1":
                //残损修复
                File fixOrigin = new File(storeDirectory, "fix_origin.png");
                if (fixOrigin.exists()){
                    result = repairStep1();  // 风格，选择的字, 手动输入
                    response.getWriter().print(result);
                }else{
                    response.getWriter().print("error: file fix_origin.png not exists");
                }
                break;
            case "repair_step2":
                //残损修复
                result = repairStep2(args1, args2);  // 风格，选择的字, 手动输入
                response.getWriter().print(result);
                break;
            case "identify":
                //笔迹鉴定
                File imageLeft = new File(storeDirectory, "identityLeft.png");
                File imageRight = new File(storeDirectory, "identityRight.png");
                if (imageLeft.exists() && imageRight.exists()){
                    result = identify();
                    response.getWriter().print(result);
                }else{
                    response.getWriter().print("error: image file not exists");
                }
                break;
            case "imitate":
                //风格模仿
                result = imitate(args1, args2);
                response.getWriter().print(result);
                break;
            case "recoSingle":
                //手写识别
                File recoSingle = new File(storeDirectory, "re_single.png");
                if (recoSingle.exists()){
                    result = recoSingle();
                    response.getWriter().print(result);
                }else{
                    response.getWriter().print("error: file re_single.png not exists");
                }
                break;
            case "recoMore":
                //手写识别
                File recoMore = new File(storeDirectory, "re_more.png");
                if (recoMore.exists()){
                    result = recoMore();
                    response.getWriter().print("error");
                }else{
                    response.getWriter().print("error");
                }
                break;
        }

    }



    private String repairStep2(String style, String charactor){
        // stype: 1表示楷书，2表示行书
        String res = "error";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(prefixPath + "/cache/fix/fix_sel/select.txt")), "utf-8"));
            bw.write(style);
            bw.flush();
            bw.close();

            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(prefixPath + "/cache/x.txt")), "utf-8"));
            bw.write(charactor);
            bw.flush();
            bw.close();

            Runtime runtime = Runtime.getRuntime();
            Process pro = null;
            int status;

            pro = runtime.exec(repairBash2);
            status = pro.waitFor();
            if (status != 0){
                //脚本执行出错
                System.out.println("Failed to call shell's command ");
            }else{

                res = "success";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return res;
        }

    }


    private String repairStep1() {
        Runtime runtime = Runtime.getRuntime();
        Process pro = null;
        String res = "error";
        int status;
        try {
            pro = runtime.exec(repairBash1);
            status = pro.waitFor();
            if (status != 0){
                //脚本执行出错
                System.out.println("Failed to call shell's command ");

            }else{
                //脚本执行成功
                BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                StringBuffer strbr = new StringBuffer();
                String line;
                while ((line = br.readLine())!= null)
                {
                    strbr.append(line).append("\n");
                }

                String result = strbr.toString();
                res = "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return res;
        }

    }
    
    private String identify(){
        String res = "error";
        Runtime runtime = Runtime.getRuntime();
        Process pro = null;
        int status;

        try {
            pro = runtime.exec(identityBash);
            status = pro.waitFor();
            if (status != 0){
                //脚本执行出错
                System.out.println("Failed to call shell's command ");
            }else{
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(prefixPath, "output/judge/score.txt"))));
                res = br.readLine();
                res = "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }
    
    private String imitate(String style, String text){
        String res = "error";
        try {
            // 将风格数字写到文件中
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(prefixPath+"/cache/trans/trans_sel/font.txt")), "utf-8"));
            bw.write(style);
            bw.flush();

            // 将文本写入文件中
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(prefixPath+"/cache/trans/text/ori_text.txt")), "utf-8"));
            bw.write(text);
            bw.flush();

            Runtime runtime = Runtime.getRuntime();
            Process pro = null;
            int status;

            // 执行风格模仿脚本
            pro = runtime.exec(imitateBash);
            status = pro.waitFor();
            if (status != 0){
                //脚本执行出错
                System.out.println("Failed to call shell's command ");
            }else{

                res = "success";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }
    
    private String recoSingle(){
        String res = "error";
        Runtime runtime = Runtime.getRuntime();
        Process pro = null;
        int status;

        try {
            pro = runtime.exec(recoBashSingle);
            status = pro.waitFor();
            if (status != 0){
                //脚本执行出错
                System.out.println("Failed to call shell's command ");
            }else{

                res = "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }

    private String recoMore() {
        String res = "error";
        Runtime runtime = Runtime.getRuntime();
        Process pro = null;
        int status;

        try {
            pro = runtime.exec(recoBashMore);
            status = pro.waitFor();
            if (status != 0){
                //脚本执行出错
                System.out.println("Failed to call shell's command ");
            }else{

                res = "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }


}
