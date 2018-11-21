package com.alevel.brainfuck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@WebServlet("/convert")
public class BrainfuckServlet extends HttpServlet {

    private ObjectMapper objectMapper;
    private BrainfuckInterpreter brainfuckInterpreter;
    private HikariDataSource dataSource;

    @Override
    public void init() {
        HikariConfig hikariConfig = new HikariConfig("/hikary.properties");
        dataSource = new HikariDataSource(hikariConfig);
        brainfuckInterpreter = new BrainfuckInterpreter(560);
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = req.getParameter("text");
        System.out.println(text);
        String answer;
        try {
            Context context = new Context(text);
            NextOperation n = new NextOperation();
            PrevOperation p = new PrevOperation();
            Expression inc = new IncOperation();
            Expression dec = new DecOperation();
            Expression o = new OutOperation();
            Expression rbr = new RightbrOperation();
            Expression lbr = new LeftbrOperation();
            while (context.getConvertiblepointer() < text.length())
            {
                System.out.println(context.getData());
                System.out.println("pk");
                n.interpret(context);
                p.interpret(context);
                inc.interpret(context);
                dec.interpret(context);
                o.interpret(context);
                rbr.interpret(context);
                lbr.interpret(context);
                char m = context.getConvertiblepointer();
                context.setConvertiblepointer(++m);
            }

            answer = context.getOutput();
        } catch (Exception e) {
            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json;charset=utf8");
        Map<String, String> responseData = Collections.singletonMap("answer", answer);
        objectMapper.writeValue(resp.getOutputStream(), responseData);
    }

    @Override
    public void destroy() {
        dataSource.close();
    }
}
