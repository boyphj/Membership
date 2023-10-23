package commons;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ScriptUtil {
    public static void alertError(HttpServletResponse resp, Exception e) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.printf("<script>alert('%s');</script>", e.getMessage());
        e. printStackTrace();
    }
    public static void go(HttpServletResponse resp, String url, String target) throws IOException {
        target = target == null || target.isBlank() ? "self" : target;

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.printf("<script>%s.location.replace('%s');</script>", target, url);
    }

    public static void go(HttpServletResponse resp, String url) throws IOException{
        go(resp, url, null);
    }
}
