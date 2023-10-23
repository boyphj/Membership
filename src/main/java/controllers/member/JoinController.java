package controllers.member;


import commons.ScriptUtil;
import static commons.ScriptUtil.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.member.JoinService;
import models.member.ServiceManager;

import java.io.IOException;


@WebServlet("/member/join")
public class JoinController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/member/join.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try { // 가입 성공
            JoinService joinService = ServiceManager.getInstance().joinService();
            joinService.join(req);

            String url = req.getContextPath() + "/member/login";
            go(resp, url, "parent");
        } catch (Exception e){ // 가입 실패
            ScriptUtil.alertError(resp, e);
        }
    }
}
